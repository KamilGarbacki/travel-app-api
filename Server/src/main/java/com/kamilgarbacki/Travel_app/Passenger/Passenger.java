package com.kamilgarbacki.Travel_app.Passenger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kamilgarbacki.Travel_app.Booking.Booking;
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
public class Passenger {
    @Id
    @SequenceGenerator(
            name = "passenger_id_sequence",
            sequenceName = "passenger_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passenger_id_sequence"
    )
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "passenger", orphanRemoval = true)
    private List<Booking> bookings;
}
