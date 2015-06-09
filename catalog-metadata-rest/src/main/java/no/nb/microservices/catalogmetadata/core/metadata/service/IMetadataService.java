package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;

public interface IMetadataService {
    Mods getModsById(String id);
    RecordType getMarcxmlById(String id);
    String getFieldsById(String id);
}
