package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class Extent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
