package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MetadataServiceImplTest {

    @Mock
    private IMetadataRepository metadataRepository;

    private IMetadataService metadataService;


    @Before
    public void setup() {
        ITransformerService transformerService = new TransformerServiceImpl();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("no.nb.microservices.catalogmetadata.model.mods.v3", "loc.gov.marc");
        metadataService = new MetadataServiceImpl(marshaller, metadataRepository, transformerService);
    }


    @Test
    public void testGetModsById() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(modsString);
        when(metadataRepository.getModsStringById(null)).thenReturn(null);

        Mods mods = metadataService.getModsById("c06c5cbe2f82113e7b4757dbb14f8676");
        assertNotNull(mods);
        assertNull(metadataService.getModsById(null));

        verify(metadataRepository).getModsStringById("c06c5cbe2f82113e7b4757dbb14f8676");
        verify(metadataRepository).getModsStringById(null);
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetMarcxmlById() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(modsString);
        when(metadataRepository.getModsStringById(null)).thenReturn(null);

        RecordType marc = metadataService.getMarcxmlById("c06c5cbe2f82113e7b4757dbb14f8676");
        assertNotNull(marc);
        assertNull(metadataService.getMarcxmlById(null));

        verify(metadataRepository).getModsStringById("c06c5cbe2f82113e7b4757dbb14f8676");
        verify(metadataRepository).getModsStringById(null);
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetFieldsById() throws Exception {
        File fieldsFile = new File(Paths.get(getClass().getResource("/json/fields1.json").toURI()).toString());
        String fieldsString = FileUtils.readFileToString(fieldsFile);
        when(metadataRepository.getFieldsById("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(fieldsString);
        when(metadataRepository.getFieldsById(null)).thenReturn(null);

        String fields1 = metadataService.getFieldsById("c06c5cbe2f82113e7b4757dbb14f8676");
        String fields2 = metadataService.getFieldsById(null);
        assertNotNull(fields1);
        assertNull(fields2);


        verify(metadataRepository).getFieldsById("c06c5cbe2f82113e7b4757dbb14f8676");
        verify(metadataRepository).getFieldsById(null);
        verifyNoMoreInteractions(metadataRepository);
    }
}
