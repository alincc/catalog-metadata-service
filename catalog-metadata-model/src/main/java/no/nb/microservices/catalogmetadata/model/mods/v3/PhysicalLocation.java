package no.nb.microservices.catalogmetadata.model.mods.v3;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class PhysicalLocation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String authority;
    private String value;

    @XmlAttribute
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
