package com.nttdata.app.webservice.person;

import com.nttdata.app.utils.Response;
import com.nttdata.app.entity.Person;

public interface PersonWebService {
    public Response addPerson(Person p);
    public Response deletePerson(int id);
    public Person getPerson(int id);
    public Person[] getAllPersons();
}
