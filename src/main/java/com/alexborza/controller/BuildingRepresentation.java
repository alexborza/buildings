package com.alexborza.controller;

import com.alexborza.model.Building;

public record BuildingRepresentation(Integer id,
                                     String name,
                                     String street,
                                     Integer number,
                                     Integer postalCode,
                                     String city,
                                     String country,
                                     String description,
                                     Double longitude,
                                     Double latitude) {

    public BuildingRepresentation(Building building) {
        this(building.getId(),
                building.getName(),
                building.getStreet(),
                building.getNumber(),
                building.getPostalCode(),
                building.getCity(),
                building.getCountry(),
                building.getDescription(),
                building.getLongitude(),
                building.getLatitude());
    }
}
