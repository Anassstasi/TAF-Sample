package com.taf.sample.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taf.sample.configuration.AppConfig;
import com.taf.sample.framework.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Slf4j
@ContextConfiguration(classes = {AppConfig.class})
@Component
public class ConvertUtil {

    private static ObjectMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        mapper = this.objectMapper;
    }

    /**
     * Converts Waiter object to JSON object.
     *
     * @param object object which should be converted.
     * @return JSON.
     */
    public static String toJson(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    /**
     * Converts JSON object to Collection document.
     *
     * @param json either path to JSON object (including filename and extension) or JSON string.
     * @param claz class of collection document to which JSON should be converted.
     * @return Waiter object.
     */
    public static Object toObject(String json, Class claz) throws IOException {
        return json.contains(CommonConstants.JSON_EXTENSION) ? mapper.readValue(new File(json), claz) : mapper.readValue(json, claz);
    }
}