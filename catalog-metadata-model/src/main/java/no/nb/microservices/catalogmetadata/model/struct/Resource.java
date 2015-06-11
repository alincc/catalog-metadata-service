package no.nb.microservices.catalogmetadata.model.struct;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String href;
    private String originalName;
    private int height;
    private int width;

    @XmlAttribute(name = "href",namespace="http://www.w3.org/1999/xlink")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @XmlAttribute
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @XmlAttribute(name = "HEIGHT")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @XmlAttribute(name = "WIDTH")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}