package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.DateMods;

public class DateModsBuilder {
    private String encoding;
    private String value;
    
    public DateModsBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public DateModsBuilder withEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }
    
    public DateMods build() {
        DateMods date = new DateMods();
        date.setEncoding(encoding);
        date.setValue(value);
        return date;
    }

}
