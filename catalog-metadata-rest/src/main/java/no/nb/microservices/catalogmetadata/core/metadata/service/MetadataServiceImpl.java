package no.nb.microservices.catalogmetadata.core.metadata.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import com.datastax.driver.core.utils.Bytes;
import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.domain.Model;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.exception.FieldsParserException;
import no.nb.microservices.catalogmetadata.exception.ModsNotFoundException;
import no.nb.microservices.catalogmetadata.exception.StructNotFoundException;
import no.nb.microservices.catalogmetadata.model.fields.Field;
import no.nb.microservices.catalogmetadata.model.fields.Fields;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

import no.nb.microservices.catalogmetadata.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author jimarthurnilsen
 * @author ronnymikalsen
 *
 */
@Service
public class MetadataServiceImpl implements IMetadataService {
    private final Jaxb2Marshaller marshaller;
    private final IMetadataRepository repository;
    private final ITransformerService transformerService;

    @Autowired
    public MetadataServiceImpl(Jaxb2Marshaller marshaller, IMetadataRepository repository, ITransformerService transformerService) {
        this.marshaller = marshaller;
        this.repository = repository;
        this.transformerService = transformerService;
    }

    @Override
    public Mods getModsById(String id) {
        String modsString = repository.getModsStringById(id);
        if (modsString == null) {
            throw new ModsNotFoundException("Mods not found for id " + id);
        }
        return (Mods) marshaller.unmarshal(new StreamSource(new StringReader(modsString)));
    }

    @Override
    public RecordType getMarcxmlById(String id) {
        String modsString = repository.getModsStringById(id);
        if (modsString == null) {
            throw new ModsNotFoundException("Mods not found for id " + id);
        }
        String marcString = transformerService.transform(modsString, TransformerServiceImpl.MODS2MARC21);

        JAXBElement<RecordType> root = (JAXBElement<RecordType>) marshaller.unmarshal(new StreamSource(new StringReader(marcString)));
        return root.getValue();
    }

    @Override
    public Fields getFieldsById(String id) {
        Map<String, String> map = repository.getFieldsById(id);
        if (map == null) {
            throw new FieldNotFoundException("Field not found for id " + id);
        }
        ObjectMapper mapper = new ObjectMapper();
        Fields fields;
        try {
            List<Field> fieldlist = mapper.readValue(map.get("fields"), new TypeReference<List<Field>>(){});
            fields = populateField(fieldlist);
            fields.setContentClasses(mapper.readValue(map.get("contentClasses"), new TypeReference<List<String>>(){}));
            fields.setMetadataClasses(mapper.readValue(map.get("metadataClasses"), new TypeReference<List<String>>(){}));
        } catch (Exception ex) {
            throw new FieldsParserException("Error parsing " + id, ex);
        }
        return fields;
    }

    private Fields populateField(List<Field> fieldsList) {
        Fields fields = new Fields();
        
        String digital = getNamedField("digital", fieldsList).getValue();
        fields.setDigital("Ja".equals(digital) ? true : false);
        
        return fields;
    }
    private Field getNamedField(String name, List<Field> fields) {
        for(Field field : fields) {
            if (field.getName().equalsIgnoreCase(name)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public StructMap getStructById(String id) {
        String structString = repository.getStructById(id);
        if (structString == null) {
            throw new StructNotFoundException("Structure not found for id " + id);
        }

        return (StructMap) marshaller.unmarshal(new StreamSource(new StringReader(structString)));
    }
}
