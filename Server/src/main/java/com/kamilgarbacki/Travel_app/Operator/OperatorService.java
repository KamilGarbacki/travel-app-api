package com.kamilgarbacki.Travel_app.Operator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {
    private final OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public void addOperator(NewOperatorRequest request) {
        Operator operator = new Operator();
        operator.setName(request.name());
        operator.setLogo(request.logo());
        operatorRepository.save(operator);
    }

    public void deleteOperator(Long operatorId) {
        operatorRepository.deleteById(operatorId);
    }

    public void updateOperator(Long operatorId,NewOperatorRequest request) {
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(()-> new IllegalStateException("Operator with name: " + operatorId + " does not exist"));

        if (request.name() != null && !request.name().isBlank()) {
            operator.setName(request.name());
        }
        if(request.logo() != null && !request.logo().isBlank()) {
            operator.setLogo(request.logo());
        }
        operatorRepository.save(operator);
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
