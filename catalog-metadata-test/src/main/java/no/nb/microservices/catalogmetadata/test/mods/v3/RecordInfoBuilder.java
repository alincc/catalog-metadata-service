package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.RecordIdentifier;
import no.nb.microservices.catalogmetadata.model.mods.v3.RecordInfo;

public class RecordInfoBuilder {
    private String identifier;

    public RecordInfoBuilder withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public RecordInfo build() {
        RecordInfo recordInfo = new RecordInfo();
        RecordIdentifier recordIdentifier = new RecordIdentifier();
        recordIdentifier.setValue(identifier);
        recordInfo.setRecordIdentifier(recordIdentifier);

        return recordInfo;
    }

}
