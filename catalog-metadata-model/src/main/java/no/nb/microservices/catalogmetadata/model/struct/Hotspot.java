package no.nb.microservices.catalogmetadata.model.struct;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Hotspot implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String hszId;
    private String hszParent;
    private Integer b;
    private Integer l;
    private Integer r;
    private Integer t;
    private Hs hs;

    @XmlAttribute(name="hsz_id")
    public String getHszId() {
        return hszId;
    }

    public void setHszId(String hszId) {
        this.hszId = hszId;
    }

    @XmlAttribute(name="hsz_parent")
    public String getHszParent() {
        return hszParent;
    }

    public void setHszParent(String hszParent) {
        this.hszParent = hszParent;
    }

    @XmlAttribute(name="b")
    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @XmlAttribute(name="l")
    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    @XmlAttribute(name="r")
    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    @XmlAttribute(name="t")
    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Hs getHs() {
        return hs;
    }

    @XmlElement
    public void setHs(Hs hs) {
        this.hs = hs;
    }
    
    public Integer getWidth() {
        return getR() - getL();
    }
    
    public Integer getHeight() {
        return getB() - getT();
    }

}
