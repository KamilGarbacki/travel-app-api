package com.kamilgarbacki.Travel_app.Operator;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Operator {
    @Id
    @SequenceGenerator(
            name = "operator_id_sequence",
            sequenceName = "operator_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "operator_id_sequence"
    )
    private Long id;
    private String name;

    public Operator() {}
    public Operator(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Operator(String name) {
        this.name = name;
    }
}
