package no.nb.microservices.catalogmetadata.core.transform.service;

public interface ITransformerService {
    String transform(String xml, String xslTemplate);
}
