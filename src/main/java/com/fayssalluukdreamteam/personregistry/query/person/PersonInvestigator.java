package com.fayssalluukdreamteam.personregistry.query.person;

import com.fayssalluukdreamteam.personregistry.query.person.endpoints.PersonData;
import com.fayssalluukdreamteam.personregistry.query.person.queries.FindPerson;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonInvestigator {

    PersonRegistry personRegistry;

    @QueryHandler
    public PersonData findPerson(FindPerson query) {
        return personRegistry.findById(query.getUuid()).get();
    }
}
