package com.kamilgarbacki.Travel_app.Operator;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="api/operator")
public class OperatorController {
    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping
    public List<Operator> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @PostMapping
    public void addOperator(@RequestBody NewOperatorRequest request) {
        operatorService.addOperator(request);
    }

    @DeleteMapping("/{operatorId}")
    public void deleteOperator(@PathVariable("operatorId") Long operatorId){
        operatorService.deleteOperator(operatorId);
    }

    @PatchMapping("/{operatorId}")
    public void updateOperator(@PathVariable("operatorId") Long operatorId,
                               @RequestBody NewOperatorRequest request){
        operatorService.updateOperator(operatorId,request);
    }

    @GetMapping("/id/{operatorId}")
    public Operator getOperatorById(@PathVariable("operatorId") Long operatorId){
        return operatorService.getOperatorById(operatorId);
    }

    @GetMapping("/name/{operatorName}")
    public Operator getOperatorByName(@PathVariable("operatorName") String operatorName){
        return operatorService.getOperatorByName(operatorName);
    }
}
