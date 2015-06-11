package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.repository.IMetadataRepository;
import no.nb.microservices.catalogmetadata.core.transform.service.ITransformerService;
import no.nb.microservices.catalogmetadata.core.transform.service.TransformerServiceImpl;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.model.fields.Field;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;

import no.nb.microservices.catalogmetadata.model.struct.StructMap;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author jimarthurnilsen
 * @author ronnymikalsen
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MetadataServiceImplTest {

    @Mock
    private IMetadataRepository metadataRepository;

    private IMetadataService metadataService;


    @Before
    public void setup() {
        ITransformerService transformerService = new TransformerServiceImpl();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("no.nb.microservices.catalogmetadata.model", "loc.gov.marc");
        metadataService = new MetadataServiceImpl(marshaller, metadataRepository, transformerService);
    }


    @Test
    public void testGetModsById() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);
        when(metadataRepository.getModsStringById(null)).thenReturn(null);

        Mods mods = metadataService.getModsById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(mods);
        assertNull(metadataService.getModsById(null));

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verify(metadataRepository).getModsStringById(null);
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetMarcxmlById() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        when(metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e")).thenReturn(modsString);
        when(metadataRepository.getModsStringById(null)).thenReturn(null);

        RecordType marc = metadataService.getMarcxmlById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(marc);
        assertNull(metadataService.getMarcxmlById(null));

        verify(metadataRepository).getModsStringById("bfa3324befaa4518b581125fd701900e");
        verify(metadataRepository).getModsStringById(null);
        verifyNoMoreInteractions(metadataRepository);
    }

    @Test
    public void testGetFieldsById() throws Exception {
        File fieldsFile = new File(Paths.get(getClass().getResource("/json/fields1.json").toURI()).toString());
        String fieldsString = FileUtils.readFileToString(fieldsFile);
        when(metadataRepository.getFieldsById("bfa3324befaa4518b581125fd701900e")).thenReturn(fieldsString);

        List<Field> fields = metadataService.getFieldsById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(fields);


        verify(metadataRepository).getFieldsById("bfa3324befaa4518b581125fd701900e");
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
        when(metadataRepository.getFieldsById(null)).thenReturn(null);

        metadataService.getFieldsById(null);
    }

}
