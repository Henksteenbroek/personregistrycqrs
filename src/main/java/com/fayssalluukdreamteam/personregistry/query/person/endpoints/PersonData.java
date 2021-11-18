package com.fayssalluukdreamteam.personregistry.query.person.endpoints;

import com.fayssalluukdreamteam.personregistry.query.person.Marriage;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.personId", fetch = EAGER, orphanRemoval=true)
    @MapKey(name = "id.marriageDate")
    Map<LocalDate, Marriage> marriages;

//
//    @Column(nullable = true)
//    LocalDate dateOfMarriage;
//
//    @Column(nullable = true)
//    LocalDate dateOfDivorce;

    public PersonData(UUID uuid, LocalDate dateOfBirth) {
        this.uuid = uuid;
        this.dateOfBirth = dateOfBirth;
    }
}
