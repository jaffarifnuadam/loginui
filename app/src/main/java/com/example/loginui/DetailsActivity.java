package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loginui.adapter.DetailsAdapter;
import com.example.loginui.databinding.ActivityDetailsBinding;
import com.example.loginui.model.PlayerModel;
import com.example.loginui.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements LifecycleOwner {

    ActivityDetailsBinding detailsBinding;
    DetailsAdapter detailsAdapter;
    List<PlayerModel> playerModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager_1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager_2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager_3
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager_4
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager_5
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        detailsBinding.recyclerViewDiagnostics.setLayoutManager(layoutManager);
        detailsBinding.recyclerViewMentalHealth.setLayoutManager(layoutManager_1);
        detailsBinding.recyclerViewYogaMeditation.setLayoutManager(layoutManager_2);
        detailsBinding.recyclerViewSportsMedicine.setLayoutManager(layoutManager_3);
        detailsBinding.recyclerViewPodcasts.setLayoutManager(layoutManager_4);
        detailsBinding.recyclerViewDoorHealth.setLayoutManager(layoutManager_5);

        DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new Observer<ArrayList<PlayerModel>>() {
            @Override
            public void onChanged(ArrayList<PlayerModel> playerModels) {
                detailsAdapter = new DetailsAdapter(DetailsActivity.this,playerModels);
                detailsBinding.recyclerViewDiagnostics.setAdapter(detailsAdapter);
                detailsBinding.recyclerViewMentalHealth.setAdapter(detailsAdapter);
                detailsBinding.recyclerViewYogaMeditation.setAdapter(detailsAdapter);
                detailsBinding.recyclerViewSportsMedicine.setAdapter(detailsAdapter);
                detailsBinding.recyclerViewPodcasts.setAdapter(detailsAdapter);
                detailsBinding.recyclerViewDoorHealth.setAdapter(detailsAdapter);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onClick(View view) {
        gotoNextPage("Diagnosis");
    }

    public void onClickMentalHeath(View view) {
        gotoNextPage("Mental Health");
    }

    void gotoNextPage(String type){
        Intent intent = new Intent(this,SeeAllActivity.class);
        intent.putExtra("title","Mental Health");
        startActivity(intent);
    }

    public void onClickYoga(View view) {
        gotoNextPage(getString(R.string.yoga_meditation));
    }

    public void onClickSportsMeditation(View view) {
        gotoNextPage(getString(R.string.sports_medicine));
    }

    public void onClickOutdoorHealth(View view) {
        gotoNextPage(getString(R.string.outdoor_health_tips));
    }
}