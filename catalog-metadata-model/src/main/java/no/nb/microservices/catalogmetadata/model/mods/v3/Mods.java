package no.nb.microservices.catalogmetadata.model.mods.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mods", namespace = "http://www.loc.gov/mods/v3")
public class Mods implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<TitleInfo> titleInfos;
    private OriginInfo originInfo;
    private PhysicalDescription physicalDescription;
    private List<Abstract> abstracts;
    private List<Note> notes;
    private List<Identifier> identifiers;
    private Location location;
    private Extension extension;
    private List<Subject> subjects;
    private List<Classification> classifications;
    private List<RelatedItem> relatedItems;
    private List<TableOfContents> tableOfContents;
    private List<Name> names;
    private RecordInfo recordInfo;
    private String typeOfResource;
    private String genre;
    private List<Language> languages;

    @XmlElement(name = "originInfo", namespace = "http://www.loc.gov/mods/v3")
    public OriginInfo getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(OriginInfo originInfo) {
        this.originInfo = originInfo;
    }

    @XmlElement(name = "physicalDescription", namespace = "http://www.loc.gov/mods/v3")
    public PhysicalDescription getPhysicalDescription() {
        return physicalDescription;
    }

    public void setPhysicalDescription(PhysicalDescription physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    @XmlElement(name = "abstract", namespace = "http://www.loc.gov/mods/v3")
    public List<Abstract> getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(List<Abstract> abstracts) {
        this.abstracts = abstracts;
    }

    @XmlElement(name = "note", namespace = "http://www.loc.gov/mods/v3")
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @XmlElement(name = "location", namespace = "http://www.loc.gov/mods/v3")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @XmlElement(name = "relatedItem", namespace = "http://www.loc.gov/mods/v3")
    public List<RelatedItem> getRelatedItems() {
        return relatedItems;
    }

    public void setRelatedItems(List<RelatedItem> relatedItems) {
        this.relatedItems = relatedItems;
    }

    @XmlElement(name = "tableOfContents", namespace = "http://www.loc.gov/mods/v3")
    public List<TableOfContents> getTableOfContents() {
        return tableOfContents;
    }

    public void setTableOfContents(List<TableOfContents> tableOfContents) {
        this.tableOfContents = tableOfContents;
    }

    @XmlElement(name = "classification", namespace = "http://www.loc.gov/mods/v3")
    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    @XmlElement(name = "identifier", namespace = "http://www.loc.gov/mods/v3")
    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    @XmlElement(name = "subject", namespace = "http://www.loc.gov/mods/v3")
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @XmlElement(name = "name", namespace = "http://www.loc.gov/mods/v3")
    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    @XmlElement(name = "language", namespace = "http://www.loc.gov/mods/v3")
    public List<Language> getLanguages() {
        return languages; 
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    /**
     * @return PlaceTerm : Publisher, DateIssued
     */
    public String getPublished() {
        String published = "";

        if (originInfo != null && originInfo.getPlace() != null && !originInfo.getPlace().getPlaceTerm().isEmpty()) {
            published += originInfo.getPlace().getPlaceTerm();
            published += getPublisher(originInfo);
            published += getDateIssued(originInfo);
        }

        if (originInfo != null && originInfo.getDateIssued() != null && !originInfo.getDateIssued().isEmpty()) {
            published += originInfo.getDateIssued();
        }

        if (published.length() > 0) {
            return published;
        } else {
            return null;
        }
    }

    private String getPublisher(OriginInfo info) {
        String publisher = "";
        if (info.getPublisher() != null && !info.getPublisher().isEmpty()) {
            publisher += " : ";
            publisher += info.getPublisher();
        }
        return publisher;
    }

    private String getDateIssued(OriginInfo info) {
        String issued = "";
        if (info.getDateIssued() != null && !info.getDateIssued().isEmpty()) {
            issued += ", ";
        }
        return issued;
    }

    /**
     * @return Notes or Abstract
     */
    public String getOtherInformation() {
        String information = "";
        if (abstracts != null) {
            for (Abstract abstractValue : this.abstracts) {
                information += abstractValue.getValue();
            }
        }
        if (information.length() > 0) {
            return information;
        } else {
            return null;
        }
    }

    @XmlElement(name = "extension", namespace = "http://www.loc.gov/mods/v3")
    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    @XmlElement(name = "recordInfo", namespace = "http://www.loc.gov/mods/v3")
    public RecordInfo getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }

    @XmlElement(name = "titleInfo", namespace = "http://www.loc.gov/mods/v3")
    public List<TitleInfo> getTitleInfos() {
        return titleInfos;
    }

    public void setTitleInfos(List<TitleInfo> titleInfos) {
        this.titleInfos = titleInfos;
    }

    @XmlElement(name = "typeOfResource", namespace = "http://www.loc.gov/mods/v3")
    public String getTypeOfResource() {
        return typeOfResource;
    }

    public void setTypeOfResource(String typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    @XmlElement(name = "genre", namespace = "http://www.loc.gov/mods/v3")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
