package com.nttdata.app;


import com.nttdata.app.webservice.person.PersonWebServiceImpl;
import com.nttdata.app.webservice.user.UserWebService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class APIApp extends Application {
    private Set<Object> singletons;
    public APIApp() {
        singletons = new HashSet<Object>();
        singletons.add(new PersonWebServiceImpl());
        singletons.add(new UserWebService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}