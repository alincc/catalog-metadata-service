package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "mods", namespace="http://www.loc.gov/mods/v3")
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

    @XmlElement(name = "originInfo", namespace="http://www.loc.gov/mods/v3")
    public OriginInfo getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(OriginInfo originInfo) {
        this.originInfo = originInfo;
    }

    @XmlElement(name = "physicalDescription", namespace="http://www.loc.gov/mods/v3")
    public PhysicalDescription getPhysicalDescription() {
        return physicalDescription;
    }
    public void setPhysicalDescription(PhysicalDescription physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    @XmlElement(name = "abstract", namespace="http://www.loc.gov/mods/v3")
    public List<Abstract> getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(List<Abstract> abstracts) {
        this.abstracts = abstracts;
    }

    @XmlElement(name = "note", namespace="http://www.loc.gov/mods/v3")
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

    @XmlElement(name = "tableOfContents", namespace="http://www.loc.gov/mods/v3")
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

    @XmlElement(name = "identifier", namespace="http://www.loc.gov/mods/v3")
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

    /**
     * @return PlaceTerm : Publisher, DateIssued
     */
    public String getPublished(){
        String published = "";

        if(originInfo != null){
            if(originInfo.getPlace() != null && !originInfo.getPlace().getPlaceTerm().isEmpty()){
                published += originInfo.getPlace().getPlaceTerm();

                if(originInfo.getPublisher() != null && !originInfo.getPublisher().isEmpty()){
                    published += " : ";
                    published += originInfo.getPublisher();
                }

                if(originInfo.getDateIssued() != null && !originInfo.getDateIssued().isEmpty()){
                    published += ", ";
                }else{
                    return published;
                }
            }

            if(originInfo.getDateIssued() != null && !originInfo.getDateIssued().isEmpty()){
                published += originInfo.getDateIssued();
            }
        }

        if(published.length() > 0){
            return published;
        }else{
            return null;
        }
    }

    /**
     * @return Notes or Abstract
     */
    public String getOtherInformation(){
        String notes = "";

//		if(this.notes != null && this.notes.size() > 0){
//			// Note
//			for(Note note : this.notes){
//				if(note.getType() != null && note.getType().equalsIgnoreCase("statement of responsibility") == false){
//					notes += note;
//				}
//			}
//		}else{
        // Abstract
        if(abstracts != null){
            for(Abstract abstractValue : this.abstracts){
                notes += abstractValue.getValue();
            }
        }
//		}

        if(notes.length() > 0){
            return notes;
        }else{
            return null;
        }
    }

    /**
     * @return
     * [geographic][Mo, Rana, NordNorge, ..]
     * [coordinates][.., .., .., ..]
     * [scale][.., .., .., .. ]
     */
    public HashMap<String, ArrayList<String>> getGeographicMap(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        if(subjects != null){
            for(Subject subject : subjects){
                if(subject.getGeographic() != null){
                    for (Geographic geographic : subject.getGeographic()) {
                        putItemInMap(map, "geographic", geographic.getValue());
                    }
                }

                if(subject.getCartographics() != null){
                    putItemInMap(map, "coordinates", subject.getCartographics().getCoordinates());

                    if(subject.getCartographics().getScale() != null){
                        putItemInMap(map, "scale", subject.getCartographics().getScale());
                    }
                }
            }
        }
        return map;
    }

    /**
     * @return
     * [//isbn][.., .., .., ..]
     * [issn][.., .., .., ..]
     * [ismn][.., .., .., .. ]
     * [isrc][.., .., .., .. ]
     */
    public HashMap<String, ArrayList<Identifier>> getIdentifierMap(){
        HashMap<String, ArrayList<Identifier>> map = new HashMap<String, ArrayList<Identifier>>();

        if(identifiers == null){
            return map;
        }

        for(Identifier identifier : identifiers){
            if(identifier.getType() != null){
                putItemInMap(map, identifier.getType(), identifier);
            }
        }

        return map;
    }

    /**
     * @return
     * [udc][.., .., .., ..]
     * [ddc][.., .., .., ..]
     * [lcc][.., .., .., .. ]
     * [nlm][.., .., .., .. ]
     * [sudocs][.., .., .., .. ]
     * [candocs][.., .., .., .. ]
     * [other][.., .., .., .. ]
     */
    public HashMap<String, ArrayList<Classification>> getClassificationMap(){
        HashMap<String, ArrayList<Classification>> map = new HashMap<String, ArrayList<Classification>>();

        if(classifications == null){
            return map;
        }

        for(Classification classification : classifications){
            if(classification.getAuthority() != null){
                if(classification.getAuthority().equalsIgnoreCase("udc")){
                    putItemInMap(map, "udc", classification);
                }else if(classification.getAuthority().equalsIgnoreCase("ddc")){
                    putItemInMap(map, "ddc", classification);
                }else if(classification.getAuthority().equalsIgnoreCase("lcc")){
                    putItemInMap(map, "lcc", classification);
                }else if(classification.getAuthority().equalsIgnoreCase("nlm")){
                    putItemInMap(map, "nlm", classification);
                }else if(classification.getAuthority().equalsIgnoreCase("sudocs")){
                    putItemInMap(map, "sudocs", classification);
                }else if(classification.getAuthority().equalsIgnoreCase("candocs")){
                    putItemInMap(map, "candocs", classification);
                }
                else{
                    putItemInMap(map, "other", classification);
                }
            }
            else{
                putItemInMap(map, "other", classification);
            }
        }

        return map;
    }

    /**
     * @return
     * [relatedItem_preceding][.., .., .., ..]
     * [series][.., .., .., ..]
     * [constituent][.., .., .., .. ]
     * [relatedResource][.., .., .., .. ]
     */
    public HashMap<String, ArrayList<RelatedItem>> getRelatedItemsMap(){
        HashMap<String, ArrayList<RelatedItem>> map = new HashMap<String, ArrayList<RelatedItem>>();

        if(relatedItems == null){
            return map;
        }

        for(RelatedItem relatedItem : relatedItems){

            if(relatedItem.getTitleInfo() != null){
                if(relatedItem.getTitleInfo() != null && relatedItem.getTitleInfo().size() > 0){
                    if(relatedItem.getType() != null && relatedItem.getType().equalsIgnoreCase("relatedItem_preceding")){
                        putItemInMap(map, "relatedItem_preceding", relatedItem);
                    }else if(relatedItem.getType() != null && relatedItem.getType().equalsIgnoreCase("series")){
                        putItemInMap(map, "series", relatedItem);
                    }else if(relatedItem.getType() != null && relatedItem.getType().equalsIgnoreCase("constituent")){
                        putItemInMap(map, "constituent", relatedItem);
                    }else{
                        if(relatedItem.getDisplayLabel() != null){
                            putItemInMap(map, "relatedResource", relatedItem);
                        }
                    }
                }
            }
        }
        return map;
    }

    @XmlElement(name = "extension", namespace = "http://www.loc.gov/mods/v3")
    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    private <T> void putItemInMap(HashMap<String, ArrayList<T>> map, String key, T value){
        if(map.containsKey(key)){
            map.get(key).add(value);
        }else{
            ArrayList<T> temp = new ArrayList<T>();
            temp.add(value);
            map.put(key, temp);
        }
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
