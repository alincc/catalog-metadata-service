package no.nb.microservices.catalogmetadata.core.metadata.service;

import java.util.List;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.model.fields.Field;
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
    List<Field> getFieldsById(String id);
    StructMap getStructById(String id);
}
