package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Url> urls;
    private List<PhysicalLocation> physicalLocations; 

    @XmlElement(name = "url", namespace = "http://www.loc.gov/mods/v3")
    public List<Url> getUrls() {
        return this.urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getThumbnailUrl() {
        return formattedUrls(getUrlsByAccess("preview"));
    }

    public String getRawObjectUrls() {
        return formattedUrls(getUrlsByAccess("raw object"));
    }

    private List<Url> getUrlsByAccess(String access) {
        List<Url> urlsByAccess = new ArrayList<Url>();
        if (this.urls != null) {
            return urlsByAccess;
        }
        for (Url url : this.urls) {
            if (url.getAccess() != null && url.getAccess().equalsIgnoreCase(access)) {
                urlsByAccess.add(url);
            }
        }
        if (urlsByAccess.isEmpty()) {
            for (Url url : this.urls) {
                if (url.getAccess() == null) {
                    urlsByAccess.add(url);
                }
            }
        }
        return urlsByAccess;
    }

    String formattedUrls(List<Url> urls) {
        String formatted = "";
        if (urls != null) {
            for (Url url : urls) {
                formatted += url.getValue().replaceAll("(\\r|\\n)", "") + ";";
            }
        }
        return formatted;
    }

    @XmlElement(name = "physicalLocation", namespace = "http://www.loc.gov/mods/v3")
    public List<PhysicalLocation> getPhysicalLocations() {
        return physicalLocations;
    }

    public void setPhysicalLocations(List<PhysicalLocation> physicalLocations) {
        this.physicalLocations = physicalLocations;
    }
}
