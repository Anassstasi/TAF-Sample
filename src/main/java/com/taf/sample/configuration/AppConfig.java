package com.taf.sample.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.JSch;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Properties;

@Lazy
@Configuration
@ComponentScan(basePackages = "com.taf.sample")
public class AppConfig {

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(name = "softAssert")
    public SoftAssert softAssert() {
        return new SoftAssert();
    }

    @Bean(name = "jsch")
    public JSch jsch() {
        return new JSch();
    }

    @Bean(name = "properties")
    @Scope("singleton")
    public Properties properties() throws IOException {
        String env = System.getProperty("env");
        return PropertiesLoaderUtils.loadAllProperties("application-" + env.toLowerCase() + ".properties");
    }

}
