package com.bsr.emlak.burada.controller;

import com.bsr.emlak.burada.service.AdvertService;
import com.bsr.emlak.commons.dto.request.EmlakUserRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.service.EmlakUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private final EmlakUserService emlakUserService;
    private final AdvertService advertService;

    public UserController(EmlakUserService emlakUserService, AdvertService advertService) {
        this.emlakUserService = emlakUserService;
        this.advertService = advertService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<EmlakUser>> getAllEmlakUsers() {
        return new ResponseEntity<>(emlakUserService.getAllEmlakUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<?> saveEmlakUser(@RequestBody EmlakUserRequestDTO emlakUserRequestDTO) {
        return new ResponseEntity<>(emlakUserService.saveEmlakUser(emlakUserRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<EmlakUser> getEmlakUserById(@PathVariable long userId) {
        return new ResponseEntity<>(emlakUserService.getEmlakUserById(userId), HttpStatus.OK);
    }


    @GetMapping(value = "/user/{userId}/adverts")
    public ResponseEntity<List<Advert>> getFavouriteAdverts(@PathVariable long userId) {
        return new ResponseEntity<>(advertService.getAllFavouriteAdverts(userId) , HttpStatus.OK);
    }
}
