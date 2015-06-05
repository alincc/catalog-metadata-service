package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class Namepart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String value;

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




}
