package no.nb.microservices.catalogmetadata.service;

import loc.gov.marc.RecordType;
import loc.gov.mods.ModsType;
import no.nb.microservices.catalogmetadata.repository.CassandraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Service
public class CassandraService {
    private final Jaxb2Marshaller marshaller;
    private final CassandraRepository repository;
    private final TransformerService transformerService;

    @Autowired
    public CassandraService(Jaxb2Marshaller marshaller, CassandraRepository repository, TransformerService transformerService) {
        this.marshaller = marshaller;
        this.repository = repository;
        this.transformerService = transformerService;
    }

    public ModsType getMods(String id) {
        String modsString = repository.getModsString(id);
        if (null == modsString) return null;
        JAXBElement<ModsType> root = (JAXBElement<ModsType>) marshaller.unmarshal(new StreamSource(new StringReader(modsString)));
        return root.getValue();
    }

    public RecordType getMarcxml(String id) {
        String modsString = repository.getModsString(id);
        if (null == modsString) return null;
        String marcString = transformerService.transform(modsString, TransformerService.MODS2MARC21);

        JAXBElement<RecordType> root = (JAXBElement<RecordType>) marshaller.unmarshal(new StreamSource(new StringReader(marcString)));
        return root.getValue();
    }
}
