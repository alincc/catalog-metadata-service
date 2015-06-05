package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class StreamingInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Offset offset;
    private Extent extent;
    private Identifier identifier;

    @XmlElement(name = "extent", namespace = "http://www.nb.no/NBModsExtension/v1")
    public Extent getExtent() {
        return extent;
    }

    public void setExtent(Extent extent) {
        this.extent = extent;
    }

    @XmlElement(name = "identifier", namespace = "http://www.nb.no/NBModsExtension/v1")
    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @XmlElement(name = "offset", namespace = "http://www.nb.no/NBModsExtension/v1")
    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

}