package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.Identifier;

public class IdentifierBuilder {
    private String type;
    private String value;
    
    public IdentifierBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public IdentifierBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public Identifier build() {
        Identifier identifier = new Identifier();
        identifier.setType(type);
        identifier.setValue(value);
        return identifier;
    }
    
}
