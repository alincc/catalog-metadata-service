package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class Name implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;

    private List<Namepart> nameParts;
    private List<Role> role;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @XmlElement(name = "namePart", namespace = "http://www.loc.gov/mods/v3")
    public List<Namepart> getNameParts() {
        return nameParts;
    }

    public void setNameParts(List<Namepart> nameParts) {
        this.nameParts = nameParts;
    }

    @XmlElement(name = "role", namespace = "http://www.loc.gov/mods/v3")
    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

}

