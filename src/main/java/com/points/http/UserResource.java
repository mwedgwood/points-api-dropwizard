package com.points.http;

import com.codahale.metrics.annotation.Timed;
import com.points.model.User;
import com.points.repository.Repository;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final Repository<User> userRepository;

    public UserResource(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    @Timed
    public Response index() {
        return Response.ok(userRepository.find()).build();
    }

    @POST
    public Response create(@Valid User user) {
        userRepository.save(user);
        return Response.ok(user).build();
    }

}
