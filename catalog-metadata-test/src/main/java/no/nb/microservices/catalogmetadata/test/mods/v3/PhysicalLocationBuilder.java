package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.PhysicalLocation;

public class PhysicalLocationBuilder {

    private String authority;
    private String value;
    
    public PhysicalLocationBuilder withAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public PhysicalLocationBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public PhysicalLocation build() {
        PhysicalLocation physicalLocation = new PhysicalLocation();
        physicalLocation.setAuthority(authority);
        physicalLocation.setValue(value);
        return physicalLocation;
    }

}
