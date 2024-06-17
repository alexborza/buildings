package com.alexborza.service;

import com.alexborza.controller.BuildingRepresentation;
import com.alexborza.controller.BuildingSaveRequestGenerator;
import com.alexborza.repository.BuildingGenerator;
import com.alexborza.repository.Buildings;
import com.alexborza.restclient.GeoapifyClient;
import com.alexborza.restclient.GeoapifyResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {

    @InjectMocks
    private BuildingService buildingService;

    @Mock
    private GeoapifyClient geoapifyClient;


    @Mock
    private Buildings buildings;

    @Test
    void testSaveBuildings() {
        when(geoapifyClient.search(any(), any(), any())).thenReturn(
                new GeoapifyResponse(List.of(new GeoapifyResponse.Result(23.5, 24.6)))
        );

        buildingService.saveAll(List.of(BuildingSaveRequestGenerator.generateSaveRequest()));

        verify(buildings).saveAll(any());
    }

    @Test
    void getAllBuildings() {
        when(buildings.getAll()).thenReturn(List.of(
                BuildingGenerator.generateBuildingBuilder().name("Building 1").build(),
                BuildingGenerator.generateBuildingBuilder().name("Building 2").build()
        ));

        List<BuildingRepresentation> representations = buildingService.getAllBuildings();
        assertThat(representations).hasSize(2);
    }
}