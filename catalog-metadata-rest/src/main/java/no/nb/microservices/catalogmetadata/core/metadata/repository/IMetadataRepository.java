package no.nb.microservices.catalogmetadata.core.metadata.repository;

import java.util.Map;

public interface IMetadataRepository {
    public String getModsStringById(String id);
    public String getStructById(String id);
    public Map<String, String> getFieldsById(String id);
}
