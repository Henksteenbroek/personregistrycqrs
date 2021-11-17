package com.fayssalluukdreamteam.personregistry.query.person.endpoints;

import com.fayssalluukdreamteam.personregistry.query.person.PersonRegistry;
import com.fayssalluukdreamteam.personregistry.query.person.queries.FindPerson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController("queryPersonsEndpoint")
@RequestMapping(path = "persons", method = RequestMethod.GET)
@Slf4j
public class Persons {
    private QueryGateway gateway;

    @GetMapping("/{uuid}")
    public CompletableFuture<PersonData> findPerson(@PathVariable UUID uuid){
        log.info("Find Person called with data {}", uuid);
        return gateway.query(new FindPerson(uuid), PersonData.class);
    }
}
