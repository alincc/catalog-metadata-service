package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    private String languageTerm;

    @XmlElement(name = "languageTerm",namespace="http://www.loc.gov/mods/v3")
    public String getLanguageTerm() {
        return languageTerm;
    }

    public void setLanguageTerm(String languageTerm) {
        this.languageTerm = languageTerm;
    }

}
