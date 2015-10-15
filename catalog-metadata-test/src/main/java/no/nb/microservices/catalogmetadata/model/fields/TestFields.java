package no.nb.microservices.catalogmetadata.model.fields;

public class TestFields {

    public static FieldsBuilder aDefaultBookFields() {
        return new FieldsBuilder()
            .withMediaTypes("bøker")
            .isDigital(true);
    }

    public static FieldsBuilder aDefaultReadioFields() {
        return new FieldsBuilder()
                .withMediaTypes("radio")
                .isDigital(true);
    }
    
}
