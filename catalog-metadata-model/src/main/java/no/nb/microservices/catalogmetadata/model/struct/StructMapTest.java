package no.nb.microservices.catalogmetadata.model.struct;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StructMapTest {

    @Test
    public void addDiv() {
        StructMap structMap = new StructMap();
        
        structMap.addDiv(new Div());
        
        assertEquals(1, structMap.getDivs().size());
    }
    
    @Test
    public void testGetDivById() {
        String id = "id1";
        StructMap structMap = new StructMap();
        structMap.addDiv(new Div("id0"));
        structMap.addDiv(new Div(id));
        structMap.addDiv(new Div("id2"));
        
        Div div = structMap.getDivById(id);
        
        assertEquals(id, div.getId());
    }

}
