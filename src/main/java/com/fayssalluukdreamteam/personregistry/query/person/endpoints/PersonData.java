package com.fayssalluukdreamteam.personregistry.query.person.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class PersonData {
    @Id
    UUID uuid;

    @Column(nullable = false)
    LocalDate dateOfBirth;
}
