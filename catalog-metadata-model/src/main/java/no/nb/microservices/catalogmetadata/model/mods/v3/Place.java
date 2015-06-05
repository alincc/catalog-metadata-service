package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Place implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String placeTerm;

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "placeTerm", namespace="http://www.loc.gov/mods/v3")
    public String getPlaceTerm() {
        return placeTerm;
    }

    public void setPlaceTerm(String placeTerm) {
        this.placeTerm = placeTerm;
    }
}