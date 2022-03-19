package com.bsr.emlak.burada.controller;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.burada.service.EmlakUserService;
import com.bsr.emlak.commons.entity.EmlakUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private EmlakUserService emlakUserService;

    @GetMapping(value = "/persons")
    public ResponseEntity<List<EmlakUser>> getAllPerson() {
        return new ResponseEntity<>(emlakUserService.getAllEmlakUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<?> savePerson(@RequestBody EmlakUserRequestDTO emlakUserRequestDTO) {
        emlakUserService.saveEmlakUser(emlakUserRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/person/{personId}")
    public ResponseEntity<EmlakUser> getPersonById(@PathVariable long personId) {
        return new ResponseEntity<>(emlakUserService.getEmlakUserById(personId), HttpStatus.OK);
    }
}
