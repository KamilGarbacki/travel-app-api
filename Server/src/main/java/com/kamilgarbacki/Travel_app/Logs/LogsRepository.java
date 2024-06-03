package com.kamilgarbacki.Travel_app.Logs;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogsRepository extends MongoRepository<Logs, ObjectId> {
}
