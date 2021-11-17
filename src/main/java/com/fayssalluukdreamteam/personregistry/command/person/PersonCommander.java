package com.fayssalluukdreamteam.personregistry.command.person;

import com.fayssalluukdreamteam.personregistry.command.person.commands.CreatePerson;
import com.fayssalluukdreamteam.personregistry.command.person.endpoints.PersonData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

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

       gateway.send(createPerson);
       log.info("Dispatching command");
       return uuid;
    }
}
