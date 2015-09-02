package no.nb.microservices.catalogmetadata.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.service.MetadataService;
import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.exception.ModsNotFoundException;
import no.nb.microservices.catalogmetadata.model.fields.FieldResource;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;

@RunWith(MockitoJUnitRunner.class)
public class MetadataControllerTest {
    @Mock
    private MetadataService metadataService;
    private MetadataController metadataController;
    private MockHttpServletRequest request;

    @Before
    public void setup() {
        metadataController = new MetadataController(metadataService);
        request = new MockHttpServletRequest("GET", "/v1/id1");
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);
    }
    
    @After
    public void cleanUp() {
        RequestContextHolder.resetRequestAttributes();
    }
    
    @Test
    public void whenModsIsFoundThenResponseShouldBeNotNull() {
        when(metadataService.getModsById("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(new Mods());

        ResponseEntity<Mods> m1 = metadataController.getMods("c06c5cbe2f82113e7b4757dbb14f8676");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());
    }

    @Test(expected = ModsNotFoundException.class)
    public void whenModsIsNotFoundThenResponseShouldBeNull () {
        when(metadataService.getModsById("e7b4757dbb14f8676c06c5cbe2f82113")).thenThrow(new ModsNotFoundException(""));

        metadataController.getMods("e7b4757dbb14f8676c06c5cbe2f82113");
    }

    @Test
    public void testGetMarcxml() {
        when(metadataService.getMarcxmlById("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(new RecordType());
        when(metadataService.getMarcxmlById("e7b4757dbb14f8676c06c5cbe2f82113")).thenReturn(null);

        ResponseEntity<RecordType> m1 = metadataController.getMarcxml("c06c5cbe2f82113e7b4757dbb14f8676");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());

    }

    @Test
    public void whenFieldsIsFoundThenResponseShouldBeNotNull() throws Exception {
        Fields fields = new Fields("id1");
        request = new MockHttpServletRequest("GET", "/c06c5cbe2f82113e7b4757dbb14f8676/fields");

        when(metadataService.getFieldsById("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(fields);

        ResponseEntity<FieldResource> m1 = metadataController.getFields("c06c5cbe2f82113e7b4757dbb14f8676");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());
    }

    @Test(expected = FieldNotFoundException.class)
    public void whenFieldsIsNotFoundThenResponseShouldBeNull() throws Exception {
        when(metadataService.getFieldsById("e7b4757dbb14f8676c06c5cbe2f82113")).thenThrow(new FieldNotFoundException(""));

        metadataController.getFields("e7b4757dbb14f8676c06c5cbe2f82113");

    }

}

