package com.bsr.emlak.burada.controller;

import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.burada.service.EmlakUserService;
import com.bsr.emlak.commons.entity.EmlakUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private final EmlakUserService emlakUserService;

    public UserController(EmlakUserService emlakUserService) {
        this.emlakUserService = emlakUserService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<EmlakUser>> getAllPerson() {
        return new ResponseEntity<>(emlakUserService.getAllEmlakUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> savePerson(@RequestBody EmlakUserRequestDTO emlakUserRequestDTO) {
        return new ResponseEntity<>(emlakUserService.saveEmlakUser(emlakUserRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<EmlakUser> getPersonById(@PathVariable long userId) {
        return new ResponseEntity<>(emlakUserService.getEmlakUserById(userId), HttpStatus.OK);
    }
}
