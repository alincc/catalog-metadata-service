package no.nb.microservices.catalogmetadata.core.transform.service;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

public class TransformerServiceImplTest extends XMLTestCase {

    @Test
    public void testTransform() throws Exception {
        ITransformerService service = new TransformerServiceImpl();
        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        File marcFile = new File(Paths.get(getClass().getResource("/xml/marc1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        String marcString = FileUtils.readFileToString(marcFile);
        String result = service.transform(modsString, TransformerServiceImpl.MODS2MARC21);
        XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(marcString, result);

        result = service.transform("bogus xml :O", TransformerServiceImpl.MODS2MARC21);
        assertNull(result);
    }
}
