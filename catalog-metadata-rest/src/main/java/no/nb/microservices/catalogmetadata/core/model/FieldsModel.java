package no.nb.microservices.catalogmetadata.core.model;

import java.util.Arrays;
import java.util.List;

public class FieldsModel {
    private String fields;
    private String contentClasses = new String();
    private String metadataClasses = new String();

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getContentClasses() {
        return contentClasses;
    }

    public void setContentClasses(String contentClasses) {
        this.contentClasses = contentClasses;
    }

    public String getMetadataClasses() {
        return metadataClasses;
    }

    public void setMetadataClasses(String metadataClasses) {
        this.metadataClasses = metadataClasses;
    }
    
    public List<String> getContentClassesAsList() {
        return Arrays.asList(getContentClasses().split(","));
    }
    
    public List<String> getMetadataClassesAsList() {
        return Arrays.asList(getMetadataClasses().split(","));
    }

}
