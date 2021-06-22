package kr.update.resources;

import kr.update.entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/users") // API Path
@Produces(MediaType.APPLICATION_JSON) // OUT
@Consumes(MediaType.APPLICATION_JSON) // IN
public class UserResource {

    private final Collection<User> users = Collections.synchronizedCollection(new ArrayList<>());

    @POST // METHOD
    public User create() {
        User user = new User();
        users.add(new User());

        return user;
    }

    @DELETE
    @Path("{id}")
    public String delete(String id) {
        Optional<User> maybeUser = users.stream().filter(item -> item.getId().equals(id)).findFirst();
        if (maybeUser.isEmpty()) {
            throw new RuntimeException("user_not_found");
        }

        User user = maybeUser.get();

        users.remove(user);

        return "OK";
    }


}
