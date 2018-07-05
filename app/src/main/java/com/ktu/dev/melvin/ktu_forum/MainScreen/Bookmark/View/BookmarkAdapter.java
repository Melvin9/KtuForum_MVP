package com.ktu.dev.melvin.ktu_forum.MainScreen.Bookmark.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.public_data;
import com.ktu.dev.melvin.ktu_forum.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Melvin on 12/25/2017.
 */

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private List<BookmarkData> listitems;
    private Context context;
    private List<BookmarkData> listitemsfiltered;

    BookmarkAdapter(List<BookmarkData> listitems, Context context) {
        this.listitems = new ArrayList<BookmarkData>();
        this.listitemsfiltered=listitems;
        this.listitems.addAll(listitemsfiltered);
        this.context = context;
    }

    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_public,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder holder, int position) {
        BookmarkData list=listitemsfiltered.get(position);
        if(list.getQn_id().equals(ViewAnswerFragment.user_id)){
            holder.qn_user.setText("You asked: ");
            holder.qn.setText(list.getQuestion());
            holder.ans.setText(list.getAnswer());
            if(list.getAnswer().equals("")){
                holder.answer_user.setText(String.format("%s did not answer.", list.getAns_id()));
            }
            else{
                holder.answer_user.setText(String.format("%s Answered:", list.getAns_id()));
            }
            holder.feed=list;
        }
    }

    @Override
    public int getItemCount() {
        return listitemsfiltered.size();
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listitemsfiltered.clear();
        if (charText.length() == 0) {
            listitemsfiltered.addAll(listitems);
        } else {
            for (BookmarkData wp : listitems) {
                if (wp.getAnswer().toLowerCase(Locale.getDefault()).contains(charText)||wp.getQuestion().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listitemsfiltered.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView qn_user, qn, answer_user,ans;
        BookmarkData feed;
        ViewHolder(final View itemView){
            super(itemView);
            qn_user=itemView.findViewById(R.id.question_user);
            qn = itemView.findViewById(R.id.question);
            answer_user = itemView.findViewById(R.id.answer_user);
            ans = itemView.findViewById(R.id.answer);
        }
    }
}
