package no.nb.microservices.catalogmetadata.test.model.fields;

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

    public static FieldsBuilder aDefaultMusic() {
        return new FieldsBuilder()
                .withMediaTypes("musikk")
                .isDigital(false);
    }

}
