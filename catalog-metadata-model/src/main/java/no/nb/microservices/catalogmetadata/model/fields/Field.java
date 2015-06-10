package no.nb.microservices.catalogmetadata.model.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author ronnymikalsen
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {

    private String name;
    private String value;
    
    @JsonCreator
    public Field() {
        super();
    }
    
    public Field(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
