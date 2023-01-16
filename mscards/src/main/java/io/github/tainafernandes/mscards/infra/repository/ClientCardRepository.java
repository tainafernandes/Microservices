package io.github.tainafernandes.mscards.infra.repository;

import io.github.tainafernandes.mscards.domain.ClientCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {
    List<ClientCard> findByCpf(String cpf);
}
