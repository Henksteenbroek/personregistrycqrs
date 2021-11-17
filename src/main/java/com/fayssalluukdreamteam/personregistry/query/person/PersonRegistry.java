package com.fayssalluukdreamteam.personregistry.query.person;

import com.fayssalluukdreamteam.personregistry.query.person.endpoints.PersonData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRegistry extends CrudRepository<PersonData, UUID> {

}
