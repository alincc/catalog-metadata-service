package no.nb.microservices.catalogmetadata.core.metadata.service;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.exception.FieldsParserException;
import no.nb.microservices.catalogmetadata.model.fields.Field;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;

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
            return null;
        }
        return (Mods) marshaller.unmarshal(new StreamSource(new StringReader(modsString)));
    }

    @Override
    public RecordType getMarcxmlById(String id) {
        String modsString = repository.getModsStringById(id);
        if (modsString == null) {
            return null;
        }
        String marcString = transformerService.transform(modsString, TransformerServiceImpl.MODS2MARC21);

        JAXBElement<RecordType> root = (JAXBElement<RecordType>) marshaller.unmarshal(new StreamSource(new StringReader(marcString)));
        return root.getValue();
    }

    @Override
    public List<Field> getFieldsById(String id) {
        String fieldsAsJson = repository.getFieldsById(id);
        if (fieldsAsJson == null) {
            throw new FieldNotFoundException("Field not found for id " + id);
        }
        
        ObjectMapper mapper = new ObjectMapper();
        List<Field> fields = null;
        try {
            fields = mapper.readValue(fieldsAsJson, new TypeReference<List<Field>>(){
            });
        } catch (Exception ex) {
            throw new FieldsParserException("Error parsing " + id, ex);
        }
        return fields;
    }
}
