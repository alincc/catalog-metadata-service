package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.TitleInfo;

public class TitleInfoBuilder {

    private String title;
    private String type;
    private String partNumber;
    
    public TitleInfoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TitleInfoBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public TitleInfoBuilder withPartNumber(String partNumber) {
        this.partNumber = partNumber;
        return this;
    }
    
    public TitleInfo build() {
        TitleInfo titleInfo = new TitleInfo();
        titleInfo.setTitle(title);
        titleInfo.setType(type);
        titleInfo.setPartNumber(partNumber);
        return titleInfo;
    }

}
