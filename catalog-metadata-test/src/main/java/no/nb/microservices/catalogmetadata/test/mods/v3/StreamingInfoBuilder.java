package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.Extent;
import no.nb.microservices.catalogmetadata.model.mods.v3.Identifier;
import no.nb.microservices.catalogmetadata.model.mods.v3.Offset;
import no.nb.microservices.catalogmetadata.model.mods.v3.StreamingInfo;

public class StreamingInfoBuilder {
    private Identifier identifier;
    private Offset offset;
    private Extent extent;
    
    public StreamingInfoBuilder withIdentifier(Identifier identifier) {
        this.identifier = identifier;
        return this;
    }
    
    public StreamingInfoBuilder withOffset(Offset offset) {
        this.offset = offset;
        return this;
    }

    public StreamingInfoBuilder withExtent(Extent extent) {
        this.extent = extent;
        return this;
    }

    public StreamingInfo build() {
        StreamingInfo streamingInfo = new StreamingInfo();
        streamingInfo.setIdentifier(identifier);
        streamingInfo.setOffset(offset);
        streamingInfo.setExtent(extent);
        return streamingInfo;
    }

}
