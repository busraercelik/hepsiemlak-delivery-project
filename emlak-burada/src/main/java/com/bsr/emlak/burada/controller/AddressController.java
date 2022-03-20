package com.bsr.emlak.burada.controller;

import com.bsr.emlak.burada.service.AddressService;
import com.bsr.emlak.commons.dto.request.AddressRequestDTO;
import com.bsr.emlak.commons.entity.Address;
import com.bsr.emlak.commons.transformers.AddressTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService addressService;
    private final AddressTransformer address;

    @Autowired
    public AddressController(AddressService addressService, AddressTransformer address) {
        this.addressService = addressService;
        this.address = address;
    }

    @GetMapping(value = "/address/{id}")
    public ResponseEntity<Address> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/address")
    public ResponseEntity<Address> saveAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        return new ResponseEntity<>(addressService.saveAddress(address.transform(addressRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
}
