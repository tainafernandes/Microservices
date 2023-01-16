package io.github.tainafernandes.mscards.application;

import io.github.tainafernandes.mscards.domain.Card;
import io.github.tainafernandes.mscards.infra.repository.CardRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;
    @Transactional
    public Card save(Card card){
        return repository.save(card);
    }

    public List<Card> getCardIncomeLessEqual(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
