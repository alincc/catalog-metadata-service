package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

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
    public List<LanguageTerm> getLanguageTerm() { return this.languageTerms; }

    public void setLanguageTerms(List<LanguageTerm> languageTerms) { this.languageTerms = languageTerms; }

}
