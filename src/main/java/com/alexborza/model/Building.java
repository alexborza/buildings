package com.alexborza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Building {

    @Id
    @SequenceGenerator(
            name = "building_id_sequence",
            sequenceName = "building_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "building_id_sequence"
    )
    private Integer id;

    private String name;
    private String street;
    private Integer number;
    private Integer postalCode;
    private String city;
    private String country;
    private String description;
    private Double longitude;
    private Double latitude;

    public String getAddress() {
        return String.join(", ", street, number.toString(), city, country, postalCode.toString());
    }
}
