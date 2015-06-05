package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> roleTerms;

    @XmlElement(name = "roleTerm",namespace="http://www.loc.gov/mods/v3")
    public List<String> getRoleTerms() {
        return roleTerms;
    }

    public void setRoleTerms(List<String> roleTerms) {
        this.roleTerms = roleTerms;
    }
}