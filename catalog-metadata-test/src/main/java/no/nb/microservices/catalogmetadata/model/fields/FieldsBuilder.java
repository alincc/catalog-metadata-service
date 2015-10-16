package no.nb.microservices.catalogmetadata.model.fields;

import java.util.Arrays;
import java.util.List;

public class FieldsBuilder {
    private List<String> mediaTypes;
    private boolean isDigital;

    public FieldsBuilder withMediaTypes(String... mediatypes) {
        this.mediaTypes = Arrays.asList(mediatypes);
        return this;
    }

    public FieldsBuilder isDigital(boolean isDigital) {
        this.isDigital = isDigital;
        return this;
    }

    public FieldResource build() {
        FieldResource fieldResource = new FieldResource();
        fieldResource.setDigital(isDigital);
        fieldResource.setMediaTypes(mediaTypes);
        return fieldResource;
    }

}
