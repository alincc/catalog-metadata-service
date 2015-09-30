package no.nb.microservices.catalogmetadata.test.struct;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import no.nb.microservices.catalogmetadata.model.struct.StructMap;

public final class TestStructMap {
    
    public static StructMapBuilder aDefaultStructMap() {
        return new StructMapBuilder().withCoverFront(true);
    }
   
    public static String structMapToString(StructMap struct) {
        StringWriter sw = new StringWriter();
        try {
            final Marshaller m = JAXBContext.newInstance(StructMap.class).createMarshaller();
            m.marshal(struct, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
    
}
