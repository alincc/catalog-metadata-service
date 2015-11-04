package no.nb.microservices.catalogmetadata.model.mods.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    private String objectPart;
    private List<LanguageTerm> languageTerms;

    @XmlElement(name = "objectPart",namespace="http://www.loc.gov/mods/v3")
    public String getObjectPart() {
        return objectPart;
    }
    
    public void setObjectPart(String objectPart) {
        this.objectPart = objectPart;
    }
    
    @XmlElement(name = "languageTerm",namespace="http://www.loc.gov/mods/v3")
    public List<LanguageTerm> getLanguageTerm() {
        if (this.languageTerms == null) {
            this.languageTerms = new ArrayList<>();
        }
        return this.languageTerms;
    }

    public void setLanguageTerms(List<LanguageTerm> languageTerms) {
        this.languageTerms = languageTerms;
    }

}
