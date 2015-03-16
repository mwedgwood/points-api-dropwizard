package com.points.http;

import com.points.model.AuthToken;
import com.points.model.User;
import io.dropwizard.auth.Auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth/tokens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TokenResource {

    @GET
    public Response read(@Auth User user) {
        return Response.ok(new AuthToken()).build();
    }

}
