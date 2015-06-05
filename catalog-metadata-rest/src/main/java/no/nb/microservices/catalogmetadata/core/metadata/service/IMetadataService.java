package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import loc.gov.mods.ModsType;

public interface IMetadataService {
    ModsType getMods(String id);
    RecordType getMarcxml(String id);
}
