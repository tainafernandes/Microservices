package io.github.tainafernandes.mscreditappraiser.application;

import io.github.tainafernandes.mscreditappraiser.application.ex.ClientsDataNotFoundException;
import io.github.tainafernandes.mscreditappraiser.application.ex.ErrorComunicationMicroservicesException;
import io.github.tainafernandes.mscreditappraiser.application.ex.ErrorRequestCardException;
import io.github.tainafernandes.mscreditappraiser.domain.model.CardIssuanceRequestData;
import io.github.tainafernandes.mscreditappraiser.domain.model.CardRequestProtocol;
import io.github.tainafernandes.mscreditappraiser.domain.model.ClientEvaluationReturn;
import io.github.tainafernandes.mscreditappraiser.domain.model.ClientStatus;
import io.github.tainafernandes.mscreditappraiser.domain.model.EvaluationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-assessments")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;
    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "client-status", params = "cpf")
    public ResponseEntity checkCustomerStatus(@RequestParam("cpf") String cpf) {
        try {
            ClientStatus clientStatus = creditAppraiserService.getClientStatus(cpf);
            return ResponseEntity.ok(clientStatus);
        } catch (ClientsDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity doEvaluation(@RequestBody EvaluationData data) {
        try {
            ClientEvaluationReturn clientEvaluationReturn = creditAppraiserService
                    .doEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(clientEvaluationReturn);
        } catch (ClientsDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("issuance-cards")
    public ResponseEntity cardRequest(@RequestBody CardIssuanceRequestData data){
        try{
            CardRequestProtocol cardRequestProtocol = creditAppraiserService
                    .requestCardIssuance(data);
            return ResponseEntity.ok(cardRequestProtocol);
        } catch (ErrorRequestCardException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
