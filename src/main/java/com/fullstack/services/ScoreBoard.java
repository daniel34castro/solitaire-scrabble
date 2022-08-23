package com.fullstack.services;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private  List<String> scoreList = new ArrayList<>() ;

    public  List<String> getUsers () {
        return scoreList;
    }
    public void addUser(String user) {
        this.scoreList.add(user);
    }


}
