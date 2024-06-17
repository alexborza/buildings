package com.alexborza.repository;

import com.alexborza.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SdjBuildings extends JpaRepository<Building, Integer> {
}
