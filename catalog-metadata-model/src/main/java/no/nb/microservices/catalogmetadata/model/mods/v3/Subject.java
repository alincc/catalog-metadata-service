package no.nb.microservices.catalogmetadata.model.mods.v3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Topic> topics;
    private List<String> genres;
    private List<Geographic> geographic;
    private Cartographics cartographics;
    private String authority;

    @XmlElement(name = "topic", namespace = "http://www.loc.gov/mods/v3")
    public List<Topic> getTopic() {
        return topics;
    }

    public void setTopic(List<Topic> topics) {
        this.topics = topics;
    }

    @XmlElement(name = "geographic", namespace = "http://www.loc.gov/mods/v3")
    public List<Geographic> getGeographic() {
        return geographic;
    }

    public void setGeographic(List<Geographic> geographic) {
        this.geographic = geographic;
    }

    @XmlElement(name = "cartographics", namespace = "http://www.loc.gov/mods/v3")
    public Cartographics getCartographics() {
        return cartographics;
    }

    public void setCartographics(Cartographics cartographics) {
        this.cartographics = cartographics;
    }

    @XmlElement(name = "genre", namespace = "http://www.loc.gov/mods/v3")
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @XmlAttribute(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}