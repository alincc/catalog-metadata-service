package no.nb.microservices.catalogmetadata.core.transform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Paths;

@Service
public class TransformerServiceImpl implements ITransformerService {
    final Logger log = LoggerFactory.getLogger(TransformerServiceImpl.class);

    @Override
    public String transform(String xml, String xslTemplate) {
        TransformerFactory factory = TransformerFactory.newInstance();
        StringReader xmlString = new StringReader(xml);
        StreamSource in = new StreamSource(xmlString);
        StringWriter writer = new StringWriter();
        StreamResult out = new StreamResult(writer);

        try {
            String stylesheetPath = Paths.get(getClass().getResource(xslTemplate).toURI()).toString();
            StreamSource xsltStream = new StreamSource(stylesheetPath);
            Transformer transformer = factory.newTransformer(xsltStream);
            transformer.transform(in, out);
            return writer.toString();
        } catch (Exception ex) {
            log.error("Error transforming xml: " + ex.getLocalizedMessage());
        }

        return null;
    }
}
