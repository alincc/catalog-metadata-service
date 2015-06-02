package no.nb.microservices.catalogmetadata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfigTest extends AbstractCassandraConfiguration {
    public static final String KEYSPACE = "localtestcluster";
    public static final String HOST = "localhost";
    public static final int PORT = 9142;
    public static final int THRIFT_PORT = 9171;

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    protected int getPort() {
        return PORT;
    }

    @Override
    protected String getContactPoints() {
        return HOST;
    }
}
