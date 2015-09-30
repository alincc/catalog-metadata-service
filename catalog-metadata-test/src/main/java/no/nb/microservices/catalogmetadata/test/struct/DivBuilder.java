package no.nb.microservices.catalogmetadata.test.struct;

import java.text.DecimalFormat;

import org.apache.commons.lang.math.NumberUtils;

import no.nb.microservices.catalogmetadata.model.struct.Div;
import no.nb.microservices.catalogmetadata.model.struct.Resource;

public class DivBuilder {
    private final DecimalFormat df = new DecimalFormat("0000");
    String pageNumber;
    
    public DivBuilder() {
        this.pageNumber = "1";
    }
    
    public DivBuilder withPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }
    
    public Div build() {
        Div div = new Div("DIV" + pageNumber);
        div.setOrderLabel("p"+pageNumber);
        div.setType("PAGE");
        div.setOrder(""+pageNumber);
        Resource resource = new Resource();
        resource.setWidth(100);
        resource.setHeight(200);
        if (NumberUtils.isDigits(pageNumber)) {
            pageNumber = df.format(pageNumber);
        }
        resource.setOriginalName("digibok_2001010100001_" + pageNumber + ".jpg");
        resource.setHref("URN:NBN:no-nb_digibok_2001010100001_" + df.format(pageNumber));
        div.setResource(resource);
        return div;
    }
}
