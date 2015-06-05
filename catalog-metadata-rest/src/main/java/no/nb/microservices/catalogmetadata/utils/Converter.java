package no.nb.microservices.catalogmetadata.utils;

import java.nio.charset.Charset;

public class Converter {
    private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

    private Converter() {
    }

    public static String string(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return new String(bytes, DEFAULT_ENCODING);
    }
}
