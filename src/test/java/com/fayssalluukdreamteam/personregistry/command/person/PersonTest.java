package com.fayssalluukdreamteam.personregistry.command.person;

import com.fayssalluukdreamteam.personregistry.command.person.commands.CreatePerson;
import com.fayssalluukdreamteam.personregistry.command.person.events.PersonCreated;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private FixtureConfiguration<Person> fixture;

    @BeforeEach
    public void setup() {
        fixture = new AggregateTestFixture<>(Person.class);
    }

    @Test
    public void CreatePersonCommandResultsInPersonCreatedEventTest() {
        //GIVEN
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.of(1762, 1, 8);

        fixture.givenNoPriorActivity()
                .when(new CreatePerson(uuid, date))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PersonCreated(uuid, date));
    }
}