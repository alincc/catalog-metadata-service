package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class OriginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String publisher;
    private String dateIssued;
    private String edition;
    private String frequency;
    private Place place;
    private List<DateMods> dateIssuedList;
    private List<DateMods> dateCreated;
    private String issuance;

    @XmlElement(name = "publisher", namespace = "http://www.loc.gov/mods/v3")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @XmlElement(name = "dateIssued", namespace = "http://www.loc.gov/mods/v3")
    public List<DateMods> getDateIssuedList() {
        return dateIssuedList;
    }

    public void setDateIssuedList(List<DateMods> dateIssuedList) {
        this.dateIssuedList = dateIssuedList;
    }

    public String getDateIssued() {
        DateMods finalDateIssued = new DateMods();

        if(this.dateIssuedList != null){
            for (DateMods dateIssued : this.dateIssuedList) {
                if (dateIssued.getPoint() == null) {
                    if (dateIssued.getEncoding() != null && dateIssued.getEncoding().equalsIgnoreCase("marc")) {
                        finalDateIssued = dateIssued;
                        break;
                    }
                    else {
                        finalDateIssued = dateIssued;
                    }
                }
            }
        }
        return finalDateIssued.getValue();
    }

    public void setDateIssued(String dsystemateIssued) {
        this.dateIssued = dateIssued;
    }

    @XmlElement(name = "dateCreated", namespace = "http://www.loc.gov/mods/v3")
    public List<DateMods> getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(List<DateMods> dateCreated) {
        this.dateCreated = dateCreated;
    }

    @XmlElement(name = "place", namespace = "http://www.loc.gov/mods/v3")
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @XmlElement(name = "edition", namespace = "http://www.loc.gov/mods/v3")
    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @XmlElement(name = "frequency", namespace = "http://www.loc.gov/mods/v3")
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @XmlElement(name = "issuance", namespace = "http://www.loc.gov/mods/v3")
    public String getIssuance() {
        return issuance;
    }

    public void setIssuance(String issuance) {
        this.issuance = issuance;
    }
}
