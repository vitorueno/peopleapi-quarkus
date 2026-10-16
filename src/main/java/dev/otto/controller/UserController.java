package dev.otto.controller;

import dev.otto.domain.Users;
import dev.otto.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Transactional
    public Response findAllUsers(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAllUsers(page, pageSize);
        return Response.ok(users).build();
    }

    @POST
    @Transactional
    public Response createUser(Users users) {
        return Response
                .ok(userService.createUser(users)).build();
    }

    @GET
    @Path("/{id}")
    public Response findUserById(@PathParam("id")UUID userId) {
        return Response
                .ok(userService.findUsersById(userId)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateUser(@PathParam("id")UUID userId, Users users) {
        return Response
                .ok(userService.updateUser(userId, users)).build();
    }


    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id")UUID userId) {
        userService.deleteUser(userId);
        return Response
                .noContent().build();
    }
}
