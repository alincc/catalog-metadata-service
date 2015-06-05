package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class TitleInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String partName;
    private String displayLabel;
    private String type;
    private String partNumber;
    private String subTitle;
    private String nonSort;

    @XmlElement(name = "partName", namespace="http://www.loc.gov/mods/v3")
    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @XmlAttribute
    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "title", namespace="http://www.loc.gov/mods/v3")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "partNumber", namespace="http://www.loc.gov/mods/v3")
    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    @XmlElement(name = "subTitle", namespace="http://www.loc.gov/mods/v3")
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @XmlElement(name = "nonSort", namespace="http://www.loc.gov/mods/v3")
    public String getNonSort() {
        return nonSort;
    }

    public void setNonSort(String nonSort) {
        this.nonSort = nonSort;
    }
}