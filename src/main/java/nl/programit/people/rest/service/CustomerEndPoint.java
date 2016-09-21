package nl.programit.people.rest.service;

import nl.programit.people.domain.Customer;
import nl.programit.people.domain.Person;
import nl.programit.people.service.CustomerService;
import nl.programit.people.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/customers")
@Component
public class CustomerEndPoint {

    @Autowired
    private CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {

        Iterable<Customer> result = this.customerService.findAllCustomers();
        return Response.ok(result).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public Response post(Customer customer) {
        this.customerService.saveCustomer(customer);
        //returns person object else retuns nothing

        // whats difference between Response.ok  & Response.accepted
        return Response.accepted(customer).build(); // build maakt JSON van resultaat
    }}