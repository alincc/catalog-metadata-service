package no.nb.microservices.catalogmetadata.core.metadata.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import no.nb.microservices.catalogmetadata.domain.Model;
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
    public String getModsString(String id) {
        Select select = QueryBuilder.select().from("expressionrecord");
        select.where(QueryBuilder.eq("key",id)).and(QueryBuilder.eq("column1", "modsRecord"));
        List<Model> result = cassandraOperations.select(select, Model.class);
        if (result.isEmpty()) {
            return null;
        }
        Model model = result.get(0);
        return model.getValueAsString();
    }
}