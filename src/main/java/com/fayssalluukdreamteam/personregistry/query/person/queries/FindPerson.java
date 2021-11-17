package com.fayssalluukdreamteam.personregistry.query.person.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class FindPerson {
    private UUID uuid;
}
