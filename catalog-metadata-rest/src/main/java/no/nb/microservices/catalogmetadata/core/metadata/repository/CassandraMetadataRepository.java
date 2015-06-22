package no.nb.microservices.catalogmetadata.core.metadata.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.Bytes;
import no.nb.microservices.catalogmetadata.core.model.FieldsModel;
import no.nb.microservices.catalogmetadata.domain.CassandraModel;
import no.nb.microservices.catalogmetadata.exception.FieldNotFoundException;
import no.nb.microservices.catalogmetadata.exception.ModsNotFoundException;
import no.nb.microservices.catalogmetadata.exception.StructNotFoundException;
import no.nb.microservices.catalogmetadata.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CassandraMetadataRepository implements IMetadataRepository {
    private final CassandraOperations cassandraOperations;

    @Autowired
    public CassandraMetadataRepository(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    @Override
    public String getModsStringById(String id) {
        String modsRecord = getColumn(id, "modsRecord");
        if (modsRecord == null) {
            throw new ModsNotFoundException("Mods not found for id " + id);
        }
        return modsRecord;
    }

    @Override
    public String getStructById(String id) {
        String structure = getColumn(id, "structure");
        if (structure == null) {
            throw new StructNotFoundException("Structure not found for id " + id);
        }
        return structure;
    }

    @Override
    public FieldsModel getFieldsById(String id) {
        Select select = QueryBuilder.select().from("expressionrecord");
        select.where(QueryBuilder.eq("key", id)).and(QueryBuilder.in("column1","fields","contentClasses","metadataClasses"));
        List<CassandraModel> fields = cassandraOperations.select(select, CassandraModel.class);
        if (fields.isEmpty()) {
            throw new FieldNotFoundException("Field not found for id " + id);
        }
        return populateFieldsModel(fields);
    }

    private FieldsModel populateFieldsModel(List<CassandraModel> models) {
        FieldsModel fieldsModel = new FieldsModel();
        for (CassandraModel model : models) {
            String column = Converter.string(Bytes.getArray(model.getColumn1()));
            switch (column) {
                case "fields":
                    fieldsModel.setFields(model.getValueAsString());
                    break;
                case "contentClasses":
                    fieldsModel.setContentClasses(model.getValueAsString());
                    break;
                case "metadataClasses":
                    fieldsModel.setMetadataClasses(model.getValueAsString());
                    break;
                default:
                    break;
            }
        }
        return fieldsModel;
    }

    private String getColumn(String id, String column) {
        Select select = QueryBuilder.select().from("expressionrecord");
        select.where(QueryBuilder.eq("key",id)).and(QueryBuilder.eq("column1", column));
        List<CassandraModel> result = cassandraOperations.select(select, CassandraModel.class);
        if (result.isEmpty()) {
            return null;
        }
        CassandraModel model = result.get(0);
        return model.getValueAsString();
    }
}
