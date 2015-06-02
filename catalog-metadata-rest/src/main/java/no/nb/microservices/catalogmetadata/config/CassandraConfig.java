package no.nb.microservices.catalogmetadata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Autowired
    ApplicationSettings settings;

    @Override
    protected String getKeyspaceName() {
        return settings.getCassandra().getKeyspace();
    }

    @Override
    protected int getPort() {
        return settings.getCassandra().getPort();
    }

    @Override
    protected String getContactPoints() {
        return settings.getCassandra().getHost();
    }

    @Bean
    public CassandraOperations cassandraOperations() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}
