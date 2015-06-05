package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class RelatedItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String displayLabel;
    private List<TitleInfo> titleInfo;
    private Name name;
    private String href;
    private RecordInfo recordInfo;
    private Identifier identifier;

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public String getDisplayLabel() {
        return displayLabel;
    }
    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    @XmlElement(name = "titleInfo", namespace="http://www.loc.gov/mods/v3")
    public List<TitleInfo> getTitleInfo() {
        return titleInfo;
    }
    public void setTitleInfo(List<TitleInfo> titleInfo) {
        this.titleInfo = titleInfo;
    }

    @XmlElement(name = "name", namespace="http://www.loc.gov/mods/v3")
    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }

    @XmlAttribute(name = "href", namespace="http://www.w3.org/1999/xlink")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @XmlElement(name = "recordInfo", namespace="http://www.loc.gov/mods/v3")
    public RecordInfo getRecordInfo() {
        return recordInfo;
    }
    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }

    @XmlElement(name = "identifier", namespace="http://www.loc.gov/mods/v3")
    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}