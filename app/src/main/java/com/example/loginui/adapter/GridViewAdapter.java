package com.example.loginui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.loginui.R;
import com.example.loginui.model.PlayerModel;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<PlayerModel> playerModelList;

    public GridViewAdapter(Context context,List<PlayerModel> playerModels){
        this.context = context;
        this.playerModelList = playerModels;
    }

    @Override
    public int getCount() {
        return playerModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflter = (LayoutInflater.from(context));;
        view = inflter.inflate(R.layout.item_details, null); // inflate the layout
        TextView textView =  view.findViewById(R.id.player_name); // get the reference of ImageView
        TextView description =  view.findViewById(R.id.description); // get the reference of ImageView
        textView.setText(playerModelList.get(i).getName());
        description.setText(playerModelList.get(i).getDescription());
        return view;
    }
}
