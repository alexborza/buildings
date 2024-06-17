package com.alexborza.repository;

import com.alexborza.model.Building;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BuildingRepository.class)
@DataJpaTest
class BuildingRepositoryTest {

    @Autowired
    private BuildingRepository buildingRepository;

    @Test
    void testGetOrThrow() {
        Building building = BuildingGenerator.generateBuildingBuilder().build();
        Integer id = buildingRepository.save(building).getId();

        Building buildingFromDb = buildingRepository.getOrThrow(id);
        assertThat(buildingFromDb.getName()).isEqualTo(building.getName());
        assertThat(buildingFromDb.getPostalCode()).isEqualTo(building.getPostalCode());
    }

    @Test
    void testGetAll() {
        Building b1 = BuildingGenerator.generateBuildingBuilder().build();
        Building b2 = BuildingGenerator.generateBuildingBuilder().build();
        Building b3 = BuildingGenerator.generateBuildingBuilder().build();

        buildingRepository.saveAll(List.of(b1, b2, b3));

        List<Building> buildingFromDb = buildingRepository.getAll();
        assertThat(buildingFromDb).hasSize(3);
    }

    @Test
    void testDelete() {
        Building b1 = BuildingGenerator.generateBuildingBuilder().build();
        Building b2 = BuildingGenerator.generateBuildingBuilder().build();
        Building b3 = BuildingGenerator.generateBuildingBuilder().build();

        Integer idToDelete = buildingRepository.save(b1).getId();
        buildingRepository.save(b2);
        buildingRepository.save(b3);

        buildingRepository.delete(idToDelete);

        List<Building> buildingFromDb = buildingRepository.getAll();
        assertThat(buildingFromDb).hasSize(2);
    }

}