package com.alexborza.service;

import com.alexborza.controller.BuildingRepresentation;
import com.alexborza.controller.BuildingSaveRequest;
import com.alexborza.model.Building;
import com.alexborza.repository.Buildings;
import com.alexborza.restclient.GeoapifyClient;
import com.alexborza.restclient.GeoapifyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BuildingService {

    private final Buildings buildings;
    private final GeoapifyClient geoapifyClient;
    private final String apiKey;

    public BuildingService(Buildings buildings,
                           GeoapifyClient geoapifyClient,
                           @Value("${geoapify.api-key}") String apiKey) {
        this.buildings = buildings;
        this.geoapifyClient = geoapifyClient;
        this.apiKey = apiKey;
    }

    public void saveAll(List<BuildingSaveRequest> buildingSaveRequest) {
        List<Building> buildingList = buildingSaveRequest.stream()
                .map(this::toBuilding)
                .toList();

        buildings.saveAll(buildingList);
    }

    public void update(Integer buildingId, BuildingSaveRequest buildingSaveRequest) {
        Building building = buildings.getReferenceById(buildingId);

        building.setName(buildingSaveRequest.name());
        building.setStreet(buildingSaveRequest.street());
        building.setNumber(buildingSaveRequest.number());
        building.setPostalCode(buildingSaveRequest.postalCode());
        building.setCity(buildingSaveRequest.city());
        building.setCountry(buildingSaveRequest.country());
        building.setDescription(buildingSaveRequest.description());

        GeoapifyResponse search = geoapifyClient.search(building.getAddress(), "json", apiKey);
        search.results().stream().findFirst().ifPresent(result -> {
            building.setLongitude(result.lon());
            building.setLatitude(result.lat());
        });

        buildings.save(building);
    }

    public void delete(Integer buildingId) {
        buildings.delete(buildingId);
    }

    public BuildingRepresentation getBuilding(Integer id) {
        Building building = buildings.getOrThrow(id);
        return new BuildingRepresentation(building);
    }

    public List<BuildingRepresentation> getAllBuildings() {
        return buildings.getAll().stream()
                .map(BuildingRepresentation::new)
                .toList();
    }

    private Building toBuilding(BuildingSaveRequest buildingSaveRequest) {
        Building building = Building.builder()
                .name(buildingSaveRequest.name())
                .street(buildingSaveRequest.street())
                .number(buildingSaveRequest.number())
                .postalCode(buildingSaveRequest.postalCode())
                .city(buildingSaveRequest.city())
                .country(buildingSaveRequest.country())
                .description(buildingSaveRequest.description())
                .build();

        GeoapifyResponse search = geoapifyClient.search(building.getAddress(), "json", apiKey);
        search.results().stream().findFirst().ifPresent(result -> {
            building.setLongitude(result.lon());
            building.setLatitude(result.lat());
        });

        return building;
    }
}
