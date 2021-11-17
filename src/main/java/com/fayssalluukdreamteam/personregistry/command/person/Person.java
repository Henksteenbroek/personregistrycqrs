package com.fayssalluukdreamteam.personregistry.command.person;

import com.fayssalluukdreamteam.personregistry.command.person.commands.CreatePerson;
import com.fayssalluukdreamteam.personregistry.command.person.events.PersonCreated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Person {

    @AggregateIdentifier
    private UUID uuid;

    @CommandHandler
    public Person(CreatePerson createPerson) {
        log.info("Person Handler Constructor called with data {}", createPerson);
        PersonCreated personCreated = new PersonCreated(createPerson.getUuid(), createPerson.getDateOfBirth());
        AggregateLifecycle.apply(personCreated);
    }

    @EventSourcingHandler
    public void handlePersonCreated(PersonCreated personCreated){
        log.info("Person Event Handler called with data {}", personCreated);
        this.uuid = personCreated.getUuid();
    }
}
