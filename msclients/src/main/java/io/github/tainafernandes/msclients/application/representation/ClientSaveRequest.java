package io.github.tainafernandes.msclients.application.representation;

import io.github.tainafernandes.msclients.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {
    private String cpf;
    private String name;
    private Integer age;
    public Client toModel(){
        return new Client(cpf, name, age);
    }
}
