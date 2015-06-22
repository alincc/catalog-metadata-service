package no.nb.microservices.catalogmetadata.core.model;

public class FieldsModel {
    private String fields;
    private String contentClasses;
    private String metadataClasses;

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
}
