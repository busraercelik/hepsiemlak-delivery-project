package com.property.emlakburada.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PropertySearchDTO {
    private String city;
    private Integer minPrice;
    private Integer maxPrice;
    private PropertyType propertyType;
    private List<String> noOfRooms;

    public PropertySearchDTO city(String city) {
        this.city = city;
        return this;
    }

    public PropertySearchDTO minPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public PropertySearchDTO maxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public PropertySearchDTO propertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public PropertySearchDTO noOfRooms(List<String> noOfRooms) {
        this.noOfRooms = noOfRooms;
        return this;
    }

    public static PropertySearchDTO from() {
        return new PropertySearchDTO();
    }
}
