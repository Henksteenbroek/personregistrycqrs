package com.fayssalluukdreamteam.personregistry.query.person;

import com.fayssalluukdreamteam.personregistry.command.person.events.*;
import com.fayssalluukdreamteam.personregistry.query.person.endpoints.PersonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class PersonProjector {
    private PersonRegistry personRegistry;

    @EventHandler
    public void handleEventCreated(PersonCreated personCreated) {
        PersonData createdPerson = personRegistry.save(
                new PersonData(personCreated.getUuid(), personCreated.getDateOfBirth())
        );
        log.info("Person with information {} created", createdPerson);
    }

    @EventHandler
    public void handleEventDateOfDeathRegistered(DateOfDeathRegistered dateOfDeathRegistered) {
        PersonData person = findById(dateOfDeathRegistered.getUuid());

        person.setDateOfDeath(dateOfDeathRegistered.getDateOfDeath());

        //TODO: Finish implementing EventHandler
        log.info("Person with information {} date of death registered", dateOfDeathRegistered);
    }

    @EventHandler
    public void handleEventDeletePerson(PersonDeleted personDeleted) {
        personRegistry.deleteById(
                personDeleted.getUuid()
        );
        log.info("Person with information {} deleted", personDeleted);
    }

    @EventHandler
    public void handleEventMarriageRegister(MarriageRegistered marriageRegistered) {
        PersonData person = findById(marriageRegistered.getUuid());

        person.setDateOfMarriage(marriageRegistered.getDateOfMarriage());

        personRegistry.save(person);

        log.info("Person with information {} marriage registered", marriageRegistered);
    }

    @EventHandler
    public void handleEventRegisterDivorce(DivorceRegistered divorceRegistered) {
        PersonData person = findById(divorceRegistered.getUuid());

        person.setDateOfDivorce(divorceRegistered.getDateOfDivorce());

        personRegistry.save(person);

        log.info("Person with information {} divorce registered", divorceRegistered);
    }


    //TODO Add optional checks
    private PersonData findById(UUID uuid){
        return personRegistry.findById(uuid).get();

    }

}