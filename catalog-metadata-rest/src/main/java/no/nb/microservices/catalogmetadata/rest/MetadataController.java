package no.nb.microservices.catalogmetadata.rest;

import com.wordnik.swagger.annotations.Api;
import loc.gov.marc.RecordType;
import loc.gov.mods.ModsType;
import no.nb.microservices.catalogmetadata.core.metadata.service.IMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/", description = "Home api")
public class MetadataController {
    private final IMetadataService service;

    @Autowired
    public MetadataController(IMetadataService service) {
        this.service = service;
    }

    @RequestMapping(value = "{id}/mods", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ModsType> getMods(@PathVariable String id) {
        ModsType mods = service.getMods(id);
        if (mods == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity(mods, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/marcxml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<RecordType> getMarcxml(@PathVariable String id) {
        RecordType marc = service.getMarcxml(id);
        if (marc == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity(marc,HttpStatus.OK);
    }
}
