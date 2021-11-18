package com.fayssalluukdreamteam.personregistry.query.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Marriage {
    @EmbeddedId
    MarriageId id;

    @Column
    LocalDate divorceDate;

    public Marriage(MarriageId id) {
        this.id = id;
    }
}
