package com.taf.sample.framework.managers;

import com.taf.sample.framework.exceptions.FileException;
import com.taf.sample.framework.utils.FileUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

@Component
@Slf4j
public class RestManager {
    /**
     * Sends POST request to URL.
     *
     * @param requestURL  URL.
     * @param requestBody body request.
     * @return response.
     */
    public Response sendPostRequest(String requestURL, String requestBody, ContentType contentType) {
        log.info("Sending POST request to {} with request body {}", requestURL, requestBody);
        return given()
                .header(HTTP.CONTENT_TYPE, contentType)
                .body(requestBody)
                .post(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * Sends POST request to URL.
     *
     * @param requestURL URL.
     * @param file       file which consists of body request.
     * @return response.
     */
    public Response sendPostRequest(String requestURL, File file, ContentType contentType) throws IOException, FileException {
        String bodyRequest = FileUtil.readFile(file);
        log.info("Sending POST request to {} with request body {}", requestURL, bodyRequest);
        return given()
                .header(HTTP.CONTENT_TYPE, contentType)
                .body(bodyRequest)
                .post(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * Sends POST request to URL.
     *
     * @param requestURL URL.
     * @param file       file which consists of body request.
     * @return response.
     */
    public Response sendPostRequestWithAuth(String requestURL, File file, ContentType contentType, String username, String password) throws IOException, FileException {
        String bodyRequest = FileUtil.readFile(file);
        log.info("Sending POST request to {} with request body {}", requestURL, bodyRequest);
        return given()
                .auth()
                .basic(username, password)
                .header(HTTP.CONTENT_TYPE, contentType)
                .body(bodyRequest)
                .post(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * Sends GET request to URL.
     *
     * @param requestURL URL.
     * @return response.
     */
    public Response sendGetRequestWithAuth(String requestURL, ContentType contentType, String username, String password) {
        log.info("Sending GET request to {}", requestURL);
        return given()
                .auth()
                .basic(username, password)
                .header(HTTP.CONTENT_TYPE, contentType)
                .get(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * Sends GET request to URL.
     *
     * @param requestURL URL.
     * @return response.
     */
    public Response sendGetRequest(String requestURL, ContentType contentType) {
        log.info("Sending GET request to {}", requestURL);
        return given()
                .header(HTTP.CONTENT_TYPE, contentType)
                .get(requestURL)
                .then()
                .log().all()
                .extract().response();
    }
}
