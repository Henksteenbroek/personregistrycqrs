package com.fayssalluukdreamteam.personregistry.command.person.endpoints;

import com.fayssalluukdreamteam.personregistry.command.person.Person;
import com.fayssalluukdreamteam.personregistry.command.person.PersonCommander;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("/{uuid}/date_of_death")
    public void registerDateOfDeath(@RequestBody LocalDate date, @PathVariable UUID uuid){
        commander.registerDateOfDeath(date, uuid);
        log.info("Entered register date of death endpoint: data={}", date);
    }

    @PutMapping("/{uuid}/marriages/{date}")
    public void registerDateOfMarriage(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date, @PathVariable UUID uuid){
        commander.registerDateOfMarriage(uuid, date);
    }

    @PutMapping("/{uuid}/marriages/{dateOfMarriage}/divorce")
    public void registerDateOfDivorce(@RequestBody LocalDate dateOfDivorce, @PathVariable UUID uuid, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate dateOfMarriage){
        commander.registerDateOfDivorce(uuid, dateOfDivorce, dateOfMarriage);
    }


    @DeleteMapping("/{uuid}")
    public void deletePerson(@PathVariable UUID uuid){
        commander.deletePerson(uuid);

        log.info("Entered delete person endpoint: data={}", uuid);
    }


}
