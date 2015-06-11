package no.nb.microservices.catalogmetadata.core.metadata.repository;

public interface IMetadataRepository {
    public String getModsStringById(String id);
    public String getFieldsById(String id);
    public String getStructById(String id);
}
