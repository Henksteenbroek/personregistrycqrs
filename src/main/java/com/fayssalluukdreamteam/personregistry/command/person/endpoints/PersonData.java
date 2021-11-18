package com.fayssalluukdreamteam.personregistry.command.person.endpoints;

import com.fayssalluukdreamteam.personregistry.query.person.Marriage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonData {
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
}

