package com.points.http;

import com.codahale.metrics.annotation.Timed;
import com.points.model.Membership;
import com.points.model.User;
import com.points.repository.MembershipRepository;
import com.points.repository.UserRepository;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/users/{userName}/memberships")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembershipResource {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public MembershipResource(UserRepository userRepository, MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    @GET
    @Timed
    public Response index() {
        return Response.ok(membershipRepository.find()).build();
    }

    @POST
    public Response create(@PathParam("userName") String userName, @Valid Membership membership) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        membershipRepository.save(membership.setUserId(user.getId()));
        return Response.created(getLocation(user.getId())).build();
    }

    URI getLocation(Integer userId) {
        return UriBuilder.fromResource(UserResource.class)
                .path(UserResource.class, "read")
                .build(userId);
    }

}
