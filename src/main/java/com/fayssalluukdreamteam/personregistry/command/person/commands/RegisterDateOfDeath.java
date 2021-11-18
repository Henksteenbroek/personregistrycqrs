package com.fayssalluukdreamteam.personregistry.command.person.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Value
public class RegisterDateOfDeath {
    @TargetAggregateIdentifier
    UUID uuid;

    LocalDate dateOfDeath;
}
