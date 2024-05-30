package com.kamilgarbacki.Travel_app.City;

import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
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
    @OneToMany(mappedBy = "city", orphanRemoval = true)
    private List<TrainStation> trainStations;

    public City(String name) {
        this.name = name;
    }


}
