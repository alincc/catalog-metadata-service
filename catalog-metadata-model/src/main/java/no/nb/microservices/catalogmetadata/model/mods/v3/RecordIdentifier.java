package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

public class RecordIdentifier implements Serializable {
    private static final long serialVersionUID = 1L;

    private String source;
    private String value;

    @XmlAttribute
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    @XmlValue
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
