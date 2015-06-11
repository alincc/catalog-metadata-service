package no.nb.microservices.catalogmetadata.model.struct;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name="structMap")
public class StructMap implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Div> divs;
    private Map<String, Div> divsMap;

    @XmlElement(name = "div")
    public List<Div> getDivs() {
        return divs;
    }

    public void setDivs(List<Div> divs) {
        this.divs = divs;
    }

    public Map<String, Div> getDivsMap() {
        if (this.divsMap == null) {
            this.divsMap = new HashMap<String, Div>();
            for (Div div : this.divs) {
                divsMap.put(div.getOrder(), div);
            }
        }
        return this.divsMap;
    }
}