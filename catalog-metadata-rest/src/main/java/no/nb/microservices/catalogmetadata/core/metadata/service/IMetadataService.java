package no.nb.microservices.catalogmetadata.core.metadata.service;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.model.fields.Fields;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

/**
 * 
 * @author jimarthurnilsen
 * @author ronnymikalsen
 *
 */
public interface IMetadataService {
    Mods getModsById(String id);
    RecordType getMarcxmlById(String id);
    Fields getFieldsById(String id);
    StructMap getStructById(String id);

}
