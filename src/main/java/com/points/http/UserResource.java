package com.points.http;

import com.codahale.metrics.annotation.Timed;
import com.points.model.User;
import com.points.repository.Repository;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
        return Response.status(CREATED).entity(user).build();
    }

    @Path("/{id}")
    @GET
    public Response read(@PathParam("id") int userId) {
        User user = userRepository.findById(userId);
        return Response.ok(user).build();
    }

}
