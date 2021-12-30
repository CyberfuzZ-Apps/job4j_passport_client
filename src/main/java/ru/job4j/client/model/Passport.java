package ru.job4j.client.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Класс Passport
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Component
public class Passport {

    private int id;
    private int series;
    private long number;
    private String name;
    private String surName;
    private Timestamp birthDate;
    private Timestamp dateOfIssue;
    private Timestamp dateOfReplacement;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
