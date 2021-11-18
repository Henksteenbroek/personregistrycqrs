package com.fayssalluukdreamteam.personregistry.query.person;

import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
public class MarriageId implements Serializable {

    long serialVersionUID;

    UUID personId;

    @Basic
    LocalDate marriageDate;

    public MarriageId(UUID personId, LocalDate marriageDate) {
        this.personId = personId;
        this.marriageDate = marriageDate;
    }
}
