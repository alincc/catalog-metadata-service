package no.nb.microservices.catalogmetadata.test.mods.v3;

public class TestIdentifier {

    public static IdentifierBuilder aDefaultMusicIdentifier() {
        return new IdentifierBuilder()
            .withType("urn")
            .withValue("URN:NBN:no-nb_lydsikringkopi_21856");
    }

}
