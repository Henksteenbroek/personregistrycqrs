package com.fayssalluukdreamteam.personregistry.query.person;

import com.fayssalluukdreamteam.personregistry.command.person.events.PersonCreated;
import com.fayssalluukdreamteam.personregistry.query.person.endpoints.PersonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

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
}
