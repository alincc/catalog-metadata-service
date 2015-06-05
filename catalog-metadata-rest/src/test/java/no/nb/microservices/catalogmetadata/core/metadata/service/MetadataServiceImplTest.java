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

    private Jaxb2Marshaller marshaller;
    private IMetadataService metadataService;
    private ITransformerService transformerService;


    @Before
    public void setup() {
        transformerService = new TransformerServiceImpl();
        marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("no.nb.microservices.catalogmetadata.model.mods.v3","loc.gov.marc");
        metadataService = new MetadataServiceImpl(marshaller, metadataRepository,transformerService);
    }


    @Test
    public void testGetMods() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsString("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(modsString);
        when(metadataRepository.getModsString(null)).thenReturn(null);

        Mods mods = metadataService.getMods("c06c5cbe2f82113e7b4757dbb14f8676");
        assertNotNull(mods);
        assertNull(metadataService.getMods(null));

        verify(metadataRepository).getModsString("c06c5cbe2f82113e7b4757dbb14f8676");
        verify(metadataRepository).getModsString(null);
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetMarcxml() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsString("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(modsString);
        when(metadataRepository.getModsString(null)).thenReturn(null);

        RecordType marc = metadataService.getMarcxml("c06c5cbe2f82113e7b4757dbb14f8676");
        assertNotNull(marc);
        assertNull(metadataService.getMarcxml(null));

        verify(metadataRepository).getModsString("c06c5cbe2f82113e7b4757dbb14f8676");
        verify(metadataRepository).getModsString(null);
        verifyNoMoreInteractions(metadataRepository);
    }
}
