package com.kamilgarbacki.Travel_app.Logs;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/logs")
public class LogsController {
    private final LogsService logsService;
    private final LogsRepository logsRepository;

    @PostMapping
    public void addLog(@RequestBody NewLogRequest request){
        logsService.addLog(request);
    }

    @GetMapping
    public List<Logs> getAllLogs(){
        return logsService.getAllLogs();
    }

    @DeleteMapping
    public void deleteLog(@RequestBody ObjectId id){
        logsService.deleteLog(id);
    }
    @DeleteMapping("/deleteAll")
    public void clearLogs(){
        logsService.deleteAllLogs();
    }
}
