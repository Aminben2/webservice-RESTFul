package com.nttdata.app.webservice.person;

import com.nttdata.app.utils.Response;
import com.nttdata.app.entity.Person;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Path("/person")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class PersonWebServiceImpl implements PersonWebService {
  private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

  @Override
  @POST
  @Path("/add")
  public Response addPerson(Person p) {
    Response response = new Response();
    if (persons.get(p.getId()) != null) {
      response.setStatus(false);
      response.setMessage("Person Already Exists");
      return response;
    }
    persons.put(p.getId(), p);
    response.setStatus(true);
    response.setMessage("Person created successfully");
    return response;
  }

  @Override
  @DELETE
  @Path("/{id}/delete")
  public Response deletePerson(@PathParam("id") int id) {
    Response response = new Response();
    if (persons.get(id) == null) {
      response.setStatus(false);
      response.setMessage("Person Doesn't Exists");
      return response;
    }
    persons.remove(id);
    response.setStatus(true);
    response.setMessage("Person deleted successfully");
    return response;
  }

  @Override
  @GET
  @Path("/{id}/get")
  public Person getPerson(@PathParam("id") int id) {
    return persons.get(id);
  }

  @GET
  @Path("/{id}/getDummy")
  public Person getDummyPerson(@PathParam("id") int id) {
    Person p = new Person();
    p.setAge(99);
    p.setName("Dummy");
    p.setId(id);
    return p;
  }

  @Override
  @GET
  @Path("/getAll")
  public Person[] getAllPersons() {
    Person p1 = new Person();
    p1.setName("Ali");
    p1.setId(1);
    p1.setAge(30);
    Person p2 = new Person();
    p2.setName("Ahmed");
    p2.setId(2);
    p2.setAge(25);
    persons.put(p1.getId(), p1);
    persons.put(p2.getId(), p2);
    Set<Integer> ids = persons.keySet();
    Person[] p = new Person[ids.size()];
    int i = 0;
    for (Integer id : ids) {
      p[i] = persons.get(id);
      i++;
    }
    return p;
  }
}
