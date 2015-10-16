package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.Extent;
import no.nb.microservices.catalogmetadata.model.mods.v3.Offset;

public class ExtentBuilder {
    private Integer value;
    private String format;
    
    public ExtentBuilder withFormat(String format) {
        this.format = format;
        return this;
    }

    public ExtentBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public Extent build() {
        Extent extent = new Extent();
        extent.setFormat(format);
        extent.setValue(""+value);
        
        return extent;
    }
}
