package nl.programit.people.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import nl.programit.people.domain.Person;

@Component
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByFirstName(String firstName);
}