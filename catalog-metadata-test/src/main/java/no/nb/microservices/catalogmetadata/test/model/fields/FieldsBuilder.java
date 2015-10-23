package no.nb.microservices.catalogmetadata.test.model.fields;

import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.fields.FieldResource;

public class FieldsBuilder {
    private List<String> mediaTypes;
    private List<String> contentClasses;
    private boolean isDigital;

    public FieldsBuilder withMediaTypes(String... mediatypes) {
        this.mediaTypes = Arrays.asList(mediatypes);
        return this;
    }

    public FieldsBuilder withContentClasses(String... contentClasses) {
        this.contentClasses = Arrays.asList(contentClasses);
        return this;
    }
    
    public FieldsBuilder isDigital(boolean isDigital) {
        this.isDigital = isDigital;
        return this;
    }

    public FieldResource build() {
        FieldResource fieldResource = new FieldResource();
        fieldResource.setMediaTypes(mediaTypes);
        fieldResource.setContentClasses(contentClasses);
        fieldResource.setDigital(isDigital);
        return fieldResource;
    }

}
