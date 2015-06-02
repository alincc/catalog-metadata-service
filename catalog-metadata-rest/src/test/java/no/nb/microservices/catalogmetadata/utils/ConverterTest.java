package no.nb.microservices.catalogmetadata.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConverterTest {

    @Test
    public void testString() {
        byte[] b = null;
        String result = Converter.string(b);
        assertNull(result);

        String s = "Hej hej hej jeg er bare 4 år!";
        b = s.getBytes();
        result = Converter.string(b);
        assertEquals(s,result);
    }
}
