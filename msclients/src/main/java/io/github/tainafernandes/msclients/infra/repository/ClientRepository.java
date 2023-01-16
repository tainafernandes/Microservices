package io.github.tainafernandes.msclients.infra.repository;

import io.github.tainafernandes.msclients.domain.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
