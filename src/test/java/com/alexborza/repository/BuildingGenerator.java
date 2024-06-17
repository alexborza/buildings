package com.alexborza.repository;

import com.alexborza.model.Building;

import static com.alexborza.TestConstants.*;

public class BuildingGenerator {
    
    public static Building.BuildingBuilder generateBuildingBuilder() {
        return Building.builder()
                .name(NAME)
                .street(STREET)
                .number(NUMBER)
                .postalCode(POSTAL_CODE)
                .city(CITY)
                .country(COUNTRY)
                .description(DESCRIPTION);
    }
}
