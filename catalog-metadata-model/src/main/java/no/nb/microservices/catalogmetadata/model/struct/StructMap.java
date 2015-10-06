package no.nb.microservices.catalogmetadata.model.struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="structMap")
public class StructMap implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Div> divs;

    @XmlElement(name = "div")
    public List<Div> getDivs() {
        return divs;
    }
    
    public boolean addDiv(Div div) {
        if(divs == null) {
            divs = new ArrayList<>();
        }
        return divs.add(div);
    }

    public void setDivs(List<Div> divs) {
        this.divs = divs;
    }

    public Map<String, Div> getDivsMap() {
       Map<String, Div> divsMap = new HashMap<>();
       for (Div div : this.divs) {
           divsMap.put(div.getOrder(), div);
       }
       return divsMap;
    }

    public Div getDivById(String id) {
        for (Div div : this.divs) {
            if (id.equals(div.getId())) {
                return div;
            }
        }
        
        return null;
    }

    public Div getDivByHref(String href) {
        for (Div div : this.divs) {
            if (href.equals(div.getResource().getHref())) {
                return div;
            }
        }
        
        return null;
    }
}