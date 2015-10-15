package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.Location;
import no.nb.microservices.catalogmetadata.model.mods.v3.PhysicalLocation;
import no.nb.microservices.catalogmetadata.model.mods.v3.Url;

public class LocationBuilder {
    private List<Url> urls = null;
    private List<PhysicalLocation> physicalLocations;
    
    public LocationBuilder withUrls(Url... urls) {
        this.urls = Arrays.asList(urls);
        return this;
    }
    
    public LocationBuilder withPhysicalLocations(PhysicalLocation... physicalLocations) {
        this.physicalLocations = Arrays.asList(physicalLocations);
        return this;
    }
    
    public Location build() {
        Location location = new Location();
        location.setUrls(urls);
        location.setPhysicalLocations(physicalLocations);
        return location;
    }

}
