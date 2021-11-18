package com.fayssalluukdreamteam.personregistry.command.person.events;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@AllArgsConstructor
public class DateOfDeathRegistered {
    UUID uuid;
    LocalDate dateOfDeath;
}
