package no.nb.microservices.catalogmetadata.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andreasb on 14.09.15.
 */
public class Mapper {
    public static List<String> getStringAsList(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Arrays.asList(str.split("\\s*,\\s*"));
    }
}
