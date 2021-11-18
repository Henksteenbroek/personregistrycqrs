package com.fayssalluukdreamteam.personregistry.command.person;

import com.fayssalluukdreamteam.personregistry.command.person.commands.*;
import com.fayssalluukdreamteam.personregistry.command.person.events.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Person {

    @AggregateIdentifier
    private UUID uuid;

    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private LocalDate dateOfMarriage;


    @CommandHandler
    public Person(CreatePerson createPerson) {
        log.info("Person Handler Constructor called with data {}", createPerson);

        PersonCreated personCreated = new PersonCreated(createPerson.getUuid(), createPerson.getDateOfBirth());
        AggregateLifecycle.apply(personCreated);
    }


    @CommandHandler
    public void RegisterDateOfDeath(RegisterDateOfDeath registerDateOfDeath) {
        log.info("Person Handler Register Date of Death called with data {}", registerDateOfDeath);

        if(registerDateOfDeath.getDateOfDeath().isAfter(this.dateOfBirth)){
            DateOfDeathRegistered dateOfDeathRegistered = new DateOfDeathRegistered(registerDateOfDeath.getUuid(), registerDateOfDeath.getDateOfDeath());
            AggregateLifecycle.apply(dateOfDeathRegistered);
        } else {
            throw new IllegalStateException();
        }
    }

    @CommandHandler
    public void DeletePerson(DeletePerson deletePerson) {
        log.info("Person Handler Delete Person called with data {}", deletePerson);
        PersonDeleted personDeleted = new PersonDeleted(deletePerson.getUuid());
        AggregateLifecycle.apply(personDeleted);
    }

    @CommandHandler
    public void RegisterMarriage(RegisterMarriage registerMarriage) {
        log.info("Person Handler Register Marriage called with data {}", registerMarriage);
        MarriageRegistered marriageRegistered = new MarriageRegistered(registerMarriage.getUuid(), registerMarriage.getDateOfMarriage());

        Period intervalPeriod = Period.between(dateOfBirth, marriageRegistered.getDateOfMarriage());

        if(intervalPeriod.getYears() >= 18 && dateOfDeath == null){
            AggregateLifecycle.apply(marriageRegistered);
        } else {
            throw new IllegalStateException();
        }
    }

    @CommandHandler
    public void RegisterDivorce(RegisterDivorce registerDivorce) {
        log.info("Person Handler Register Divorce called with data {}", registerDivorce);
        DivorceRegistered divorceRegistered = new DivorceRegistered(registerDivorce.getUuid(), registerDivorce.getDateOfDivorce());

        if(dateOfMarriage != null){
            if (registerDivorce.getDateOfDivorce().isAfter(dateOfMarriage)){
                AggregateLifecycle.apply(registerDivorce);
            } else{
                throw new IllegalStateException();
            }
        } else{
            throw new IllegalStateException();
        }

    }

    @EventSourcingHandler
    public void handlePersonCreated(PersonCreated personCreated){
        log.info("Person Event Handler called with data {}", personCreated);
        this.uuid = personCreated.getUuid();
        this.dateOfBirth = personCreated.getDateOfBirth();
    }

    @EventSourcingHandler
    public void handlePersonDeleted(PersonDeleted personDeleted){
        //todo logging
        //TODO Make sure all current and future commandhandlers fail validation if the person is marked as deleted.
        AggregateLifecycle.markDeleted();
    }

    @EventSourcingHandler
    public void handleRegisterDateOfDeath(DateOfDeathRegistered dateOfDeathRegistered){
        //todo logging
        this.dateOfDeath = dateOfDeathRegistered.getDateOfDeath();
    }

    @EventSourcingHandler
    public void handleRegisterDateOfMarriage(MarriageRegistered marriageRegistered){
        //todo logging
        this.dateOfMarriage = marriageRegistered.getDateOfMarriage();
    }
}
