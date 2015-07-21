package no.nb.microservices.catalogmetadata.core.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
