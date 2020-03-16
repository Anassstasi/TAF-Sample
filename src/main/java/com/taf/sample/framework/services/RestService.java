package com.taf.sample.framework.services;

import com.taf.sample.framework.managers.RestManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for system-based operations (ex. sending requests to system endpoints).
 */
@Service
@Slf4j
public class RestService {

    @Value("#{@properties.getProperty('endpoint.domainService')}")
    private String domainServiceEndpoint;
    @Value("#{@properties.getProperty('endpoint.domain')}")
    private String domainEndpoint;

    @Autowired
    private RestManager restManager;

    /**
     * Starts domain job.
     */
    public Response startDomainJob() {
        log.info("Starting domain job.");
        return restManager.sendGetRequest(domainEndpoint, ContentType.XML);
    }

    /**
     * Sends a GET request to domain service.
     *
     * @param mongoEntityId mongo Entity ID.
     * @return response.
     */
    public Response sendGetRequestToDomainService(String mongoEntityId) {
        log.info(String.format("Sending GET request to  domain service with mongo entity Id [%s]", mongoEntityId));
        return restManager.sendGetRequest(domainServiceEndpoint.replace("{mongoEntityId}", mongoEntityId), ContentType.XML);
    }
}
