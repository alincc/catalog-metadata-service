package no.nb.microservices.catalogmetadata.rest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.exception.FieldsParserException;
import no.nb.microservices.catalogmetadata.model.fields.FieldResource;

public class FieldResultResourceAssemblerTest {

    private FieldResultResourceAssembler assembler;
    private MockHttpServletRequest request;

    @Before
    public void setup() {
        assembler = new FieldResultResourceAssembler();
        request = new MockHttpServletRequest("GET", "/c06c5cbe2f82113e7b4757dbb14f8676/fields");
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);
    }
    
    @After
    public void cleanUp() {
        RequestContextHolder.resetRequestAttributes();
    }
    
    @Test
    public void testSelfLink() {
        Fields fields = new Fields("id1");
        
        FieldResource resource = assembler.toResource(fields);
        System.out.println(resource.getId().getHref());
        assertEquals("Should have a self-referential link element", "self", resource.getId().getRel());
    }
    
    @Test
    public void testAssemblingTitle() {
        Fields fields = new Fields("id1");
        fields.setFieldsAsJson("[{\"name\":\"title\",\"value\":\"Lovefool\"}]");

        FieldResource resource = assembler.toResource(fields);
        
        assertEquals("Lovefool", resource.getTitle());
    }

    @Test
    public void testAssemblingMediaType() {
        Fields fields = new Fields("id1");
        fields.setFieldsAsJson("[{\"name\":\"mediatype\",\"value\":\"Bøker\"}]");

        FieldResource resource = assembler.toResource(fields);

        assertEquals(1, resource.getMediaTypes().size());
        assertEquals("Bøker", resource.getMediaTypes().get(0));
    }

    @Test
    public void testAssemblingMediaTypes() {
        Fields fields = new Fields("id1");
        fields.setFieldsAsJson("[{\"name\":\"mediatype\",\"value\":\"Bøker,Aviser\"}]");

        FieldResource resource = assembler.toResource(fields);

        assertEquals(2, resource.getMediaTypes().size());
        assertEquals("Bøker", resource.getMediaTypes().get(0));
        assertEquals("Aviser", resource.getMediaTypes().get(1));
    }

    @Test
    public void testAssemblingContentClasses() {
        Fields fields = new Fields("id1");
        fields.setContentClasses("[\"restricted\", \"jp2\"]");

        FieldResource resource = assembler.toResource(fields);
        
        assertArrayEquals(new String[]{"restricted", "jp2"}, resource.getContentClasses().toArray());
    }

    @Test
    public void testAssemblingMetadataClasses() {
        Fields fields = new Fields("id1");
        fields.setMetadataClasses("[\"restricted\", \"public\"]");

        FieldResource resource = assembler.toResource(fields);
        
        assertArrayEquals(new String[]{"restricted", "public"}, resource.getMetadataClasses().toArray());
    }

    @Test
    public void testAssemblingURN() {
        Fields fields = new Fields("id1");
        fields.setFieldsAsJson("[{\"name\":\"urn\",\"value\":\"URN:NBN:no-nb_digibok_2010113008086\"}]");

        FieldResource resource = assembler.toResource(fields);
        assertTrue("urn list should not be empty", !resource.getUrns().isEmpty());
        assertEquals("URN:NBN:no-nb_digibok_2010113008086",resource.getUrns().get(0));
    }

    @Test(expected = FieldsParserException.class)
    public void whenFieldsHasIllegalJsonThenThrowError() throws Exception {
        Fields fields = new Fields("id1");
        createIllegalFields(fields);

        assembler.toResource(fields);
    }

    private void createIllegalFields(Fields fields) {
        fields.setFieldsAsJson("[{\"name\":\"digital\",\"value\":\"Ja}]");
    }
    
}
