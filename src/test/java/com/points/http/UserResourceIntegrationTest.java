package com.points.http;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResourceIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<PointsApiConfiguration> RULE =
            new DropwizardAppRule<>(PointsApi.class, "src/main/resources/api.yml");

    @Test
    public void loginHandlerRedirectsAfterPost() {

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

        ClientResponse response = client.target(
                String.format("http://localhost:%d/users", RULE.getLocalPort()))
                .request()
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);
    }

}
