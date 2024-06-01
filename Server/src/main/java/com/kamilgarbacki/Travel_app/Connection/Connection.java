package com.kamilgarbacki.Travel_app.Connection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kamilgarbacki.Travel_app.Booking.Booking;
import com.kamilgarbacki.Travel_app.Operator.Operator;
import com.kamilgarbacki.Travel_app.TrainStation.TrainStation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "connection", orphanRemoval = true)
    private List<Booking> bookings;

    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double price;
}
