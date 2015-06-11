package no.nb.microservices.catalogmetadata.rest;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.service.IMetadataService;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.exception.ModsNotFoundException;
import no.nb.microservices.catalogmetadata.exception.StructNotFoundException;
import no.nb.microservices.catalogmetadata.model.fields.Field;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;

import no.nb.microservices.catalogmetadata.model.struct.StructMap;
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
    public void whenModsIsFoundThenResponseShouldBeNotNull() {
        when(metadataService.getModsById("bfa3324befaa4518b581125fd701900e")).thenReturn(new Mods());

        ResponseEntity<Mods> m1 = metadataController.getMods("bfa3324befaa4518b581125fd701900e");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());
    }

    @Test(expected = ModsNotFoundException.class)
    public void whenModsIsNotFoundThenResponseShouldBeNull() {
        when(metadataService.getModsById("e7b4757dbb14f8676c06c5cbe2f82113")).thenThrow(new ModsNotFoundException(""));

        metadataController.getMods("e7b4757dbb14f8676c06c5cbe2f82113");
    }

    @Test
    public void whenMarcxmlIsFoundThenResponseShouldBeNotNull() {
        when(metadataService.getMarcxmlById("bfa3324befaa4518b581125fd701900e")).thenReturn(new RecordType());

        ResponseEntity<RecordType> m1 = metadataController.getMarcxml("bfa3324befaa4518b581125fd701900e");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());
    }

    @Test
    public void whenStructIsFoundThenResponseShouldBeNotNull() throws Exception {
        when(metadataService.getStructById("bfa3324befaa4518b581125fd701900e")).thenReturn(new StructMap());

        ResponseEntity<StructMap> response = metadataController.getStructure("bfa3324befaa4518b581125fd701900e");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test(expected = StructNotFoundException.class)
    public void whenStructIsNotFoundThenResponseShouldBeNull() throws Exception {
        when(metadataService.getStructById("bfa3324befaa4518b581125fd701900e")).thenThrow(new StructNotFoundException(""));

        metadataController.getStructure("bfa3324befaa4518b581125fd701900e");
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

