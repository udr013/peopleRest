package nl.programit.people.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import nl.programit.people.rest.service.PersonEndPoint;

@Component
//
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        //this needs to be done for every rest service
        register(PersonEndPoint.class);

        //better you can also register the package:
        packages("nl.programit.people.rest.service");
    }
}