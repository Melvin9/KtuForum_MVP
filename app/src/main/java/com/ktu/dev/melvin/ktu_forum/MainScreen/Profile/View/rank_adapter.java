package com.ktu.dev.melvin.ktu_forum.MainScreen.Profile.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ktu.dev.melvin.ktu_forum.R;

import java.util.List;

/**
 * Created by Melvin on 12/27/2017.
 */

public class rank_adapter extends RecyclerView.Adapter<rank_adapter.ViewHolder> {

    private Context context;
    private List<items_rank> list;

    public rank_adapter(Context context, List<items_rank> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public rank_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.card_rank,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull rank_adapter.ViewHolder holder, int position) {
        items_rank items_rank=list.get(position);
        holder.rank.setText(items_rank.getRank());
        holder.name.setText(items_rank.getName());
        holder.point.setText(items_rank.getPoint());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,point,rank;
        ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            point=itemView.findViewById(R.id.points);
            rank=itemView.findViewById(R.id.rank);
        }
    }
}
