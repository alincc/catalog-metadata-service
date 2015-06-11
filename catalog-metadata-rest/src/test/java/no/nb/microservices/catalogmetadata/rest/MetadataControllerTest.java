package no.nb.microservices.catalogmetadata.rest;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.service.IMetadataService;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.model.fields.Field;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author jimarthurnilsen
 * @author ronnymikalsen
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MetadataControllerTest {
    @Mock
    private IMetadataService metadataService;

    private MetadataController metadataController;

    @Before
    public void setup() {
        metadataController = new MetadataController(metadataService);
    }

    @Test
    public void testgetMods() {
        when(metadataService.getModsById("bfa3324befaa4518b581125fd701900e")).thenReturn(new Mods());
        when(metadataService.getModsById("e7b4757dbb14f8676c06c5cbe2f82113")).thenReturn(null);

        ResponseEntity<Mods> m1 = metadataController.getMods("bfa3324befaa4518b581125fd701900e");
        ResponseEntity<Mods> m2 = metadataController.getMods("e7b4757dbb14f8676c06c5cbe2f82113");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());

        assertEquals(HttpStatus.NOT_FOUND, m2.getStatusCode());
        assertNull(m2.getBody());
    }

    @Test
    public void testGetMarcxml() {
        when(metadataService.getMarcxmlById("bfa3324befaa4518b581125fd701900e")).thenReturn(new RecordType());
        when(metadataService.getMarcxmlById("e7b4757dbb14f8676c06c5cbe2f82113")).thenReturn(null);

        ResponseEntity<RecordType> m1 = metadataController.getMarcxml("bfa3324befaa4518b581125fd701900e");
        ResponseEntity<RecordType> m2 = metadataController.getMarcxml("e7b4757dbb14f8676c06c5cbe2f82113");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());

        assertEquals(HttpStatus.NOT_FOUND, m2.getStatusCode());
        assertNull(m2.getBody());
    }

    @Test
    public void whenFieldsIsFoundThenResponseShouldBeNotNull() throws Exception {
        List<Field> fields = Arrays.asList(new Field("title", "Verden på lørdag"), new Field("title", "Verden på søndag"));

        when(metadataService.getFieldsById("bfa3324befaa4518b581125fd701900e")).thenReturn(fields);

        ResponseEntity<List<Field>> m1 = metadataController.getFields("bfa3324befaa4518b581125fd701900e");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());
        assertEquals("Size of body should be 2", 2, m1.getBody().size());
    }

    @Test(expected = FieldNotFoundException.class)
    public void whenFieldsIsNotFoundThenResponseShouldBeNull() throws Exception {
        when(metadataService.getFieldsById("e7b4757dbb14f8676c06c5cbe2f82113")).thenThrow(new FieldNotFoundException(""));

        metadataController.getFields("e7b4757dbb14f8676c06c5cbe2f82113");

    }

}

