package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class Id implements Serializable {
    private static final long serialVersionUID = 1L;

    private String xmlValue;

    /**
     *
     * @return String
     */
    @XmlValue
    public String getXmlValue() {
        return this.xmlValue;
    }

    /**
     *
     * @param name
     */
    public void setXmlValue(String xmlValue) {
        this.xmlValue = xmlValue;
    }

}