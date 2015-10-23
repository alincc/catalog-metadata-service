package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.RoleTerm;

public class RoleTermBuilder {
    private String authority;
    private String type;
    private String value;
    
    public RoleTermBuilder withAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public RoleTermBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public RoleTermBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public RoleTerm build() {
        RoleTerm roleTerm = new RoleTerm();
        roleTerm.setAuthority(authority);
        roleTerm.setType(type);
        roleTerm.setValue(value);
        return roleTerm;
    }

}
