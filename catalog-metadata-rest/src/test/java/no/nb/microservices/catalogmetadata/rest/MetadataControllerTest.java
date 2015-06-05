package no.nb.microservices.catalogmetadata.rest;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.service.IMetadataService;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

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
        when(metadataService.getMods("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(new Mods());
        when(metadataService.getMods("e7b4757dbb14f8676c06c5cbe2f82113")).thenReturn(null);

        ResponseEntity<Mods> m1 = metadataController.getMods("c06c5cbe2f82113e7b4757dbb14f8676");
        ResponseEntity<Mods> m2 = metadataController.getMods("e7b4757dbb14f8676c06c5cbe2f82113");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());

        assertEquals(HttpStatus.NOT_FOUND, m2.getStatusCode());
        assertNull(m2.getBody());
    }

    @Test
    public void testGetMarcxml() {
        when(metadataService.getMarcxml("c06c5cbe2f82113e7b4757dbb14f8676")).thenReturn(new RecordType());
        when(metadataService.getMarcxml("e7b4757dbb14f8676c06c5cbe2f82113")).thenReturn(null);

        ResponseEntity<RecordType> m1 = metadataController.getMarcxml("c06c5cbe2f82113e7b4757dbb14f8676");
        ResponseEntity<RecordType> m2 = metadataController.getMarcxml("e7b4757dbb14f8676c06c5cbe2f82113");
        assertEquals(HttpStatus.OK,m1.getStatusCode());
        assertNotNull(m1.getBody());

        assertEquals(HttpStatus.NOT_FOUND, m2.getStatusCode());
        assertNull(m2.getBody());
    }
}
