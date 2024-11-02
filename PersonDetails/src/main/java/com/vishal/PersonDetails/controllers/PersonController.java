package com.vishal.PersonDetails.controllers;

import com.vishal.PersonDetails.advices.ApiResponse;
import com.vishal.PersonDetails.dtos.PersonDTO;
import com.vishal.PersonDetails.services.PersonService;
import com.vishal.PersonDetails.utils.PersonDeletedDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createNewPerson(@RequestBody @Valid PersonDTO inputPerson) {
        PersonDTO personDTO = personService.createNewPerson(inputPerson);
        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Integer personId) {
        PersonDTO personDTO = personService.getPersonById(personId);
        return ResponseEntity.ok(personDTO);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<PersonDeletedDTO> deletePersonById(@PathVariable Integer personId) {
        PersonDeletedDTO deletedPerson = personService.deletePersonById(personId);
        return ResponseEntity.ok(deletedPerson);
    }
}
