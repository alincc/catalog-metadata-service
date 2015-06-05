package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class PublicationHistory implements Serializable {
    private String firstTransmissionChannel;

    @XmlElement(name = "firstTransmissionChannel", namespace = "urn:ebu:metadata-schema:ebuCore_20090928")
    public String getFirstTransmissionChannel() {
        return firstTransmissionChannel;
    }

    public void setFirstTransmissionChannel(String firstTransmissionChannel) {
        this.firstTransmissionChannel = firstTransmissionChannel;
    }
}