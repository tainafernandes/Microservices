package io.github.tainafernandes.msclients.application;

import io.github.tainafernandes.msclients.domain.Client;
import io.github.tainafernandes.msclients.infra.repository.ClientRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    @Transactional
    public Client save (Client client){
        return repository.save(client);
    }

    public Optional<Client> getByCPF(String cpf){
        return repository.findByCpf(cpf);
    }
}
