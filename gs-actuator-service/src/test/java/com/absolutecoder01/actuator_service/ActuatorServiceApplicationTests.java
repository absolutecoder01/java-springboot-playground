package com.absolutecoder01.actuator_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.server.port=0"})
@AutoConfigureRestTestClient
class ActuatorServiceApplicationTests {

    @Test
    public void shouldReturn200WhenSendingRequestToController(@Autowired RestTestClient rest) {
        rest.get().uri("/hello-world").exchange().expectStatus().isOk();
    }

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint(@Autowired RestTestClient rest, @LocalManagementPort int port) {
        rest.get().uri("http://localhost:{port}/actuator", port).exchange().expectStatus().isOk();
    }


}
