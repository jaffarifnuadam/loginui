package com.example.loginui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.telecom.Call;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginui.adapter.DetailsAdapter;
import com.example.loginui.adapter.PlayerAdapter;
import com.example.loginui.adapter.SliderAdapter;
import com.example.loginui.databinding.ActivityMainBinding;
import com.example.loginui.model.PlayerModel;
import com.example.loginui.model.User;
import com.example.loginui.viewmodel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements LifecycleOwner{

    private SliderAdapter mAdapter;
    private PlayerAdapter playerAdapter;
    List<Integer> images;
    List<PlayerModel> playerModelsList;
    ActivityMainBinding binding;
    final int duration = 10;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    final int time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        images = new ArrayList<>();
        images.add(R.drawable.dhoni);
        images.add(R.drawable.hardik);
        images.add(R.drawable.rahul);
        images.add(R.drawable.virat);
        images.add(R.drawable.yuvaraj);

        playerModelsList = new ArrayList<>();

        //binding.viewPager.setPageTransformer(true, new HingeAnimation());

        mAdapter = new SliderAdapter(this, images, playerModelsList);

        LoginViewModel loginViewModel = new LoginViewModel(MainActivity.this,binding.mainLayout);
        binding.setViewModel(loginViewModel);

        loginViewModel.getUser().observe(MainActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(!user.getEmail().equals("Please enter correct number") || !user.getEmail().equals("Please enter valid email")){
                    Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                    startActivity(intent);
                }
            }
        });

        loginViewModel.getUserMutableLiveData().observe(this, new Observer<ArrayList<PlayerModel>>() {
            @Override
            public void onChanged(ArrayList<PlayerModel> playerModels) {
                playerModelsList = playerModels;
                playerAdapter = new PlayerAdapter(MainActivity.this, playerModels);
                binding.recyclerView.setAdapter(playerAdapter);
                playerAdapter.notifyDataSetChanged();
            }
        });
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.viewPager.setAdapter(mAdapter);

        final LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(binding.recyclerView);

        final Timer timer_1 = new Timer();
        timer_1.schedule(new TimerTask() {

            @Override
            public void run() {
                if(playerModelsList.size() > 0){
                    if (layoutManager.findLastCompletelyVisibleItemPosition() < (playerAdapter.getItemCount() - 1)) {
                        layoutManager.smoothScrollToPosition(binding.recyclerView, new RecyclerView.State(), layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                    } else if (layoutManager.findLastCompletelyVisibleItemPosition() == (playerAdapter.getItemCount() - 1)) {
                        layoutManager.smoothScrollToPosition(binding.recyclerView, new RecyclerView.State(), 0);
                    }
                }
            }
        }, 1000, time);

        /*new TabLayoutMediator(binding.indicator, binding.viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    }
                }).attach();*/

        binding.indicator.setupWithViewPager(binding.viewPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 1000, 3000);



        binding.btnContinueEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button_text", binding.btnContinueEmail.getText().toString());
                if (binding.btnContinueEmail.getText().toString().equalsIgnoreCase(getResources().getString(R.string.continue_google))) {
                    binding.spinner.setVisibility(View.GONE);
                    binding.view.setVisibility(View.GONE);
                    binding.edtMobileNo.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    binding.btnContinueEmail.setText(getResources().getString(R.string.continue_mobile));
                    binding.edtMobileNo.setHint(getResources().getString(R.string.enter_email));
                } else if (binding.btnContinueEmail.getText().toString().equalsIgnoreCase(getResources().getString(R.string.continue_mobile))) {
                    binding.spinner.setVisibility(View.VISIBLE);
                    binding.view.setVisibility(View.VISIBLE);
                    binding.edtMobileNo.setInputType(InputType.TYPE_CLASS_NUMBER);
                    binding.btnContinueEmail.setText(getResources().getString(R.string.continue_google));
                    binding.edtMobileNo.setHint(getResources().getString(R.string.enter_your_mobile_number));
                }
            }
        });


        binding.btnSignIn.setOnClickListener(view -> {
            if (binding.btnContinueEmail.getText().toString().equalsIgnoreCase(getResources().getString(R.string.continue_google))) {
                if (binding.edtMobileNo.getText().toString().length() < 10) {
                    Snackbar snackbar = Snackbar
                            .make(binding.mainLayout, "Please enter correct number", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    sbView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                    snackbar.show();

                }else{
                    Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                    startActivity(intent);
                }
            } else {
                if (!isValidEmail(binding.edtMobileNo.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (binding.viewPager.getCurrentItem() < images.size() - 1) {
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
                    } else {
                        binding.viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}