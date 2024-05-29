package com.kamilgarbacki.Travel_app.TrainStation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class TrainStation {
    @Id
    @SequenceGenerator(
            name="trainStation_id_sequence",
            sequenceName = "trainStation_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trainStation_id_sequence"
    )
    private Long id;
    private String name;
    private String city;

    public TrainStation() {}
    public TrainStation(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public TrainStation(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
