package com.kamilgarbacki.Travel_app.Logs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "logs")
public class Logs {
    @MongoId
    private ObjectId id;
    private LocalDateTime date;
    private String message;

    public Logs() {
        this.date = LocalDateTime.now();
    }
}
