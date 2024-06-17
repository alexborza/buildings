package com.alexborza.controller;

import com.alexborza.service.BuildingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/buildings")
@AllArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @PostMapping
    public ResponseEntity<Void> saveBuildings(@RequestBody List<BuildingSaveRequest> buildingsSaveRequest) {
        log.info("new building is saved {}", buildingsSaveRequest);
        buildingService.saveAll(buildingsSaveRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{buildingId}")
    public ResponseEntity<Void> updateBuilding(@PathVariable("buildingId") Integer buildingId,
                                                              @RequestBody BuildingSaveRequest buildingSaveRequest) {
        log.info("building with id {} is updated", buildingId);
        buildingService.update(buildingId, buildingSaveRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{buildingId}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable("buildingId") Integer buildingId) {
        log.info("building with id {} is deleted", buildingId);
        buildingService.delete(buildingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{buildingId}")
    public ResponseEntity<BuildingRepresentation> getBuilding(@PathVariable("buildingId") Integer buildingId) {
        BuildingRepresentation representation = buildingService.getBuilding(buildingId);
        return new ResponseEntity<>(representation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BuildingRepresentation>> getAllBuildings() {
        List<BuildingRepresentation> representations = buildingService.getAllBuildings();
        return new ResponseEntity<>(representations, HttpStatus.OK);
    }
}
