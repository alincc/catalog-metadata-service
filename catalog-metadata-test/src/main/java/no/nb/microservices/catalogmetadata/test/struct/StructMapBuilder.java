package no.nb.microservices.catalogmetadata.test.struct;

import java.util.ArrayList;
import java.util.List;

import no.nb.microservices.catalogmetadata.model.struct.Div;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

public class StructMapBuilder {

    private int numberOfpages = 10;
    private boolean addCoverFront;
    private boolean addCoverBack;
    private boolean addCoverSpine;
    private boolean addInsideFrontCover;
    private boolean addInsideBackCover;
    
    public StructMapBuilder() {
        numberOfpages = 10;
        addCoverFront = true;
        addCoverBack = true;
        addCoverSpine = true;
        addInsideFrontCover = true;
        addInsideBackCover = true;
    }
    
    public StructMapBuilder withPages(int numberOfPages) {
        this.numberOfpages = numberOfPages;
        return this;
    }
    
    public StructMapBuilder withCoverFront(boolean coverFront) {
        this.addCoverFront = coverFront;
        return this;
    }

    public StructMapBuilder withCoverBack(boolean addCoverBack) {
        this.addCoverBack = addCoverBack;
        return this;
    }

    public StructMapBuilder withCoverSpine(boolean addCoverSpine) {
        this.addCoverSpine = addCoverSpine;
        return this;
    }

    public StructMapBuilder withInsideCoverFront(boolean addInsideFrontCover) {
        this.addInsideFrontCover = addInsideFrontCover;
        return this;
    }

    public StructMapBuilder withInsideCoverBack(boolean addInsideBackCover) {
        this.addInsideBackCover = addInsideBackCover;
        return this;
    }

    public StructMap build() {
        List<Div> divs = new ArrayList<>();
        
        if (addCoverFront) {
            divs.add(createDiv("C1"));
        }

        if (addInsideFrontCover) {
            divs.add(createDiv("I1"));
        }
        for(int pageNumber = 0; pageNumber < numberOfpages; pageNumber++) {
            divs.add(createDiv(""+pageNumber));
        }

        if (addInsideBackCover) {
            divs.add(createDiv("I3"));
        }

        if (addCoverBack) {
            divs.add(createDiv("C3"));
        }

        if (addCoverSpine) {
            divs.add(createDiv("C2"));
        }

        StructMap struct = new StructMap();
        struct.setDivs(divs);
        
        return struct;
    }

    private Div createDiv(String pageNumber) {
        return new DivBuilder().withPageNumber(pageNumber).build();
    }


}
