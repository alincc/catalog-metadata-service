package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.Name;
import no.nb.microservices.catalogmetadata.model.mods.v3.Namepart;
import no.nb.microservices.catalogmetadata.model.mods.v3.Role;
import no.nb.microservices.catalogmetadata.model.mods.v3.RoleTerm;

public class NameBuilder {
    private String type;
    private List<Namepart> nameParts;
    private List<RoleTerm> roleTerms = new ArrayList<>();
    
    public NameBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public NameBuilder withNameParts(Namepart... nameParts) {
        this.nameParts = Arrays.asList(nameParts);
        return this;
    }

    public NameBuilder withRoleTerms(RoleTerm... roleTerms) {
        this.roleTerms = Arrays.asList(roleTerms);
        return this;
    }

    public Name build() {
        Name name = new Name();
        name.setType(type);
        name.setNameParts(nameParts);
        Role role = new Role();
        role.setRoleTerms(roleTerms);
        name.setRole(Arrays.asList(role));
        return name;
    }
}
