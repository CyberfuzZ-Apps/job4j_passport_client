package ru.job4j.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.job4j.client.model.Passport;
import ru.job4j.client.service.PassportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Класс PassportAPIController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("/passportAPI")
public class PassportAPIController {

    private final PassportService passportService;
    private final ObjectMapper objectMapper;

    public PassportAPIController(PassportService passportService,
                                 ObjectMapper objectMapper) {
        this.passportService = passportService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/find")
    public List<Passport> findAll(
            @RequestParam(required = false) Integer seria) {
        List<Passport> rsl;
        if (seria == null) {
            rsl = passportService.findAll();
        } else {
            rsl = passportService.findBySeria(seria);
        }
        return rsl;
    }

    @GetMapping("/unavailable")
    public List<Passport> unavailable() {
        return passportService.findUnavailable();
    }

    @GetMapping("/replaceable")
    public List<Passport> replaceable() {
        return passportService.findReplaceable();
    }

    @PostMapping("/save")
    public Passport save(@RequestBody Passport passport) {
        return passportService.save(passport);
    }

    @PutMapping("/update")
    public Passport update(@RequestParam Integer id,
                           @RequestBody Passport passport) {
        return passportService.update(id, passport);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        passportService.delete(id);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public void exceptionHandler(Exception e,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", "Паспорта не найдены!");
                put("type", e.getClass());
            }
        }));
    }
}
