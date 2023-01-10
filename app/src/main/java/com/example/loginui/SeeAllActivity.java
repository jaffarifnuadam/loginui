package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.loginui.adapter.GridViewAdapter;
import com.example.loginui.databinding.ActivitySeeAllBinding;
import com.example.loginui.model.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class SeeAllActivity extends AppCompatActivity {

    ActivitySeeAllBinding seeAllBinding;
    List<PlayerModel> playerModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seeAllBinding = DataBindingUtil.setContentView(this,R.layout.activity_see_all);

        playerModels = new ArrayList<>();
        playerModels.add(new PlayerModel("Dhoni", getResources().getString(R.string.dhoni_description)));
        playerModels.add(new PlayerModel("Hardik", getResources().getString(R.string.hardik_description)));
        playerModels.add(new PlayerModel("Rahul", getResources().getString(R.string.rahul_description)));
        playerModels.add(new PlayerModel("Virat", getResources().getString(R.string.virat_description)));
        playerModels.add(new PlayerModel("Yuvraj", getResources().getString(R.string.yuvaraj_description)));

        GridViewAdapter customAdapter = new GridViewAdapter(getApplicationContext(), playerModels);
        seeAllBinding.gridView.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView
        seeAllBinding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                // start Intent
            }
        });

    }
}