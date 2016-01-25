package no.nb.microservices.catalogmetadata.core.metadata.service;

import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import no.nb.microservices.catalogmetadata.core.index.repository.IndexRepository;
import no.nb.microservices.catalogsearchindex.ItemResource;
import no.nb.microservices.catalogsearchindex.NBSearchType;
import no.nb.microservices.catalogsearchindex.SearchResource;
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
    private final IndexRepository indexRepository;
    private final ITransformerService transformerService;

    @Autowired
    public MetadataServiceImpl(Jaxb2Marshaller marshaller, IMetadataRepository repository, IndexRepository indexRepository, ITransformerService transformerService) {
        this.marshaller = marshaller;
        this.repository = repository;
        this.indexRepository = indexRepository;
        this.transformerService = transformerService;
    }

    @Override
    public Mods getModsById(String id) {
        id = getId(id);

        String modsString = repository.getModsStringById(id);
        return (Mods) marshaller.unmarshal(new StreamSource(new StringReader(modsString)));
    }

    @Override
    public RecordType getMarcxmlById(String id) {
        id = getId(id);

        String modsString = repository.getModsStringById(id);
        String marcString = transformerService.transform(modsString, TransformerServiceImpl.MODS2MARC21);

        JAXBElement<RecordType> root = (JAXBElement<RecordType>) marshaller.unmarshal(new StreamSource(new StringReader(marcString)));
        return root.getValue();
    }

    @Override
    public Fields getFieldsById(String id) {
        id = getId(id);
        return repository.getFieldsById(id);
    }

    @Override
    public StructMap getStructById(String id) {
        id = getId(id);
        String structString = repository.getStructById(id);
        return (StructMap) marshaller.unmarshal(new StreamSource(new StringReader(structString)));
    }

    private String getId(String id) {
        if(id != null && !id.isEmpty()) {
            if (id.contains("URN:NBN")) {
                SearchResource searchResource = indexRepository.search("urn:\"" + id + "\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH);
                List<ItemResource> items = searchResource.getEmbedded().getItems();
                if (items != null && !items.isEmpty()) {
                    return items.get(0).getItemId();
                }
            }
        }
        return id;
    }
}
