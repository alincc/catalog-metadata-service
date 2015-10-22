package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.RecordInfo;
import no.nb.microservices.catalogmetadata.model.mods.v3.RelatedItem;
import no.nb.microservices.catalogmetadata.model.mods.v3.TitleInfo;

public class RelatedItemBuilder {
    private String type;
    private List<TitleInfo> titleInfos;
    private RecordInfo recordInfo;
    
    public RelatedItemBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public RelatedItemBuilder withTitleInfos(TitleInfo... titleInfos) {
        this.titleInfos = Arrays.asList(titleInfos);
        return this;
    }

    public RelatedItemBuilder withRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
        return this;
    }

    public RelatedItem build() {
        RelatedItem relatedItem = new RelatedItem();
        relatedItem.setType(type);
        relatedItem.setTitleInfo(titleInfos);
        relatedItem.setRecordInfo(recordInfo);
        return relatedItem;
    }

}
