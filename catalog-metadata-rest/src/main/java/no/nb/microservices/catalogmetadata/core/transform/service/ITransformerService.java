package no.nb.microservices.catalogmetadata.core.transform.service;

public interface ITransformerService {
    public static final String MODS2MARC21 = "/stylesheet/MODS2MARC21slim.xsl";

    String transform(String xml, String xslTemplate);
}
