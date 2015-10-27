package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.ArrayList;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.RecordInfo;
import no.nb.microservices.catalogmetadata.model.mods.v3.RelatedItem;
import no.nb.microservices.catalogmetadata.model.mods.v3.TitleInfo;

public class TestRelatedItem {

    private static final String TYPE_CONSTITUENT = "constituent";
    private static final String TYPE_HOST = "host";
    private static final String TYPE_PRECEDING = "preceding";
    private static final String TYPE_SUCCEEDING = "succeeding";

    public static List<RelatedItem> aDefaultMusicAlbum() {
        List<RelatedItem> relatedItems = new ArrayList<>();
        relatedItems.add(createRelatedItem(TYPE_CONSTITUENT, "1", "Paula Abdul medley", "123456"));
        relatedItems.add(createRelatedItem(TYPE_CONSTITUENT, "2", "Qu est le soleil? : [Disconet]", "123457"));
        relatedItems.add(createRelatedItem(TYPE_CONSTITUENT, "3", "Don't drop bombs : [Disconet]", "123458"));
        relatedItems.add(createRelatedItem(TYPE_PRECEDING, "1", "Paula Abdul medley", "123456"));
        relatedItems.add(createRelatedItem(TYPE_SUCCEEDING, "3", "Don't drop bombs : [Disconet]", "123458"));
        return relatedItems;
    }
    
    public static List<RelatedItem> aDefaultMusicTrack() {
        List<RelatedItem> relatedItems = new ArrayList<>();
        relatedItems.add(createRelatedItem(TYPE_HOST, null, "Disconet dance classics volume 3", "777231"));
        return relatedItems;
    }
    
    private static RelatedItem createRelatedItem(String type, String partNumber, String title, String identifier) {
        TitleInfo titleInfo = new TitleInfoBuilder()
                .withPartNumber(partNumber)
                .withTitle(title)
                .build();
        RecordInfo recordInfo = new RecordInfoBuilder()
                .withIdentifier(identifier)
                .build();
        
        return new RelatedItemBuilder()
                .withType(type)
                .withTitleInfos(titleInfo)
                .withRecordInfo(recordInfo)
                .build();
    }
    
}
