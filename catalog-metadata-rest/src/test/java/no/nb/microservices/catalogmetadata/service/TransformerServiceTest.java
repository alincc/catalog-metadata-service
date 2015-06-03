package no.nb.microservices.catalogmetadata.service;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

public class TransformerServiceTest extends XMLTestCase {

    @Test
    public void testTransform() throws Exception {
        TransformerService service = new TransformerService();
        File modsFile = new File(Paths.get(getClass().getResource("mods1.xml").toURI()).toString());
        File marcFile = new File(Paths.get(getClass().getResource("marc1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        String marcString = FileUtils.readFileToString(marcFile);
        String result = service.transform(modsString, TransformerService.MODS2MARC21);
        XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(marcString, result);

        result = service.transform("bogus xml :O", TransformerService.MODS2MARC21);
        assertNull(result);
    }
}
