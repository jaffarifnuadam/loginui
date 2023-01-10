package com.example.loginui.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginui.R;
import com.example.loginui.model.PlayerModel;

import java.util.ArrayList;

public class DetailViewModel extends ViewModel {

    MutableLiveData<ArrayList<PlayerModel>> playerLiveData;
    ArrayList<PlayerModel> playerArrayList;
    private Context context;

    public DetailViewModel(){
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
        playerArrayList.add(new PlayerModel("Dhoni", context.getResources().getString(R.string.dhoni_description)));
        playerArrayList.add(new PlayerModel("Hardik", context.getResources().getString(R.string.hardik_description)));
        playerArrayList.add(new PlayerModel("Rahul", context.getResources().getString(R.string.rahul_description)));
        playerArrayList.add(new PlayerModel("Virat", context.getResources().getString(R.string.virat_description)));
        playerArrayList.add(new PlayerModel("Yuvraj", context.getResources().getString(R.string.yuvaraj_description)));

    }
}
