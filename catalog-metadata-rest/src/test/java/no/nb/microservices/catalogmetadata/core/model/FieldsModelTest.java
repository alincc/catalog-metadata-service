package no.nb.microservices.catalogmetadata.core.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class FieldsModelTest {

    @Test
    public void getContentClassesAsList() {
        FieldsModel fields = new FieldsModel();
        fields.setContentClasses("[\"restricted\", \"jp2\", \"public\"]");
        List<String> contentClasses = fields.getContentClassesAsList();

        assertEquals("Length should be 3", 3, contentClasses.size());
        assertTrue("List should contain \"restricted\"", contentClasses.contains("restricted"));
    }

    @Test
    public void getMetadataClassesAsList() {
        FieldsModel fields = new FieldsModel();
        fields.setMetadataClasses("[\"public\"]");
        List<String> metadataClasses = fields.getMetadataClassesAsList();

        assertEquals("Length should be 1", 1, metadataClasses.size());
        assertTrue("List should contain \"public\"", metadataClasses.contains("public"));
    }

}
