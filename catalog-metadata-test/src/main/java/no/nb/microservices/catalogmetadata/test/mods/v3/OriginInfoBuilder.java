package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.DateMods;
import no.nb.microservices.catalogmetadata.model.mods.v3.OriginInfo;

public class OriginInfoBuilder {

    private List<DateMods> dateIssueds;

    public OriginInfoBuilder withDateIssued(final DateMods... dateIssueds) {
        this.dateIssueds = Arrays.asList(dateIssueds);
        return this;
    }

    public OriginInfo build() {
        OriginInfo originInfo = new OriginInfo();
        originInfo.setDateIssuedList(dateIssueds);
        return originInfo;
    }
}
