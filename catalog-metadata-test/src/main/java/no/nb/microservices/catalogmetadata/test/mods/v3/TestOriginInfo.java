package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.DateMods;

public class TestOriginInfo {

    public static OriginInfoBuilder aDefaultMusic() {
        DateMods dateIssued = new DateModsBuilder()
                .withEncoding("w3cdtf")
                .withValue("2009")
                .build();

        return new OriginInfoBuilder()
                .withDateIssued(dateIssued) ;
    }

    public static OriginInfoBuilder aDefaultBook() {
        DateMods dateIssued = new DateModsBuilder()
                .withValue("2009")
                .build();

        return new OriginInfoBuilder()
                .withDateIssued(dateIssued) ;
    }

}
