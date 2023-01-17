package io.github.tainafernandes.mscards.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tainafernandes.mscards.domain.Card;
import io.github.tainafernandes.mscards.domain.CardIssuanceRequestData;
import io.github.tainafernandes.mscards.domain.ClientCard;
import io.github.tainafernandes.mscards.infra.repository.CardRepository;
import io.github.tainafernandes.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssuanceCardsSubscriber {
    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.issuance-cards}")
    public void receiveIssueRequest(@Payload String payload) {

        var mapper = new ObjectMapper();
        try {
            CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);
            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();

            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(data.getCpf());
            clientCard.setBasicLimit(data.getReleasedLimit());

            clientCardRepository.save(clientCard);

        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Error receiving card issue request: {}", e.getMessage());
        }
    }
}
