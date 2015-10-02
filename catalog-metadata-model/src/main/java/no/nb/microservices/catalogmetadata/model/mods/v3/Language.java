package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<LanguageTerm> languageTerms;

    @XmlElement(name = "languageTerm",namespace="http://www.loc.gov/mods/v3")
    public List<LanguageTerm> getLanguageTerm() { return this.languageTerms; }

    public void setLanguageTerms(List<LanguageTerm> languageTerms) { this.languageTerms = languageTerms; }

}
