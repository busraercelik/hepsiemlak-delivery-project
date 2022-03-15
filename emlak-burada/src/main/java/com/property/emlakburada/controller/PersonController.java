package com.property.emlakburada.controller;

import com.property.emlakburada.dto.PersonRequestDTO;
import com.property.emlakburada.dto.response.PersonResponseDTO;
import com.property.emlakburada.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons")
    public ResponseEntity<List<PersonResponseDTO>> getAllPerson() {
        return new ResponseEntity<>(personService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<?> savePerson(@RequestBody PersonRequestDTO personRequestDTO) {
        personService.savePerson(personRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/person/{personId}")
    public ResponseEntity<PersonResponseDTO> getPersonById(@PathVariable long personId) {
        return new ResponseEntity<>(personService.getPersonById(personId), HttpStatus.OK);
    }
}
