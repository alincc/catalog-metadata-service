package no.nb.microservices.catalogmetadata.test.mods.v3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import no.nb.microservices.catalogmetadata.model.mods.v3.Extension;
import no.nb.microservices.catalogmetadata.model.mods.v3.Extent;
import no.nb.microservices.catalogmetadata.model.mods.v3.Identifier;
import no.nb.microservices.catalogmetadata.model.mods.v3.Location;
import no.nb.microservices.catalogmetadata.model.mods.v3.Offset;
import no.nb.microservices.catalogmetadata.model.mods.v3.PhysicalLocation;
import no.nb.microservices.catalogmetadata.model.mods.v3.StreamingInfo;
import no.nb.microservices.catalogmetadata.model.mods.v3.TitleInfo;
import no.nb.microservices.catalogmetadata.test.exception.TestDataException;

import java.util.Arrays;

public final class TestMods {

    public static ModsBuilder aDefaultMods() {
        return new ModsBuilder();
    }
    
    public static ModsBuilder aDefaultMusicAlbum() {
        TitleInfo title = new TitleInfoBuilder()
                .withTitle("The sun always shines on T.V. : [Disconet]")
                .build();

        return new ModsBuilder()
                .withTitleInfos(title)
                .withLocation(TestLocation.aDefaultMusicLocation().build())
                .withRelatedItems(TestRelatedItem.aDefaultMusicAlbum());
    }

    public static String aDefaultMusicAlbumXml() {
        return objectToXml(aDefaultMusicAlbum().build());
    }
    
    public static ModsBuilder aDefaultMusicTrack() {
        return new ModsBuilder()
                .withLocation(TestLocation.aDefaultMusicLocation().build())
                .withRelatedItems(TestRelatedItem.aDefaultMusicTrack());
    }
    
    public static ModsBuilder aDefaultMovieMods() {
        return new ModsBuilder()
                .withLocation(TestLocation.aDefaultMovieLocation().build());
    }

    public static ModsBuilder aDefaultRadioHourMods() {
        Identifier identifier = new IdentifierBuilder()
                .withType("urn")
                .withValue("URN:NBN:no-nb_nrk_RP3199611270600")
                .build();
        return new ModsBuilder()
                .withIdentifiers(identifier);
    }

    public static ModsBuilder aDefaultRadioProgramMods() {
        Identifier identifier = new IdentifierBuilder()
                .withType("urn")
                .withValue("URN:NBN:no-nb_nrk_RP3199611270600")
                .build();
        Offset offset = new OffsetBuilder()
            .withFormat("seconds")
            .withValue(100)
            .build();
        Extent extent = new ExtentBuilder()
                .withFormat("seconds")
                .withValue(200)
                .build();
        StreamingInfo streamingInfo = new StreamingInfoBuilder()
                .withIdentifier(identifier)
                .withOffset(offset)
                .withExtent(extent)
                .build();
        
        Extension extension = new Extension();
        extension.setStreamingInfos(Arrays.asList(streamingInfo));
        
        return new ModsBuilder()
                .withExtension(extension);
    }

    public static ModsBuilder aDefaultBookMods() {
        TitleInfo title = new TitleInfoBuilder()
                .withTitle("Villanden")
                .build();
        TitleInfo originalTitle = new TitleInfoBuilder()
                .withType("uniform")
                .withTitle("Vildanden")
                .build();
        Identifier sesamid = new IdentifierBuilder()
                .withType("sesamid")
                .withValue("f17f2bf12cc19b377f7ce992faa40a0c")
                .build();
        Identifier oaiid = new IdentifierBuilder()
                .withType("oaiid")
                .withValue("oai:bibsys.no:biblio:96192845x")
                .build();
        Identifier isbn13a = new IdentifierBuilder()
                .withType("isbn")
                .withValue("9788203341090")
                .build();
        Identifier isbn13b = new IdentifierBuilder()
                .withType("isbn")
                .withValue("9788203341091 (h.)")
                .build();
        Identifier isbn10a = new IdentifierBuilder()
                .withType("isbn")
                .withValue("9788203341 (h.)")
                .build();
        Identifier isbn10b = new IdentifierBuilder()
                .withType("isbn")
                .withValue("9788203340")
                .build();
        Identifier issn = new IdentifierBuilder()
                .withType("issn")
                .withValue("97882033")
                .build();
        Identifier urn = new IdentifierBuilder()
                .withType("urn")
                .withValue("URN:NBN:no-nb_digibok_2014070158006")
                .build();

        PhysicalLocation physicalLocation = new PhysicalLocationBuilder()
                .withAuthority("isil")
                .withValue("NO-1160105")
                .build();
        Location location = new LocationBuilder()
                .withPhysicalLocations(physicalLocation)
                .build();
        
        return new ModsBuilder()
                .withTitleInfos(title, originalTitle)
                .withIdentifiers(sesamid, oaiid, isbn13a, isbn13b, isbn10a, isbn10b, issn, urn)
                .withNames(TestName.aDefaultBook())
                .withLocation(location);
    }

    public static String aDefaultBookModsXml() {
        return objectToXml(aDefaultBookMods().build());
    }

    private static String objectToXml(Object object) {
        ObjectMapper mapper = new XmlMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new TestDataException(ex);
        }
    }

}
