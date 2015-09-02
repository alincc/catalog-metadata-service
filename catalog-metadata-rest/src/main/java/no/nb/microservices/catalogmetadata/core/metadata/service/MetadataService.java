package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

public interface MetadataService {

    Mods getModsById(String id);
    RecordType getMarcxmlById(String id);
    Fields getFieldsById(String id);
    StructMap getStructById(String id);
}
