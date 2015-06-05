package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Extension implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nbTypeOfResource;
    private StreamingInfo streamingInfo;
    private String accessOwner;
    private PublicationHistory publicationHistory;

    @XmlElement(name = "nbTypeOfResource", namespace = "http://www.nb.no/NBModsExtension/v1")
    public String getNbTypeOfResource() {
        return nbTypeOfResource;
    }

    public void setNbTypeOfResource(String nbTypeOfResource) {
        this.nbTypeOfResource = nbTypeOfResource;
    }

    @XmlElement(name = "streamingInfo", namespace = "http://www.nb.no/NBModsExtension/v1")
    public StreamingInfo getStreamingInfo() {
        return streamingInfo;
    }

    public void setStreamingInfo(StreamingInfo streamingInfo) {
        this.streamingInfo = streamingInfo;
    }

    @XmlElement(name = "accessOwner", namespace = "http://www.nb.no/NBModsExtension/v1")
    public String getAccessOwner() {
        return accessOwner;
    }

    public void setAccessOwner(String accessOwner) {
        this.accessOwner = accessOwner;
    }

    @XmlElement(name = "publicationHistory", namespace = "urn:ebu:metadata-schema:ebuCore_20090928")
    public PublicationHistory getPublicationHistory() {
        return publicationHistory;
    }

    public void setPublicationHistory(PublicationHistory publicationHistory) {
        this.publicationHistory = publicationHistory;
    }
}
