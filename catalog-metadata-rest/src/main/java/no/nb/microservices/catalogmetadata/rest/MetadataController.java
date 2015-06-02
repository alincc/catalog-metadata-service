package no.nb.microservices.catalogmetadata.rest;

import com.wordnik.swagger.annotations.Api;
import loc.gov.marc.RecordType;
import loc.gov.mods.ModsType;
import no.nb.microservices.catalogmetadata.service.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/", description = "Home api")
public class MetadataController {
    private final CassandraService service;

    @Autowired
    public MetadataController(CassandraService service) {
        this.service = service;
    }

    @RequestMapping(value = "{id}/mods", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ModsType getMods(@PathVariable String id) {
        return service.getMods(id);
    }

    @RequestMapping(value = "{id}/marcxml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public RecordType getMarcxml(@PathVariable String id) {
        return service.getMarcxml(id);
    }
}
