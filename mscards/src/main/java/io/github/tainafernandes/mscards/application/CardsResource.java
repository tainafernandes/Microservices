package io.github.tainafernandes.mscards.application;

import io.github.tainafernandes.mscards.application.representation.CardSaveRequest;
import io.github.tainafernandes.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {
    private final CardService cardService;

    @GetMapping
    public String status(){
        return "ok";
    }
    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
