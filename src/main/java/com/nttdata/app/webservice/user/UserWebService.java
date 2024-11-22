package com.nttdata.app.webservice.user;

import com.nttdata.app.entity.User;
import com.nttdata.app.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/users")
public class UserWebService {

  UserService userService = new UserService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public User[] getUsers() {
    List<User> users;
    try {
      users = userService.getAll();
    } catch (Exception e) {
      return null;
    }
    return users.toArray(User[]::new);
  }

  @GET
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("id") Long id) {
    Optional<User> user;
    try {
      user = userService.getById(id);
      return user.orElseGet(User::new);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new User();
    }
  }

  @POST
  @Path("/add")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User addUser(User user) {
    try {
      return userService.create(user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User updateUser(@PathParam("id") Long id, User user) {
    try {
      return userService.update(id, user).orElse(null);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Boolean deleteUser(@PathParam("id") Long id) {
    try {
      userService.deleteByid(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
