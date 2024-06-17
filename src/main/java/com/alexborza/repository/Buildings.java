package com.alexborza.repository;

import com.alexborza.model.Building;

import java.util.List;

public interface Buildings {
    Building save(Building building);
    void saveAll(List<Building> buildings);
    Building getOrThrow(Integer id);
    Building getReferenceById(Integer id);
    List<Building> getAll();
    void delete(Integer id);
}
