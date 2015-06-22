package no.nb.microservices.catalogmetadata.core.metadata.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import no.nb.microservices.catalogmetadata.core.model.FieldsModel;
import no.nb.microservices.catalogmetadata.domain.CassandraModel;
import no.nb.microservices.catalogmetadata.exception.ModsNotFoundException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.cassandra.core.CassandraOperations;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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

        File modsFile = new File(Paths.get(getClass().getResource("/xml/mods1.xml").toURI()).toString());
        String modsString = FileUtils.readFileToString(modsFile);
        CassandraModel model = new CassandraModel();
        model.setValue(ByteBuffer.wrap(modsString.getBytes()));

        when(cassandraOperations.select(selectEq(select1), eq(CassandraModel.class))).thenReturn(Arrays.asList(model));

        String modsString1 = metadataRepository.getModsStringById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(modsString1);

        verify(cassandraOperations).select(selectEq(select1), eq(CassandraModel.class));
        verifyNoMoreInteractions(cassandraOperations);
    }

    @Test
    public void testGetFieldsById() throws Exception {
        Select select1 = QueryBuilder.select().from("expressionrecord");
        select1.where(QueryBuilder.eq("key","bfa3324befaa4518b581125fd701900e")).and(QueryBuilder.in("column1", "fields", "contentClasses", "metadataClasses"));


        CassandraModel fields = new CassandraModel();
        CassandraModel contentClasses = new CassandraModel();
        CassandraModel metadataClasses = new CassandraModel();

        fields.setValue(ByteBuffer.wrap("[{\"name\":\"digital\",\"value\":\"Ja\"}]".getBytes()));
        fields.setColumn1(ByteBuffer.wrap("fields".getBytes()));
        contentClasses.setValue(ByteBuffer.wrap("[\"restricted\", \"jp2\", \"public\"]".getBytes()));
        contentClasses.setColumn1(ByteBuffer.wrap("contentClasses".getBytes()));
        metadataClasses.setValue(ByteBuffer.wrap("[\"public\"]".getBytes()));
        metadataClasses.setColumn1(ByteBuffer.wrap("metadataClasses".getBytes()));


        when(cassandraOperations.select(selectEq(select1), eq(CassandraModel.class))).thenReturn(Arrays.asList(fields,contentClasses,metadataClasses));

        FieldsModel fm1 = metadataRepository.getFieldsById("bfa3324befaa4518b581125fd701900e");

        verify(cassandraOperations).select(selectEq(select1), eq(CassandraModel.class));
        verifyNoMoreInteractions(cassandraOperations);
    }

    @Test
    public void testGetStructById() throws Exception {
        Select select = QueryBuilder.select().from("expressionrecord");
        select.where(QueryBuilder.eq("key", "bfa3324befaa4518b581125fd701900e")).and(QueryBuilder.eq("column1", "structure"));

        File structFile = new File(Paths.get(getClass().getResource("/xml/struct1.xml").toURI()).toString());
        String structString = FileUtils.readFileToString(structFile);
        CassandraModel model = new CassandraModel();
        model.setValue(ByteBuffer.wrap(structString.getBytes()));

        when(cassandraOperations.select(selectEq(select), eq(CassandraModel.class))).thenReturn(Arrays.asList(model));
        String struct = metadataRepository.getStructById("bfa3324befaa4518b581125fd701900e");
        assertNotNull(struct);

        verify(cassandraOperations).select(selectEq(select), eq(CassandraModel.class));
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
