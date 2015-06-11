package no.nb.microservices.catalogmetadata.rest;

import loc.gov.marc.RecordType;
import no.nb.microservices.catalogmetadata.core.metadata.service.IMetadataService;
import no.nb.microservices.catalogmetadata.model.fields.Fields;
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

/**
 * 
 * @author jimarthurnilsen
 * @author ronnymikalsen
 *
 */
@RestController
@Api(value = "/", description = "Metadata api")
public class MetadataController {
    private final IMetadataService service;

    @Autowired
    public MetadataController(IMetadataService service) {
        this.service = service;
    }

    @ApiOperation(value = "Find mods by id", response = Mods.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
                            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = "{id}/mods", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Mods> getMods(@PathVariable String id) {
        Mods mods = service.getModsById(id);
        if (mods == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(mods, HttpStatus.OK);
    }

    @ApiOperation(value = "Find marcxml by id", response = RecordType.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
                            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = "{id}/marcxml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<RecordType> getMarcxml(@PathVariable String id) {
        RecordType marc = service.getMarcxmlById(id);
        if (marc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(marc,HttpStatus.OK);
    }

    @ApiOperation(value = "Find fields by id", response = Mods.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 404, message = "Not found")})
    @RequestMapping(value = "{id}/fields", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fields> getFields(@PathVariable String id) {
        Fields fields = service.getFieldsById(id);
        return new ResponseEntity(fields,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/struct", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<StructMap> getStructure(@PathVariable String id) {
        StructMap struct = service.getStructById(id);
        return new ResponseEntity<>(struct, HttpStatus.OK);
    }
}
