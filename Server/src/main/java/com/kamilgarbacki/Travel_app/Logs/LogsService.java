package com.kamilgarbacki.Travel_app.Logs;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LogsService {

    private static final Logger log = LoggerFactory.getLogger(LogsService.class);
    private final LogsRepository logsRepository;

    public void addLog(NewLogRequest request){
        Logs log = new Logs();
        log.setMessage(request.message());
        logsRepository.save(log);
    }

    public List<Logs> getAllLogs(){
        return logsRepository.findAll();
    }

    public void deleteLog(ObjectId id){
        logsRepository.deleteById(id);
    }

    public void deleteAllLogs(){
        logsRepository.deleteAll();
    }
}
