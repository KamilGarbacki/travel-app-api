package com.kamilgarbacki.Travel_app.Operator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kamilgarbacki.Travel_app.Connection.Connection;
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
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "operator", orphanRemoval = true)
    private List<Connection> connections;

    public Operator(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
}
