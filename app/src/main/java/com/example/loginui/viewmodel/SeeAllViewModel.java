package com.example.loginui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginui.model.PlayerModel;

import java.util.ArrayList;

public class SeeAllViewModel extends ViewModel {
    MutableLiveData<ArrayList<PlayerModel>> playerLiveData;
    ArrayList<PlayerModel> playerArrayList;

    public SeeAllViewModel(){
        playerLiveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<ArrayList<PlayerModel>> getUserMutableLiveData(){
        return playerLiveData;
    }

    public void init(){
        populateList();
        playerLiveData.setValue(playerArrayList);
    }

    public void populateList(){

        PlayerModel user;
        playerArrayList = new ArrayList<>();
        playerArrayList.add(new PlayerModel("Dhoni", "Mahendra Singh Dhoni is an Indian former international cricketer who was captain of the Indian national cricket team in limited-overs formats from 2007 to 2017 and in Test cricket from 2008 to 2014"));
        playerArrayList.add(new PlayerModel("Hardik", "Hardik Himanshu Pandya is an Indian international cricketer. An All-rounder who bats right-handed and bowls right-arm fast-medium, Pandya has played in all 3 formats for India."));
        playerArrayList.add(new PlayerModel("Rahul", "Kannanur Lokesh Rahul is an Indian international cricketer who currently is the vice-captain of the Indian cricket team in test cricket. He is a Right-handed batter and occasional wicketkeeper."));
        playerArrayList.add(new PlayerModel("Virat", "Virat Kohli is an Indian international cricketer and former captain of the India national team. He is widely regarded as one of the greatest batsmen in modern cricket."));
        playerArrayList.add(new PlayerModel("Yuvraj", "Yuvraj Singh is a former Indian international cricketer who played in all formats of the game. He is an all-rounder who batted left-handed in the middle order and bowled slow left-arm orthodox."));

    }
}
