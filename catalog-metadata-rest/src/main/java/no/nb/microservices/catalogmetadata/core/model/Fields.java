package no.nb.microservices.catalogmetadata.core.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Fields implements Identifiable<String> {
    private String id;
    private String fieldsAsJson;
    private String contentClasses = new String();
    private String metadataClasses = new String();

    public Fields(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getFieldsAsJson() {
        return fieldsAsJson;
    }

    public void setFieldsAsJson(String fieldsAsJson) {
        this.fieldsAsJson = fieldsAsJson;
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(contentClasses, String[].class));
        } catch (IOException e) {
            // nothing to do
        }
        return new ArrayList<>();
    }
    
    public List<String> getMetadataClassesAsList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(metadataClasses, String[].class));
        } catch (IOException e) {
            // nothing to do
        }
        return new ArrayList<>();
    }

}
