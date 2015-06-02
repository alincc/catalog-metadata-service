package no.nb.microservices.catalogmetadata.domain;

import com.datastax.driver.core.utils.Bytes;
import no.nb.microservices.catalogmetadata.utils.Converter;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.nio.ByteBuffer;

@Table(value = "expressionrecord")
public class Model {
    @PrimaryKey
    private ByteBuffer key;

    @Column(value = "column1")
    private ByteBuffer column1;

    @Column(value = "value")
    private ByteBuffer value;

    public ByteBuffer getKey() {
        return key;
    }

    public void setKey(ByteBuffer key) {
        this.key = key;
    }

    public ByteBuffer getColumn1() {
        return column1;
    }

    public void setColumn1(ByteBuffer column1) {
        this.column1 = column1;
    }

    public ByteBuffer getValue() {
        return value;
    }

    public void setValue(ByteBuffer value) {
        this.value = value;
    }

    public String getValueAsString() {
        return Converter.string(Bytes.getArray(getValue()));
    }
}
