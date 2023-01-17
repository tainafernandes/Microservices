package io.github.tainafernandes.mscreditappraiser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@Configuration
public class MQconfig {
    @Value("${mq.queues.issuance-cards}")
    private String issuanceQueueCards;

    @Bean
    public Queue queueIssueCard(){
        return new Queue(issuanceQueueCards, true);
    }
}
