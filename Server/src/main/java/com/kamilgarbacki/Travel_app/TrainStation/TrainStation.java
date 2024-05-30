package com.kamilgarbacki.Travel_app.TrainStation;

import com.kamilgarbacki.Travel_app.City.City;
import com.kamilgarbacki.Travel_app.Connection.Connection;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "departureStation")
    private List<Connection> Departures;

    @OneToMany(mappedBy = "destinationStation")
    private List<Connection> Destinations;


}
