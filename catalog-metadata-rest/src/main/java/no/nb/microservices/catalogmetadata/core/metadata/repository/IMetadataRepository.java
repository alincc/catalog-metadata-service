package no.nb.microservices.catalogmetadata.core.metadata.repository;

import no.nb.microservices.catalogmetadata.core.model.Fields;

public interface IMetadataRepository {
    public String getModsStringById(String id);
    public String getStructById(String id);
    public Fields getFieldsById(String id);
}
