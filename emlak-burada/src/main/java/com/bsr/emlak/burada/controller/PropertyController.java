package com.bsr.emlak.burada.controller;

import com.bsr.emlak.burada.service.PropertyService;
import com.bsr.emlak.commons.dto.request.PropertyRequestDTO;
import com.bsr.emlak.commons.entity.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(value = "/property")
    public ResponseEntity<Property> saveProperty(@RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(propertyService.saveProperty(propertyRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/property/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable("id") Long id) {
        return new ResponseEntity<>(propertyService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

}
