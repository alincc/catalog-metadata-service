package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.Namepart;

public class NamepartBuilder {
    private String type;
    private String value;
    
    public NamepartBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public NamepartBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public Namepart build() {
        Namepart namepart = new Namepart();
        namepart.setType(type);
        namepart.setValue(value);
        return namepart;
    }

}
