package ru.job4j.client.service;

import ru.job4j.client.model.Passport;

import java.util.List;

/**
 * Класс GlobalService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface GlobalService {

    Passport save(Passport passport);

    Passport update(int id, Passport passport);

    void delete(int id);

    List<Passport> findAll();

    List<Passport> findBySeria(int seria);

    List<Passport> findUnavailable();

    List<Passport> findReplaceable();

}
