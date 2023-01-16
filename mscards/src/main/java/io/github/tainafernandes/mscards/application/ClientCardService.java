package io.github.tainafernandes.mscards.application;

import io.github.tainafernandes.mscards.domain.ClientCard;
import io.github.tainafernandes.mscards.infra.repository.ClientCardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> listCardByCpf(String cpf){
        return clientCardRepository.findByCpf(cpf);
    }
}
