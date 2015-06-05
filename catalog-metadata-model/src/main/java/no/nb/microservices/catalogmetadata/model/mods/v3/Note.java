package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class Note implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String value;
    private String displayLabel;

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlValue
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public String getDisplayLabel() {
        return displayLabel;
    }
    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

}
