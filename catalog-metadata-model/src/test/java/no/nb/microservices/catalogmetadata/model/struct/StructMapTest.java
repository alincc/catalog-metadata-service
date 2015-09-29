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
        structMap.addDiv(createDiv("id0", "r0"));
        structMap.addDiv(createDiv(id, "r0"));
        structMap.addDiv(createDiv("id2", "r2"));
        
        Div div = structMap.getDivById(id);
        
        assertEquals(id, div.getId());
    }

    @Test
    public void testGetResourceByHref() {
        String href = "r10";
        StructMap structMap = new StructMap();
        structMap.addDiv(createDiv("id1", "r1"));
        structMap.addDiv(createDiv("id2", href));
        
        Resource resource = structMap.getResourceByHref(href);
        
        assertEquals(href, resource.getHref());
    }

    private Div createDiv(String id, String href) {
        Resource resource = new Resource();
        resource.setHref(href);
        Div div = new Div(id);
        div.setResource(resource);
        return div;
    }
}
