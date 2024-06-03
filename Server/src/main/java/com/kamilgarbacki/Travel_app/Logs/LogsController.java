package com.kamilgarbacki.Travel_app.Logs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/logs")
public class LogsController {
    private final LogsService logsService;

    @PostMapping
    public void addLog(@RequestBody NewLogRequest request){
        logsService.addLog(request);
    }

    @GetMapping
    public List<Logs> getAllLogs(){
        return logsService.getAllLogs();
    }
}
