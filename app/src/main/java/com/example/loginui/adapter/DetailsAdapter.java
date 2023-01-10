package com.example.loginui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginui.R;
import com.example.loginui.model.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.Holder>{

    private Context context;
    private List<PlayerModel> playerModels;

    public DetailsAdapter(Context context, List<PlayerModel> playerModels) {
        this.context = context;
        this.playerModels = playerModels;
    }

    @NonNull
    @Override
    public DetailsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details, parent, false);
        return new DetailsAdapter.Holder(view);
    }

    public void updateUserList(final ArrayList<PlayerModel> userArrayList) {
        this.playerModels.clear();
        this.playerModels = userArrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.Holder holder, int position) {
        holder.txtPlayerName.setText(playerModels.get(position).getName());
        holder.txtDescription.setText(playerModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return playerModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView txtPlayerName;
        TextView txtDescription;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtPlayerName = itemView.findViewById(R.id.player_name);
            txtDescription = itemView.findViewById(R.id.description);
        }
    }
}
