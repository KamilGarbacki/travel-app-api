package com.kamilgarbacki.Travel_app.Connection;

import com.kamilgarbacki.Travel_app.Operator.Operator;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Connection {
    @Id
    @SequenceGenerator(
            name="connection_id_sequence",
            sequenceName = "connection_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_id_sequence"
    )
    private Long id;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private TrainStation departureStation;

    @ManyToOne
    private TrainStation destinationStation;

    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double price;
}
