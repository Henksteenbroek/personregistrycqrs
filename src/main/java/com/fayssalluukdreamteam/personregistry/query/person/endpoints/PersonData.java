package com.fayssalluukdreamteam.personregistry.query.person.endpoints;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PersonData {
    @Id
    UUID uuid;

    @Column(nullable = false)
    LocalDate dateOfBirth;

    @Column(nullable = true)
    LocalDate dateOfDeath;

    @Column(nullable = true)
    LocalDate dateOfMarriage;

    @Column(nullable = true)
    LocalDate dateOfDivorce;

    public PersonData(UUID uuid, LocalDate dateOfBirth) {
        this.uuid = uuid;
        this.dateOfBirth = dateOfBirth;
    }
}
