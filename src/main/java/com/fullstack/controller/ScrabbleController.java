package com.fullstack.controller;

import com.fullstack.services.ScrabbleServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api/scrabbleController")
public class ScrabbleController {

    @GetMapping("/getLetters")
    public List<Character> getLetters() {
        return ScrabbleServices.generateLetters(10);
    }

    @PostMapping("/postScore")
    public String postScore (@RequestBody String wordList) {
        var score = ScrabbleServices.calculateScore(wordList);
        return score;
    }


    @GetMapping("/validateWord")
    public String validateWord() {

        final String uri = "https://api.dictionaryapi.dev/api/v2/entries/en/bird" ;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;

    }

}
