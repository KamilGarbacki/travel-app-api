package com.kamilgarbacki.Travel_app.Passenger;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Passenger() {}
    public Passenger(Long id, String fName, String lName, String email, String phone) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }
    public Passenger(String fName, String lName, String email, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }
}
