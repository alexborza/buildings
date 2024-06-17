package com.alexborza.repository;

import com.alexborza.model.Building;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@AllArgsConstructor
public class BuildingRepository implements Buildings {

    private final SdjBuildings sdjBuildings;

    @Override
    public Building save(Building building) {
        return sdjBuildings.saveAndFlush(building);
    }

    @Override
    public void saveAll(List<Building> buildings) {
        sdjBuildings.saveAll(buildings);
    }

    @Override
    public Building getOrThrow(Integer id) {
        return sdjBuildings.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Building with id: " + id + " does not exist"));
    }

    @Override
    public Building getReferenceById(Integer id) {
        return sdjBuildings.getById(id);
    }

    @Override
    public List<Building> getAll() {
        return sdjBuildings.findAll();
    }

    @Override
    public void delete(Integer id) {
        sdjBuildings.deleteById(id);
    }
}
