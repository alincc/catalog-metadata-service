package no.nb.microservices.catalogmetadata.core.metadata.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Paths;

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

@RunWith(MockitoJUnitRunner.class)
public class MetadataServiceImplTest {

    @Mock
    private IMetadataRepository metadataRepository;

    private MetadataServiceImpl metadataService;


    @Before
    public void setup() {
        ITransformerService transformerService = new TransformerServiceImpl();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("no.nb.microservices.catalogmetadata.model", "loc.gov.marc");
        metadataService = new MetadataServiceImpl(marshaller, metadataRepository, transformerService);
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
    public void testGetStructById() throws Exception {
        File structFile = new File(Paths.get(getClass().getResource("/xml/struct1.xml").toURI()).toString());
        String structString = FileUtils.readFileToString(structFile);

        when(metadataRepository.getStructById("bfa3324befaa4518b581125fd701900e")).thenReturn(structString);

        StructMap struct = metadataService.getStructById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(struct);

        verify(metadataRepository).getStructById("bfa3324befaa4518b581125fd701900e");
        verifyNoMoreInteractions(metadataRepository);
    }
    
    @Test(expected = FieldNotFoundException.class)
    public void testGetFieldsByNullId() throws Exception {
        when(metadataRepository.getFieldsById(null)).thenThrow(new FieldNotFoundException(""));

        metadataService.getFieldsById(null);
    }

}
