package no.nb.microservices.catalogmetadata.test.model.fields;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.nb.microservices.catalogmetadata.test.exception.TestDataException;

public class TestFields {

    public static FieldsBuilder aDefaultBook() {
        return new FieldsBuilder()
            .withMediaTypes("bøker")
            .withContentClasses("restricted", "public")
            .isDigital(true);
    }

    public static String aDefaultBookJson() {
        return objectToJson(aDefaultBook().build());
    }
    
    public static FieldsBuilder aDefaultRadio() {
        return new FieldsBuilder()
                .withMediaTypes("radio")
                .withContentClasses("restricted")
                .isDigital(true);
    }

    public static FieldsBuilder aDefaultMusic() {
        return new FieldsBuilder()
                .withMediaTypes("musikk")
                .withContentClasses("restricted")
                .isDigital(false);
    }

    public static String aDefaultMusicJson() {
        return objectToJson(aDefaultMusic().build());
    }

    private static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new TestDataException(ex);
        }
    }


}
