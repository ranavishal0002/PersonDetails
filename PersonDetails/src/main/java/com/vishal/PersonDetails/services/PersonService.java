package com.vishal.PersonDetails.services;

import com.vishal.PersonDetails.advices.ApiResponse;
import com.vishal.PersonDetails.dtos.PersonDTO;
import com.vishal.PersonDetails.entities.Person;
import com.vishal.PersonDetails.entities.PersonAddress;
import com.vishal.PersonDetails.exceptions.PersonNotFoundException;
import com.vishal.PersonDetails.repositories.PersonAddressRepository;
import com.vishal.PersonDetails.repositories.PersonRepository;
import com.vishal.PersonDetails.utils.PersonDeletedDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonAddressRepository addressRepository;

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository personRepository, PersonAddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public PersonDTO createNewPerson(PersonDTO personDTO) {

        try {

            PersonAddress inputAddress = PersonAddress.builder()
                    .street(personDTO.getStreet())
                    .state(personDTO.getState())
                    .build();
            PersonAddress savedAddress = addressRepository.save(inputAddress);

            logger.info("Person's Address Saved Successfully");

            Person inputPerson = Person.builder()
                    .name(personDTO.getName())
                    .age(personDTO.getAge())
                    .address(savedAddress)
                    .build();

            Person savedPerson = personRepository.save(inputPerson);

            logger.info("Person's Details Saved Successfully");


            return PersonDTO.builder()
                    .id(savedPerson.getPersonId())
                    .name(savedPerson.getName())
                    .age(savedPerson.getAge())
                    .street(savedAddress.getStreet())
                    .state(savedAddress.getState())
                    .build();
        } catch(Exception e) {
            logger.error("Failed to save Person's Detail");
            throw new RuntimeException(e);
        }
    }

    public PersonDTO getPersonById(Integer id) {


        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            return PersonDTO.builder()
                    .id(person.getPersonId())
                    .name(person.getName())
                    .age(person.getAge())
                    .street(person.getAddress().getStreet())
                    .state(person.getAddress().getState())
                    .build();

        } else {
            logger.error("Person does not exist with id " + id);
            throw new PersonNotFoundException("Person Does Not Exist with id: " + id);
        }
    }

    public PersonDeletedDTO deletePersonById(Integer id) {

        if(personRepository.existsById(id)) {
            personRepository.deleteById(id);
            logger.info("Person Deleted with id: {}", id);
            return PersonDeletedDTO.builder()
                    .deletedId(id)
                    .message("Person Deleted with id: " + id)
                    .build();
        } else {
            logger.error("Person cannot be deleted with id {} because person does not exist", id);
            throw new PersonNotFoundException("Person Does Not Exist with id: " + id);
        }
    }

}
