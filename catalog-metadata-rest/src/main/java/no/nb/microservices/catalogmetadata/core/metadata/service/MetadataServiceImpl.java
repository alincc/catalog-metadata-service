package no.nb.microservices.catalogmetadata.core.metadata.service;

import java.io.StringReader;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

@Service
public class MetadataServiceImpl implements MetadataService {
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
        return (Mods) marshaller.unmarshal(new StreamSource(new StringReader(modsString)));
    }

    @Override
    public RecordType getMarcxmlById(String id) {
        String modsString = repository.getModsStringById(id);
        String marcString = transformerService.transform(modsString, TransformerServiceImpl.MODS2MARC21);

        JAXBElement<RecordType> root = (JAXBElement<RecordType>) marshaller.unmarshal(new StreamSource(new StringReader(marcString)));
        return root.getValue();
    }

    @Override
    public Fields getFieldsById(String id) {
        return repository.getFieldsById(id);
    }

    @Override
    public StructMap getStructById(String id) {
        String structString = repository.getStructById(id);
        return (StructMap) marshaller.unmarshal(new StreamSource(new StringReader(structString)));
    }

}
