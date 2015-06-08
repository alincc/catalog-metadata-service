package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

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
    public Mods getMods(String id) {
        String modsString = repository.getModsString(id);
        if (null == modsString) {
            return null;
        }
        return (Mods) marshaller.unmarshal(new StreamSource(new StringReader(modsString)));
    }

    @Override
    public RecordType getMarcxml(String id) {
        String modsString = repository.getModsString(id);
        if (null == modsString) {
            return null;
        }
        String marcString = transformerService.transform(modsString, TransformerServiceImpl.MODS2MARC21);

        JAXBElement<RecordType> root = (JAXBElement<RecordType>) marshaller.unmarshal(new StreamSource(new StringReader(marcString)));
        return root.getValue();
    }
}
