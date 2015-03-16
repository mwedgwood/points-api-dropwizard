package com.points.http;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResourceIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<PointsApiConfiguration> RULE =
            new DropwizardAppRule<>(PointsApi.class, "src/main/resources/api.yml");

    @Test
    public void testReadUsers() {

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

        Response response = client.target(String.format("http://localhost:%d/users", RULE.getLocalPort()))
                .request()
                .get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);
    }

}
