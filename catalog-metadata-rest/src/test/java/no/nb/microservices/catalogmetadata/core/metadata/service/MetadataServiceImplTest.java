package no.nb.microservices.catalogmetadata.core.metadata.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.core.index.repository.IndexRepository;
import no.nb.microservices.catalogsearchindex.EmbeddedWrapper;
import no.nb.microservices.catalogsearchindex.ItemResource;
import no.nb.microservices.catalogsearchindex.NBSearchType;
import no.nb.microservices.catalogsearchindex.SearchResource;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.exception.ModsNotFoundException;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

import javax.naming.directory.SearchResult;

@RunWith(MockitoJUnitRunner.class)
public class MetadataServiceImplTest {

    @Mock
    private IMetadataRepository metadataRepository;

    @Mock
    private IndexRepository indexRepository;

    private MetadataServiceImpl metadataService;


    @Before
    public void setup() {
        ITransformerService transformerService = new TransformerServiceImpl();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("no.nb.microservices.catalogmetadata.model", "loc.gov.marc");
        metadataService = new MetadataServiceImpl(marshaller, metadataRepository, indexRepository, transformerService);
    }

    @Test
    public void whenModsIsFoundResponseShouldBeNotNull() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);

        Mods mods = metadataService.getModsById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(mods);

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void whenUsingUrnInsteadOfSesamidModsIsFoundWResponseShouldBeNotNull() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);
        SearchResource searchResource = createSearchResult(Arrays.asList("bfa3324befaa4518b581125fd701900e"));
        when(indexRepository.search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH)).thenReturn(searchResource);

        Mods mods = metadataService.getModsById("URN:NBN:no-nb_digibok_2014091948004");
        assertNotNull(mods);

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
        verify(indexRepository).search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH);
        verifyNoMoreInteractions(indexRepository);
    }

    @Test
    public void testNewspaperMods() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods_newspaper.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);

        Mods mods = metadataService.getModsById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(mods);

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testModsWithMultipleStreamingInfos() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods2.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("90201f5a4ed944797d10360893049b33")).thenReturn(modsString);

        Mods mods = metadataService.getModsById("90201f5a4ed944797d10360893049b33");
        assertNotNull(mods);

        assertEquals(2, mods.getExtension().getStreamingInfos().size());
        assertEquals("URN:NBN:no-nb_drl_4603", mods.getExtension().getStreamingInfos().get(0).getIdentifier().getValue());
        assertEquals("URN:NBN:no-nb_drl_2021", mods.getExtension().getStreamingInfos().get(1).getIdentifier().getValue());

        verify(metadataRepository).getModsStringById("90201f5a4ed944797d10360893049b33");
        verifyNoMoreInteractions(metadataRepository);
    }


    @Test(expected = ModsNotFoundException.class)
    public void whenModsIsNotFoundThenExceptionShouldBeThrown() throws Exception {
        when(metadataRepository.getModsStringById(null)).thenThrow(new ModsNotFoundException(""));

        metadataService.getModsById(null);
    }

    @Test
    public void testGetMarcxmlById() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);

        RecordType marc = metadataService.getMarcxmlById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(marc);

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetMarcxmlByUrn() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);
        SearchResource searchResource = createSearchResult(Arrays.asList("bfa3324befaa4518b581125fd701900e"));
        when(indexRepository.search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH)).thenReturn(searchResource);

        RecordType marc = metadataService.getMarcxmlById("URN:NBN:no-nb_digibok_2014091948004");
        assertNotNull(marc);

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
        verify(indexRepository).search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH);
        verifyNoMoreInteractions(indexRepository);
    }

    @Test
    public void testGetFieldsById() throws Exception {
        Fields fieldsModel = new Fields("id1");
        fieldsModel.setContentClasses("[\"restricted\", \"jp2\", \"public\"]");
        fieldsModel.setMetadataClasses("[\"public\"]");
        fieldsModel.setFieldsAsJson("[{\"name\": \"title\",\"value\": \"Composite title\"},{\"name\":\"digital\",\"value\":\"Ja\"}]");
        when(metadataRepository.getFieldsById("41a7fb4e94aab9a88be23745a1504a92")).thenReturn(fieldsModel);

        Fields fields = metadataService.getFieldsById("41a7fb4e94aab9a88be23745a1504a92");
        assertNotNull(fields);

        verify(metadataRepository).getFieldsById("41a7fb4e94aab9a88be23745a1504a92");
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetFieldsByUrn() throws Exception {
        Fields fieldsModel = new Fields("id1");
        fieldsModel.setContentClasses("[\"restricted\", \"jp2\", \"public\"]");
        fieldsModel.setMetadataClasses("[\"public\"]");
        fieldsModel.setFieldsAsJson("[{\"name\": \"title\",\"value\": \"Composite title\"},{\"name\":\"digital\",\"value\":\"Ja\"}]");
        when(metadataRepository.getFieldsById("41a7fb4e94aab9a88be23745a1504a92")).thenReturn(fieldsModel);
        SearchResource searchResource = createSearchResult(Arrays.asList("41a7fb4e94aab9a88be23745a1504a92"));
        when(indexRepository.search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH)).thenReturn(searchResource);

        Fields fields = metadataService.getFieldsById("URN:NBN:no-nb_digibok_2014091948004");
        assertNotNull(fields);

        verify(metadataRepository).getFieldsById("41a7fb4e94aab9a88be23745a1504a92");
        verifyNoMoreInteractions(metadataRepository);
        verify(indexRepository).search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH);
        verifyNoMoreInteractions(indexRepository);
    }

    @Test
    public void testGetStructById() throws Exception {
        File structFile = new File(Paths.get(getClass().getResource("/xml/struct1.xml").toURI()).toString());
        String structString = FileUtils.readFileToString(structFile);

        when(metadataRepository.getStructById("bfa3324befaa4518b581125fd701900e")).thenReturn(structString);

        StructMap struct = metadataService.getStructById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(struct);

        verify(metadataRepository).getStructById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetStructByUrn() throws Exception {
        File structFile = new File(Paths.get(getClass().getResource("/xml/struct1.xml").toURI()).toString());
        String structString = FileUtils.readFileToString(structFile);

        when(metadataRepository.getStructById("bfa3324befaa4518b581125fd701900e")).thenReturn(structString);
        SearchResource searchResource = createSearchResult(Arrays.asList("bfa3324befaa4518b581125fd701900e"));
        when(indexRepository.search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH)).thenReturn(searchResource);

        StructMap struct = metadataService.getStructById("URN:NBN:no-nb_digibok_2014091948004");
        assertNotNull(struct);

        verify(metadataRepository).getStructById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
        verify(indexRepository).search("urn:\"URN:NBN:no-nb_digibok_2014091948004\"", 0, 1, NBSearchType.FIELD_RESTRICTED_SEARCH);
        verifyNoMoreInteractions(indexRepository);
    }

    @Test(expected = FieldNotFoundException.class)
    public void testGetFieldsByNullId() throws Exception {
        when(metadataRepository.getFieldsById(null)).thenThrow(new FieldNotFoundException(""));

        metadataService.getFieldsById(null);
    }

    private SearchResource createSearchResult(List<String> itemIds) {
        SearchResource searchResource = new SearchResource();
        EmbeddedWrapper embeddedWrapper = new EmbeddedWrapper();
        List<ItemResource> items = new ArrayList<>();
        for(String itemId : itemIds) {
            ItemResource itemResource = new ItemResource();
            itemResource.setItemId(itemId);
            items.add(itemResource);
        }
        embeddedWrapper.setItems(items);
        searchResource.setEmbedded(embeddedWrapper);

        return searchResource;
    }
}
