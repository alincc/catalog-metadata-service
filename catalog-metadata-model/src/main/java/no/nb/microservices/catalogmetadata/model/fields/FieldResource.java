package no.nb.microservices.catalogmetadata.model.fields;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldResource extends ResourceSupport {

    private String title;
    private List<String> urns;
    private boolean digital;
    private List<String> mediaTypes;
    private List<String> contentClasses = new ArrayList<>();
    private List<String> metadataClasses = new ArrayList<>();
    
    @JsonCreator
    public FieldResource() {
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

    public String getTitle() {
        return title;
    }

    public List<String> getUrns() {
        return urns;
    }

    public void setUrns(List<String> urns) {
        this.urns = urns;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    public void setMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }
}