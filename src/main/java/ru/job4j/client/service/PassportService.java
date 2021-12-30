package ru.job4j.client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.client.model.Passport;

import java.util.List;

/**
 * Класс PassportService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class PassportService implements GlobalService {

    private static final String URL = "http://localhost:8080/api/passport/";

    private final RestTemplate restTemplate;

    public PassportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Passport save(Passport passport) {
        return restTemplate.postForObject(URL + "save", passport, Passport.class);
    }

    @Override
    public Passport update(int id, Passport passport) {
        return restTemplate.exchange(
                String.format("%s?id=%s", URL + "update", id),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Passport.class
        ).getBody();
    }

    @Override
    public void delete(int id) {
        restTemplate.delete(URL + "delete?id=" + id);
    }

    @Override
    public List<Passport> findAll() {
        return getList(URL + "find");
    }

    @Override
    public List<Passport> findBySeria(int seria) {
        return getList(URL + "find?seria=" + seria);
    }

    @Override
    public List<Passport> findUnavailable() {
        return getList(URL + "unavailable");
    }

    @Override
    public List<Passport> findReplaceable() {
        return getList(URL + "find-replaceable");
    }

    private List<Passport> getList(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
    }
}
