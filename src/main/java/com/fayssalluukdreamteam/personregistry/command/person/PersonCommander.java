package com.fayssalluukdreamteam.personregistry.command.person;

import com.fayssalluukdreamteam.personregistry.command.person.commands.*;
import com.fayssalluukdreamteam.personregistry.command.person.endpoints.PersonData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class PersonCommander {
    private CommandGateway gateway;

    public UUID createPerson(PersonData personData) {
       UUID uuid = UUID.randomUUID();
       CreatePerson createPerson = new CreatePerson(uuid, personData.getDateOfBirth());

       gateway.sendAndWait(createPerson);
       log.info("Dispatching command CreatePerson");
       return uuid;
    }

    public void registerDateOfDeath(LocalDate date, UUID uuid) {
        RegisterDateOfDeath registerDateOfDeath = new RegisterDateOfDeath(uuid, date);

        gateway.sendAndWait(registerDateOfDeath);

        log.info("Dispatching command Register Date of Death");
    }

    public void deletePerson(UUID uuid) {
        DeletePerson deletePerson = new DeletePerson(uuid);
        gateway.sendAndWait(deletePerson);
        log.info("Dispatching command Delete Person");
    }

    public void registerDateOfMarriage(UUID uuid, LocalDate dateOfMarriage) {
        RegisterMarriage registerMarriage = new RegisterMarriage(uuid, dateOfMarriage);

        gateway.sendAndWait(registerMarriage);

        log.info("Dispatching command Register Date of Death");
    }

    public void registerDateOfDivorce(UUID uuid, LocalDate dateOfDivorce, LocalDate dateOfMarriage) {
        RegisterDivorce registerDivorce = new RegisterDivorce(uuid, dateOfDivorce, dateOfMarriage);

        gateway.sendAndWait(registerDivorce);

        log.info("Dispatching command Register Date of Divorce");
    }
}
