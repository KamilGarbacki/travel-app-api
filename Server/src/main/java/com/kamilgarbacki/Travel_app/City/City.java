package com.kamilgarbacki.Travel_app.City;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class City {
    @Id
    @SequenceGenerator(
            name="city_id_sequence",
            sequenceName = "city_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_id_sequence"
    )
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "city", orphanRemoval = true)
    @ToString.Exclude
    private List<TrainStation> trainStations;

    public City(String name) {
        this.name = name;
    }


}
