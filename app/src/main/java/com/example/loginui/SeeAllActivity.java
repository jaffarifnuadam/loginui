package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.loginui.adapter.DetailsAdapter;
import com.example.loginui.adapter.GridViewAdapter;
import com.example.loginui.databinding.ActivitySeeAllBinding;
import com.example.loginui.model.PlayerModel;
import com.example.loginui.viewmodel.DetailViewModel;
import com.example.loginui.viewmodel.SeeAllViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeeAllActivity extends AppCompatActivity implements LifecycleOwner {

    ActivitySeeAllBinding seeAllBinding;
    List<PlayerModel> playerModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        seeAllBinding = DataBindingUtil.setContentView(this,R.layout.activity_see_all);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        seeAllBinding.txtTitle.setText(getIntent().getStringExtra("title"));

        SeeAllViewModel viewModel = ViewModelProviders.of(this).get(SeeAllViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new Observer<ArrayList<PlayerModel>>() {
            @Override
            public void onChanged(ArrayList<PlayerModel> playerModels) {
                GridViewAdapter customAdapter = new GridViewAdapter(getApplicationContext(), playerModels);
                seeAllBinding.gridView.setAdapter(customAdapter);
                seeAllBinding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    }
                });
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}