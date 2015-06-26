package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class DateMods implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;
    private String encoding;
    private String point;
    private String keyDate;

    @XmlValue
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    }

    @XmlAttribute
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @XmlAttribute
    public String getKeyDate() {
        return keyDate;
    }

    public void setKeyDate(String keyDate) {
        this.keyDate = keyDate;
    }
}
