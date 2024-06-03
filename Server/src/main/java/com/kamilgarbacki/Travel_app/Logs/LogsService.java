package com.kamilgarbacki.Travel_app.Logs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LogsService {

    private final LogsRepository logsRepository;

    public void addLog(NewLogRequest request){
        Logs log = new Logs();
        log.setMessage(request.message());
        logsRepository.save(log);
    }

    public List<Logs> getAllLogs(){
        return logsRepository.findAll();
    }
}
