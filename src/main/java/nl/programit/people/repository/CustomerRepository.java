package nl.programit.people.repository;

import nl.programit.people.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by udr013 on 22-9-16.
 */
public interface  CustomerRepository extends CrudRepository<Customer,Long> {


}
