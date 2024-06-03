package com.kamilgarbacki.Travel_app.Operator;

import com.kamilgarbacki.Travel_app.Logs.LogsController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kamilgarbacki.Travel_app.Logs.NewLogRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {
    private final OperatorRepository operatorRepository;
    private final LogsController logsController;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public void addOperator(NewOperatorRequest request) {
        Operator operator = new Operator();
        operator.setName(request.name());
        operator.setLogo(request.logo());
        operatorRepository.save(operator);

        String message = "Created Operator: " + operator;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public void deleteOperator(Long operatorId) {
        Operator operator = operatorRepository.findById(operatorId).orElse(null);
        if (operator != null) {
            String message = "Created Operator: " + operator;
            NewLogRequest logRequest = new NewLogRequest(message);
            logsController.addLog(logRequest);
        }
        operatorRepository.deleteById(operatorId);
    }

    public void updateOperator(Long operatorId,NewOperatorRequest request) {
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(()-> new IllegalStateException("Operator with name: " + operatorId + " does not exist"));

        String oldOperator = operator.toString();
        if (request.name() != null && !request.name().isBlank()) {
            operator.setName(request.name());
        }
        if(request.logo() != null && !request.logo().isBlank()) {
            operator.setLogo(request.logo());
        }
        operatorRepository.save(operator);
        String message = "Updated Operator: " + oldOperator + "to " + operator;
        NewLogRequest logRequest = new NewLogRequest(message);
        logsController.addLog(logRequest);
    }

    public Operator getOperatorById(Long operatorId) {
        return operatorRepository.findById(operatorId)
                .orElseThrow(()-> new IllegalStateException("Operator with name: " + operatorId + " does not exist"));
    }

    public Operator getOperatorByName(String operatorName) {
        return operatorRepository.findByName(operatorName)
                .orElseThrow(()-> new IllegalStateException("Operator with name: " + operatorName + " does not exist"));
    }
}
