package no.nb.microservices.catalogmetadata.core.metadata.repository;

import no.nb.microservices.catalogmetadata.domain.Model;

import java.util.Map;

public interface IMetadataRepository {
    public String getModsStringById(String id);
    public String getStructById(String id);
    public Map<String, String> getFieldsById(String id);
}
