package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.Extension;
import no.nb.microservices.catalogmetadata.model.mods.v3.Identifier;
import no.nb.microservices.catalogmetadata.model.mods.v3.Location;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.mods.v3.TitleInfo;

public class ModsBuilder {
    private List<TitleInfo> titleInfos;
    private Location location = null;
    private List<Identifier> identifiers;
    private Extension extension;
    
    public ModsBuilder withTitleInfos(final TitleInfo... titleInfos) {
        this.titleInfos = Arrays.asList(titleInfos);
        return this;
    }
    
    public ModsBuilder withLocation(final Location location) {
        this.location = location;
        return this;
    }
    
    public ModsBuilder withIdentifiers(final Identifier... identifiers) {
        this.identifiers = Arrays.asList(identifiers);
        return this;
    }

    public ModsBuilder withExtension(Extension extension) {
        this.extension = extension;
        return this;
    }
    
    public Mods build() {
        Mods mods = new Mods();
        mods.setTitleInfos(titleInfos);
        mods.setLocation(location);
        mods.setIdentifiers(identifiers);
        mods.setExtension(extension);
        return mods;
    }

}
