package nl.programit.people.service;

import nl.programit.people.domain.Person;
import nl.programit.people.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void save(Person person) {
        this.personRepository.save(person);
    }

    public Iterable<Person> findAllPersons(){
       return this.personRepository.findAll();

    }
}