package no.nb.microservices.catalogmetadata.service;

import loc.gov.marc.RecordType;
import loc.gov.mods.ModsType;
import no.nb.microservices.catalogmetadata.repository.CassandraRepository;
import org.apache.commons.io.FileUtils;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.File;
import java.nio.file.Paths;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(EasyMockRunner.class)
public class CassandraServiceTest {

    private Jaxb2Marshaller marshaller;
    private CassandraService cassandraService;
    private CassandraRepository cassandraRepository;
    private TransformerService transformerService;


    @Before
    public void setup() {
        cassandraRepository = createStrictMock(CassandraRepository.class);
        transformerService = new TransformerService();
        marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("loc.gov.mods","loc.gov.marc");
        cassandraService = new CassandraService(marshaller,cassandraRepository,transformerService);
    }


    @Test
    public void testGetMods() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        expect(cassandraRepository.getModsString("c06c5cbe2f82113e7b4757dbb14f8676")).andReturn(modsString);
        expect(cassandraRepository.getModsString("bogusid")).andReturn(null);
        replay(cassandraRepository);

        ModsType mods = cassandraService.getMods("c06c5cbe2f82113e7b4757dbb14f8676");
        assertNotNull(mods);
        assertNull(cassandraService.getMods("bogusid"));
        verify(cassandraRepository);
    }

    @Test
    public void testGetMarcxml() throws Exception {
        File modsFile = new File(Paths.get(getClass().getResource("mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        expect(cassandraRepository.getModsString("c06c5cbe2f82113e7b4757dbb14f8676")).andReturn(modsString);
        expect(cassandraRepository.getModsString("bogusid")).andReturn(null);
        replay(cassandraRepository);

        RecordType marc = cassandraService.getMarcxml("c06c5cbe2f82113e7b4757dbb14f8676");
        assertNotNull(marc);
        assertNull(cassandraService.getMods("bogusid"));
        verify(cassandraRepository);
    }
}
