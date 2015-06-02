package no.nb.microservices.catalogmetadata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "catalogmetadata")
public class ApplicationSettings {
    private CassandraSettings cassandra;

    public CassandraSettings getCassandra() {
        return cassandra;
    }

    public void setCassandra(CassandraSettings cassandra) {
        this.cassandra = cassandra;
    }

    public static class CassandraSettings {
        private String host;
        private String keyspace;
        private int port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getKeyspace() {
            return keyspace;
        }

        public void setKeyspace(String keyspace) {
            this.keyspace = keyspace;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
