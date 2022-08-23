package com.fullstack.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ScrabbleServices {

    public static List<Character> generateLetters(int numberOfLetters) {
        List<Character> letArr = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < numberOfLetters; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            letArr.add(c);
        }
        return letArr;
    }
    public static int calculateScoreSingleWord (String word){
        // Create HashMap to access score of each letter
        String[] letterGroups = {"AEIOULNRST", "DG", "BCMP", "FHVWY", "K", "JX", "QZ"};
        Integer[] scores = {1, 2, 3, 4, 5, 8, 10};
        HashMap<String,Integer> mapLettersScore = new HashMap<>();
        for (int i = 0; i < letterGroups.length; i++) {
            String[] letterInGroup = letterGroups[i].split("");
            for (String letter: letterInGroup) {
                mapLettersScore.put(letter, scores[i]);
            }
        }

        // sum score of each letters
        int score = 0;
        String[] lettersInWord= word.split("");
        for (String letter: lettersInWord) {
            letter = letter.toUpperCase();
            score += mapLettersScore.get(letter);
        }

        return score;
    }
    public static String calculateScore (String wordList){
        String[] wordListParsed = wordList.split(",");
        int totalScore = 0;
        for (String word: wordListParsed) {
            totalScore += calculateScoreSingleWord(word);
        }

        return "" + totalScore;
    }



}
