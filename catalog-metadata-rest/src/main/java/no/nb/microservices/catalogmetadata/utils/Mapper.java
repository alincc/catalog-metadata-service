package no.nb.microservices.catalogmetadata.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public final class Mapper {
    
    private Mapper() {
        super();
    }
    
    public static List<String> getStringAsList(String str) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        return Arrays.asList(str.split("\\s*,\\s*"));
    }
}
