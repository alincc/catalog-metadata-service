package no.nb.microservices.catalogmetadata.core.metadata.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import no.nb.microservices.catalogmetadata.domain.Model;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.cassandra.core.CassandraOperations;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CassandraMetadataRepositoryTest {
    @Mock
    private CassandraOperations cassandraOperations;

    private IMetadataRepository metadataRepository;

    @Before
    public void setup() {
        metadataRepository = new CassandraMetadataRepository(cassandraOperations);
    }

    @Test
    public void testGetModsStringById() throws Exception {
        Select select1 = QueryBuilder.select().from("expressionrecord");
        select1.where(QueryBuilder.eq("key","bfa3324befaa4518b581125fd701900e")).and(QueryBuilder.eq("column1", "modsRecord"));

        Select select2 = QueryBuilder.select().from("expressionrecord");
        select2.where(QueryBuilder.eq("key","bogusid")).and(QueryBuilder.eq("column1", "modsRecord"));

        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        Model model = new Model();
        model.setValue(ByteBuffer.wrap(modsString.getBytes()));

        when(cassandraOperations.select(selectEq(select1), eq(Model.class))).thenReturn(Arrays.asList(model));
        when(cassandraOperations.select(selectEq(select2), eq(Model.class))).thenReturn(new ArrayList<>());


        String modsString1 = metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e");
        String modsString2 = metadataRepository.getModsStringById("bogusid");
        assertNotNull(modsString1);
        assertNull(modsString2);

        verify(cassandraOperations).select(selectEq(select1), eq(Model.class));
        verify(cassandraOperations).select(selectEq(select2), eq(Model.class));
        verifyNoMoreInteractions(cassandraOperations);
    }

    @Test
    public void testGetFieldsById() throws Exception {
        Select select1 = QueryBuilder.select().from("expressionrecord");
        select1.where(QueryBuilder.eq("key","bfa3324befaa4518b581125fd701900e")).and(QueryBuilder.eq("column1", "fields"));

        Select select2 = QueryBuilder.select().from("expressionrecord");
        select2.where(QueryBuilder.eq("key","bogusid")).and(QueryBuilder.eq("column1", "fields"));

        File fieldsFile = new File(Paths.get(getClass().getResource("/json/fields1.json").toURI()).toString());
        String fieldsString = FileUtils.readFileToString(fieldsFile);
        Model model = new Model();
        model.setValue(ByteBuffer.wrap(fieldsString.getBytes()));

        when(cassandraOperations.select(selectEq(select1), eq(Model.class))).thenReturn(Arrays.asList(model));
        when(cassandraOperations.select(selectEq(select2), eq(Model.class))).thenReturn(new ArrayList<>());

        String fields1 = metadataRepository.getFieldsById("bfa3324befaa4518b581125fd701900e");
        String fields2 = metadataRepository.getFieldsById("bogusid");
        assertNotNull(fields1);
        assertNull(fields2);

        verify(cassandraOperations).select(selectEq(select1), eq(Model.class));
        verify(cassandraOperations).select(selectEq(select2), eq(Model.class));
        verifyNoMoreInteractions(cassandraOperations);
    }

    static class SelectMatcher extends ArgumentMatcher<Select> {
        private final Select expected;

        SelectMatcher(Select expected) {
            this.expected = expected;
        }

        @Override
        public boolean matches(Object actual) {
            if (expected == null || actual == null) return false;
            return (actual).toString().equals(expected.toString());
        }
    }

    static Select selectEq(Select expected) {
        return argThat(new SelectMatcher(expected));
    }

}
