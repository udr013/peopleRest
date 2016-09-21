package nl.programit.people.service;

import nl.programit.people.domain.Customer;
import nl.programit.people.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by udr013 on 22-9-16.
 */
@Service // if not annotated it will not be recognized by CustomerEndPoint @Autowired
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);

    }

    public Iterable<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
}
