package no.nb.microservices.catalogmetadata.model.struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Div implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String type;
    private String order;
    private String orderLabel;
    private Resource resource;
    private List<Hotspot> hotspots;

    public Div() {
        super();
    }
    
    public Div(String id) {
        super();
        this.id = id;
    }

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name = "ORDER")
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @XmlAttribute(name = "ORDERLABEL")
    public String getOrderLabel() {
        return orderLabel;
    }

    public void setOrderLabel(String orderLabel) {
        this.orderLabel = orderLabel;
    }

    @XmlElement
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @XmlElement(name="hotspot")
    @XmlElementWrapper(name="hotspots")
    public List<Hotspot> getHotspots() {
        if (hotspots == null) {
            hotspots = new ArrayList<>();
        }
        return hotspots;
    }

    public void setHotspots(List<Hotspot> hotspots) {
        this.hotspots = hotspots;
    }



}