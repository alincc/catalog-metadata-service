package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class Url implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;
    private String access;

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

}