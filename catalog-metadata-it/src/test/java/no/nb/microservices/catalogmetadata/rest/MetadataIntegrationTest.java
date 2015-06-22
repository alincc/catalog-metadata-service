package no.nb.microservices.catalogmetadata.rest;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import no.nb.microservices.catalogmetadata.Application;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class MetadataIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Test
    public void whenModsIsFoundResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/c3c9844de9cb027e003021b1aadeae6c/mods"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void whenModsNotFoundResponseShouldBeNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bogusid/mods"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
    }

    @Test
    public void whenModsIsFoundAndConvertedToMarcResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/c3c9844de9cb027e003021b1aadeae6c/marcxml"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void whenModsIsNotFoundWhenConvertingToMarcResponseShouldBeNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bogusid/marcxml"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
    }

    @Test
    public void whenFieldsIsFoundResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/c3c9844de9cb027e003021b1aadeae6c/fields"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenFieldsIsNotFoundResponseShouldBeNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bogusid/fields"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
    }

    @Test
    public void whenStructureIsFoundResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/c3c9844de9cb027e003021b1aadeae6c/struct"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenStructureIsNotFoundResponseShouldBeNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bogusid/struct"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra("cassandra.yaml");
        Cluster cluster = new Cluster.Builder().addContactPoint("localhost").withPort(9142).build();
        Session session = cluster.connect();
        CQLDataLoader cqlDataLoader = new CQLDataLoader(session);
        cqlDataLoader.load(new ClassPathCQLDataSet("cassandra.cql"));
    }

    @Before
    public void setup() throws ConfigurationException, IOException, TTransportException {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterClass
    public static void clearCassandra() {
        try {
            EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
        } catch (Exception ex) {

        }

    }
}
