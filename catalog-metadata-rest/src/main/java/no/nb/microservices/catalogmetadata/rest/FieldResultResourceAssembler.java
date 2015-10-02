package no.nb.microservices.catalogmetadata.rest;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import no.nb.microservices.catalogmetadata.utils.Mapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.nb.microservices.catalogmetadata.core.model.Field;
import no.nb.microservices.catalogmetadata.core.model.Fields;
import no.nb.microservices.catalogmetadata.exception.FieldsParserException;
import no.nb.microservices.catalogmetadata.model.fields.FieldResource;

public class FieldResultResourceAssembler extends ResourceAssemblerSupport<Fields, FieldResource> {

    public FieldResultResourceAssembler() {
        super(MetadataController.class, FieldResource.class);
    }

    @Override
    public FieldResource toResource(Fields fields) {
        
        FieldResource resource = instantiateResource(fields);
        resource.add(createSelfLink(fields));
        populateResourceFromFields(fields, resource);

        return resource;
    }

    private Link createSelfLink(Fields fields) {
        return linkTo(methodOn(MetadataController.class).getFields(fields.getId())).withSelfRel();
    }

    private void populateResourceFromFields(Fields fields, FieldResource resource) {
        try {
            List<Field> fieldsList = getAllFields(fields.getFieldsAsJson());
            
            String digital = getNamedField("digital", fieldsList).getValue();
            resource.setTitle(getNamedField("title", fieldsList).getValue());
            resource.setDigital("Ja".equals(digital) ? true : false);
            String urnString = getNamedField("urn",fieldsList).getValue();
            if (urnString != null) {
                resource.setUrns(Arrays.asList(urnString.split(";")));
            }
            resource.setMediaTypes(Mapper.getStringAsList(getNamedField("mediatype", fieldsList).getValue()));
            resource.setContentClasses(fields.getContentClassesAsList());
            resource.setMetadataClasses(fields.getMetadataClassesAsList());
            
        } catch (Exception ex) {
            throw new FieldsParserException("Error parsing " + fields, ex);
        }
    }
    
    private List<Field> getAllFields(String fieldsAsJson)
            throws IOException {
        List<Field> fieldsList = new ArrayList<>();
        
        if (fieldsAsJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            fieldsList = mapper.readValue(fieldsAsJson, new TypeReference<List<Field>>(){
            });
        }
        return fieldsList;
    }

    private Field getNamedField(String name, List<Field> fields) {
        for(Field field : fields) {
            if (name.equalsIgnoreCase(field.getName())) {
                return field;
            }
        }
        return new Field();
    }
}
