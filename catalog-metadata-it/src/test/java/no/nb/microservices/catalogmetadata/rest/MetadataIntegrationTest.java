package no.nb.microservices.catalogmetadata.rest;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import loc.gov.marc.RecordType;
import no.nb.commons.web.util.UserUtils;
import no.nb.microservices.catalogmetadata.Application;
import no.nb.microservices.catalogmetadata.config.RibbonClientConfiguration;
import no.nb.microservices.catalogmetadata.model.fields.FieldResource;
import no.nb.microservices.catalogmetadata.model.mods.v3.Mods;
import no.nb.microservices.catalogmetadata.model.struct.StructMap;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.commons.io.IOUtils;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RibbonClientConfiguration.class})
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class MetadataIntegrationTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    ILoadBalancer lb;
    private HttpHeaders headers;

    @Test
    public void whenModsIsFoundResponseShouldBeOk() throws Exception {
        ResponseEntity<Mods> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/c3c9844de9cb027e003021b1aadeae6c/mods", HttpMethod.GET,
                new HttpEntity<Void>(headers), Mods.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));
    }

    @Test
    public void whenUsingUrnInsteadOfSesamidAndModsIsFoundResponseShouldBeOk() throws Exception {
        ResponseEntity<Mods> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/URN:NBN:no-nb_digibok_2014091948005/mods", HttpMethod.GET,
                new HttpEntity<Void>(headers), Mods.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));
    }

    @Test
    public void whenModsNotFoundResponseShouldBeNotFound() throws Exception {
        ResponseEntity<Mods> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/bogusid/mods", HttpMethod.GET,
                new HttpEntity<Void>(headers), Mods.class);

        assertThat("Status code should be 404 ", entity.getStatusCode().value(), is(404));
    }

    @Test
    public void whenModsIsFoundAndConvertedToMarcResponseShouldBeOk() throws Exception {
        ResponseEntity<RecordType> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/c3c9844de9cb027e003021b1aadeae6c/marcxml", HttpMethod.GET,
                new HttpEntity<Void>(headers), RecordType.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));

    }

    @Test
    public void whenUsingUrnInsteadOfSesamidAndModsIsFoundAndConvertedToMarcResponseShouldBeOk() throws Exception {
        ResponseEntity<RecordType> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/URN:NBN:no-nb_digibok_2014091948005/marcxml", HttpMethod.GET,
                new HttpEntity<Void>(headers), RecordType.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));

    }

    @Test
    public void whenModsIsNotFoundWhenConvertingToMarcResponseShouldBeNotFound() throws Exception {
        ResponseEntity<RecordType> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/bogusid/marcxml", HttpMethod.GET,
                new HttpEntity<Void>(headers), RecordType.class);

        assertThat("Status code should be 404 ", entity.getStatusCode().value(), is(404));
    }

    @Test
    public void whenFieldsIsFoundResponseShouldBeOk() throws Exception {
        ResponseEntity<FieldResource> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/c3c9844de9cb027e003021b1aadeae6c/fields", HttpMethod.GET,
                new HttpEntity<Void>(headers), FieldResource.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));
    }

    @Test
    public void whenUsingUrnInsteadOfSesamidAndFieldsIsFoundResponseShouldBeOk() throws Exception {
        ResponseEntity<FieldResource> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/URN:NBN:no-nb_digibok_2014091948005/fields", HttpMethod.GET,
                new HttpEntity<Void>(headers), FieldResource.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));
    }

    @Test
    public void whenFieldsIsNotFoundResponseShouldBeNotFound() throws Exception {
        ResponseEntity<FieldResource> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/bogusid/fields", HttpMethod.GET,
                new HttpEntity<Void>(headers), FieldResource.class);

        assertThat("Status code should be 404 ", entity.getStatusCode().value(), is(404));
    }

    @Test
    public void whenStructureIsFoundResponseShouldBeOk() throws Exception {
        ResponseEntity<StructMap> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/c3c9844de9cb027e003021b1aadeae6c/struct", HttpMethod.GET,
                new HttpEntity<Void>(headers), StructMap.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));
    }

    @Test
    public void whenUsingUrnInsteadOfSesamidAndStructureIsFoundResponseShouldBeOk() throws Exception {
        ResponseEntity<StructMap> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/URN:NBN:no-nb_digibok_2014091948005/struct", HttpMethod.GET,
                new HttpEntity<Void>(headers), StructMap.class);

        assertThat("Status code should be 200 ", entity.getStatusCode().value(), is(200));
    }

    @Test
    public void whenStructureIsNotFoundResponseShouldBeNotFound() throws Exception {
        ResponseEntity<StructMap> entity = new TestRestTemplate().exchange(
                "http://localhost:" + port + "/catalog/v1/metadata/bogusid/struct", HttpMethod.GET,
                new HttpEntity<Void>(headers), StructMap.class);

        assertThat("Status code should be 404 ", entity.getStatusCode().value(), is(404));
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
        MockWebServer server = new MockWebServer();
        String searchResultMock = IOUtils.toString(this.getClass().getResourceAsStream("catalog-search-index-service.json"));

        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().startsWith("/catalog/v1/search?q=urn%3A%22URN%3ANBN%3Ano-nb_digibok_2014091948005%22")) {
                    return new MockResponse().setBody(searchResultMock).setResponseCode(200).setHeader("Content-Type", "application/hal+json");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        server.start();

        BaseLoadBalancer blb = (BaseLoadBalancer) lb;
        blb.setServersList(Arrays.asList(new Server(server.getHostName(), server.getPort())));
        headers = createDefaultHeaders();
    }

    @AfterClass
    public static void clearCassandra() {
        try {
            EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
        } catch (Exception ex) {

        }
    }

    private HttpHeaders createDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(UserUtils.SSO_HEADER, "token");
        headers.add(UserUtils.REAL_IP_HEADER, "123.45.100.1");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        return headers;
    }
}
