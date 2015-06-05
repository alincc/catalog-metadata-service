package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Cartographics implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scale;
    private String coordinates;

    @XmlElement(name = "scale", namespace = "http://www.loc.gov/mods/v3")
    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * @TODO : Sjekk om hvordan data er lagret i mods.
     */
    @XmlElement(name = "coordinates", namespace = "http://www.loc.gov/mods/v3")
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}