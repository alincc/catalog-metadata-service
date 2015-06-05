package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class RecordInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recordContentSource;
    private RecordIdentifier recordIdentifier;

    @XmlElement(name = "recordContentSource", namespace = "http://www.loc.gov/mods/v3")
    public String getRecordContentSource() {
        return recordContentSource;
    }

    public void setRecordContentSource(String recordContentSource) {
        this.recordContentSource = recordContentSource;
    }

    @XmlElement(name = "recordIdentifier", namespace = "http://www.loc.gov/mods/v3")
    public RecordIdentifier getRecordIdentifier() {
        return recordIdentifier;
    }

    public void setRecordIdentifier(RecordIdentifier recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }
}