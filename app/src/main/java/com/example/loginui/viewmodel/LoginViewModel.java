package com.example.loginui.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginui.BR;
import com.example.loginui.MainActivity;
import com.example.loginui.R;
import com.example.loginui.model.PlayerModel;
import com.example.loginui.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginViewModel extends BaseObservable {
    private String email;
    private Context context;
    private MutableLiveData<User> userMutableLiveData;
    View view;
    MutableLiveData<ArrayList<PlayerModel>> playerLiveData;
    ArrayList<PlayerModel> playerArrayList;

    public LoginViewModel(Context context, View view) {
        this.context = context;
        this.view = view;
        playerLiveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    @Bindable
    @NonNull
    public String getEmail() {
        return this.email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void onLoginClicked() {


        User user = new User(getEmail());

        if (!user.isValidEmail(getEmail())) {
            Toast.makeText(context, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }

        if (!user.isValidPhoneNumber()) {
            Snackbar snackbar = Snackbar
                    .make(view, "Please enter correct number", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            snackbar.show();
        }

        userMutableLiveData.setValue(user);
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
