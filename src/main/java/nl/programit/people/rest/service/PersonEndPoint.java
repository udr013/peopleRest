package nl.programit.people.rest.service;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.programit.people.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.programit.people.domain.Person;


@Path("/people")
@Component
public class PersonEndPoint {

    @Autowired
    private PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {

        Iterable<Person> result = this.personService.findAllPersons();
        return Response.ok(result).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Person person) {
        this.personService.save(person);
        //returns person object else retuns nothing

        // whats difference between Response.ok  & Response.accepted
        return Response.accepted(person).build(); // build maakt JSON van resultaat
    }}