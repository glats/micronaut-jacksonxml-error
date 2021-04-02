package me.jics;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@MicronautTest
public class MainControllerTest {

    @Inject
    @Client("/main")
    RxHttpClient client;

    @Test
    public void testMain() throws Exception {
        HttpRequest<?> request = HttpRequest.POST("/", this.getRequest())
                .header("Authorization", "Bearer eyJhdWQiOiJ0ZXN0IiwiaXNzIjoidGVzdCIsInN1YiI6InRlc3QiLCJleHAiOjExMTExMTEsImlhdCI6MTExMTExMTEsImRhdGEiOiJ0ZXN0In0=");
        HttpResponse<?> response = client.toBlocking().exchange(request, Argument.of(Response.class));
        log.info(String.valueOf(response.getBody()));
        assertEquals(response.code(), HttpStatus.OK.getCode());
        assertEquals(response.status(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGeneral() throws Exception {
        HttpRequest<?> request = HttpRequest.POST("/general", this.getRequest())
                .header("Authorization", "Bearer eyJhdWQiOiJ0ZXN0IiwiaXNzIjoidGVzdCIsInN1YiI6InRlc3QiLCJleHAiOjExMTExMTEsImlhdCI6MTExMTExMTEsImRhdGEiOiJ0ZXN0In0=");
        HttpResponse<?> response = client.toBlocking().exchange(request, Argument.of(Response.class));
        log.info(String.valueOf(response.getBody()));
        assertEquals(response.code(), HttpStatus.OK.getCode());
        assertEquals(response.status(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void testPrincipal() throws Exception {
        HttpRequest<?> request = HttpRequest.POST("/principal", this.getRequest())
                .header("Authorization", "Bearer eyJhdWQiOiJ0ZXN0IiwiaXNzIjoidGVzdCIsInN1YiI6InRlc3QiLCJleHAiOjExMTExMTEsImlhdCI6MTExMTExMTEsImRhdGEiOiJ0ZXN0In0=");
        HttpResponse<?> response = client.toBlocking().exchange(request, Argument.of(Response.class));
        log.info(String.valueOf(response.getBody()));
        assertEquals(response.code(), HttpStatus.OK.getCode());
        assertEquals(response.status(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    private Request getRequest() {
        return Request.builder()
                .info("info")
                .build();
    }
}
