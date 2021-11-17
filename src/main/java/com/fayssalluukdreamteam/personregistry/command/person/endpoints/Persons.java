package com.fayssalluukdreamteam.personregistry.command.person.endpoints;

import com.fayssalluukdreamteam.personregistry.command.person.Person;
import com.fayssalluukdreamteam.personregistry.command.person.PersonCommander;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("commandPersonsEndpoint")
@RequestMapping(path = "persons")
@Slf4j
@AllArgsConstructor
public class Persons {
    private PersonCommander commander;

    @PostMapping
    public UUID createPerson(@RequestBody PersonData data) {
        UUID personUUID = commander.createPerson(data);
        log.info("Entered createPerson endpoint: data={}",data);
        return personUUID;
    }
}
