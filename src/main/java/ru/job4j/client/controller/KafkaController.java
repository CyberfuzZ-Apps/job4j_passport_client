package ru.job4j.client.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

/**
 * Класс KafkaController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
@EnableKafka
public class KafkaController {

    @KafkaListener(topics = "unavailable")
    public void listenKafka(ConsumerRecord<Integer, String> input) {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(input + " " + currentTimeMillis);
    }
}
