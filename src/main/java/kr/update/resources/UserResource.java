package kr.update.resources;

import kr.update.domain.User;
import kr.update.resources.requests.user.CreateUser;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.time.LocalDateTime;
import java.util.*;

@Path("/users") // API Path
@Produces(MediaType.APPLICATION_JSON) // OUT
@Consumes(MediaType.APPLICATION_JSON) // IN
public class UserResource {

    private int index = 1;
    private final Collection<User> users = Collections.synchronizedCollection(new ArrayList<>());

    @GET
    public Collection<User> list() {
        return users;
    }

    @GET
    @Path("{id}")
    public User load(@PathParam("id") int id) {
        Optional<User> maybeUser = users.stream().filter(item -> item.getId() == id).findFirst();
        if (maybeUser.isEmpty()) {
            throw new RuntimeException("user_not_found");
        }

        return maybeUser.get();
    }


    @POST // METHOD
    public Response create(@Valid CreateUser body) {
        User user = User.builder()
                .id(index)
                .name(body.getName())
                .username(body.getUsername())
                .createdAt(LocalDateTime.now())
                .build();

        users.add(user);

        index += 1;
        return Response.created(
                UriBuilder.fromResource(UserResource.class)
                        .path(String.valueOf(user.getId()))
                        .build()
        ).entity(user)
                .build();
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") int id) {
        Optional<User> maybeUser = users.stream().filter(item -> item.getId() == id).findFirst();
        if (maybeUser.isEmpty()) {
            throw new RuntimeException("user_not_found");
        }

        User user = maybeUser.get();

        users.remove(user);

        return "OK";
    }


}
