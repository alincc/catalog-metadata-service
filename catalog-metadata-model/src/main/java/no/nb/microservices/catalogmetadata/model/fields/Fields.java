package no.nb.microservices.catalogmetadata.model.fields;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author ronnymikalsen
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {

    private boolean digital;
    private List<String> contentClasses = new ArrayList<>();
    private List<String> metadataClasses = new ArrayList<>();
    
    @JsonCreator
    public Fields() {
        super();
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public List<String> getContentClasses() {
        return contentClasses;
    }

    public void setContentClasses(List<String> contentClasses) {
        this.contentClasses = contentClasses;
    }

    public List<String> getMetadataClasses() {
        return metadataClasses;
    }

    public void setMetadataClasses(List<String> metadataClasses) {
        this.metadataClasses = metadataClasses;
    }
}