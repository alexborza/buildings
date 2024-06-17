package com.alexborza.controller;

import com.alexborza.repository.BuildingGenerator;
import com.alexborza.service.BuildingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.alexborza.controller.BuildingSaveRequestGenerator.generateSaveRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BuildingController.class)
class BuildingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private BuildingService buildingService;

    @Test
    public void testCreateBuildings() throws Exception {

        List<BuildingSaveRequest> requestBody = List.of(generateSaveRequest());

        mockMvc.perform(post("/api/v1/buildings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testFindAllBuildings() throws Exception {
        when(buildingService.getAllBuildings()).thenReturn(List.of(
                new BuildingRepresentation(BuildingGenerator.generateBuildingBuilder().name("Building 1").build()),
                new BuildingRepresentation(BuildingGenerator.generateBuildingBuilder().name("Building 2").build())
        ));

        mockMvc.perform(get("/api/v1/buildings")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[*].name", contains("Building 1", "Building 2")));
    }
}