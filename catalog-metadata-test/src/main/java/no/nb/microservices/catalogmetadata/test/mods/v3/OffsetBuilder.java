package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.Offset;

public class OffsetBuilder {
    private Integer value;
    private String format;
    
    public OffsetBuilder withFormat(String format) {
        this.format = format;
        return this;
    }

    public OffsetBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public Offset build() {
        Offset offset = new Offset();
        offset.setFormat(format);
        offset.setValue(""+value);
        
        return offset;
    }
    
}
