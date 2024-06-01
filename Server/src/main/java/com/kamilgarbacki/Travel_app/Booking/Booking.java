package com.kamilgarbacki.Travel_app.Booking;

import com.kamilgarbacki.Travel_app.Connection.Connection;
import com.kamilgarbacki.Travel_app.Passenger.Passenger;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Booking {
    @Id
    @SequenceGenerator(
            name="booking_id_sequence",
            sequenceName = "booking_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_id_sequence"
    )
    private Long id;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Connection connection;
    private LocalDate bookingDate;
}
