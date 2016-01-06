package no.nb.microservices.catalogmetadata.model.struct;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Hs implements Serializable {
    private static final long serialVersionUID = 1L;

    private String hsId;
    private String value;

    @XmlAttribute(name="hs_id")
    public String getHsId() {
        return hsId;
    }

    public void setHsId(String hsId) {
        this.hsId = hsId;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
