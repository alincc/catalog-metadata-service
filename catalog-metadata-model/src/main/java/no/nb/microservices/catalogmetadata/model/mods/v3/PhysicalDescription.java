package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class PhysicalDescription implements Serializable {
    private static final long serialVersionUID = 1L;

    private String extent;

    @XmlElement(name = "extent", namespace = "http://www.loc.gov/mods/v3")
    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public String getStrippedExtent() {
        if (extent != null) return extent.replaceAll("[^0-9]", "");
        return null;
    }
}
