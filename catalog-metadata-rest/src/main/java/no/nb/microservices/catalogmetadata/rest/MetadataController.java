package no.nb.microservices.catalogmetadata.rest;

import loc.gov.marc.RecordType;
import no.nb.htrace.annotation.Traceable;
import no.nb.microservices.catalogmetadata.core.metadata.service.MetadataService;
import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.model.fields.FieldResource;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/catalog/v1/metadata", description = "Metadata api")
@RequestMapping(value = "/catalog/v1/metadata")
public class MetadataController {
    private final MetadataService service;

    @Autowired
    public MetadataController(MetadataService service) {
        this.service = service;
    }

    @ApiOperation(value = "Find mods by id", response = Mods.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
                            @ApiResponse(code = 404, message = "Not found")})
    @Traceable(description="mods")
    @RequestMapping(value = "/{id}/mods", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Mods> getMods(@PathVariable String id) {
        Mods mods = service.getModsById(id);
        return new ResponseEntity(mods, HttpStatus.OK);
    }

    @ApiOperation(value = "Find marcxml by id", response = RecordType.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
                            @ApiResponse(code = 404, message = "Not found")})
    @Traceable(description="marcxml")
    @RequestMapping(value = "/{id}/marcxml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<RecordType> getMarcxml(@PathVariable String id) {
        RecordType marc = service.getMarcxmlById(id);
        return new ResponseEntity(marc,HttpStatus.OK);
    }

    @ApiOperation(value = "Find fields by id", response = Mods.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 404, message = "Not found")})
    @Traceable(description="fields")
    @RequestMapping(value = "/{id}/fields", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldResource> getFields(@PathVariable String id) {
        Fields fields = service.getFieldsById(id);
        FieldResource resource = new FieldResultResourceAssembler().toResource(fields);
        return new ResponseEntity<FieldResource>(resource,HttpStatus.OK);
    }

    @Traceable(description="struct")
    @RequestMapping(value = "/{id}/struct", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<StructMap> getStructure(@PathVariable String id) {
        StructMap struct = service.getStructById(id);
        return new ResponseEntity<>(struct, HttpStatus.OK);
    }
}
