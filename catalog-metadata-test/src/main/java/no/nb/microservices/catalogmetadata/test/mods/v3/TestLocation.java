package no.nb.microservices.catalogmetadata.test.mods.v3;

import no.nb.microservices.catalogmetadata.model.mods.v3.Url;

public class TestLocation {

    public static LocationBuilder aDefaultMusicLocation() {
        Url url = new Url();
        url.setValue("URN:NBN:no-nb_digilyd_2000010100001");
        return new LocationBuilder().withUrls(url);
    }

    public static LocationBuilder aDefaultMovieLocation() {
        Url url = new Url();
        url.setValue("URN:NBN:no-nb_video_7000");
        return new LocationBuilder().withUrls(url);
    }

}
