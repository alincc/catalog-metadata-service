package no.nb.microservices.catalogmetadata.test.mods.v3;

import java.util.Arrays;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.mods.v3.Name;

public class TestName {

    public static List<Name> aDefaultBook() {
        Name creator = createName("Ibsen, Henrik", "1828-1906", "cre");
        Name other = createName("Stang, Edvard Kristoffer", "1902-1962", null);
        return Arrays.asList(creator, other);
    }

    private static Name createName(String name, String birtAndDeath, String roleCode) {

        Name creator = new NameBuilder()
                .withType("personal")
                .withNameParts(new NamepartBuilder()
                        .withValue(name)
                        .build(),
                        new NamepartBuilder()
                        .withType("date")
                        .withValue(birtAndDeath)
                        .build())
                .withRoleTerms(new RoleTermBuilder().withAuthority("marcrelator").withType("code").withValue(roleCode).build())
                .build();
        return creator;
    }

}
