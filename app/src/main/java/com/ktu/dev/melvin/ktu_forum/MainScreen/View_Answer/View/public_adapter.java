package com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ktu.dev.melvin.ktu_forum.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class public_adapter extends RecyclerView.Adapter<public_adapter.ViewHolder>{
    private List<public_data> listitems;
    private List<public_data> listitemsfiltered;
    private Context context;

    public_adapter(List<public_data> listitems, Context context) {
        this.context = context;
        this.listitemsfiltered = listitems;
        this.listitems = new ArrayList<public_data>();
        this.listitems.addAll(listitemsfiltered);
    }


    @NonNull
    @Override
    public public_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_public,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull public_adapter.ViewHolder holder, int position) {
        public_data list=listitemsfiltered.get(position);
        holder.qn_user.setText(String.format("%s Asked a Question:", list.getQn_id()));
        holder.qn.setText(list.getQuestion());
        holder.ans.setText(list.getAnswer());
        holder.answer_user.setText(String.format("%s Answered:", list.getAns_id()));
        if(list.getVerified().equals("yes")){
            holder.ans.setCompoundDrawablesWithIntrinsicBounds(0,0, R.mipmap.ic_share_black_24dp,0);
        }
        holder.feed=list;

    }

    @Override
    public int getItemCount() {
        return listitemsfiltered.size();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listitemsfiltered.clear();
        if (charText.length() == 0) {
            listitemsfiltered.addAll(listitems);
        } else {
            for (public_data wp : listitems) {
                if (wp.getAnswer().toLowerCase(Locale.getDefault()).contains(charText)||wp.getQuestion().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listitemsfiltered.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView qn_user, qn, answer_user,ans;
        public_data feed;
        ViewHolder(final View itemView){
            super(itemView);

            qn_user= itemView.findViewById(R.id.question_user);
            qn = itemView.findViewById(R.id.question);
            answer_user = itemView.findViewById(R.id.answer_user);
            ans = itemView.findViewById(R.id.answer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(feed.getQn_id().equals(ViewAnswerFragment.user_id)){
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(itemView.getContext());
                        alertDialogBuilder.setTitle("Answer BOX").setMessage("You can not answer this Question \nDont try to earn reward by answering your Question").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                    }
                    else if (!feed.getAns_id().toLowerCase().equals("none")) {
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(itemView.getContext());
                        alertDialogBuilder.setTitle("Answer BOX").setMessage("You can not answer this Question \nAnswer before others Do").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                    }
                    else if (feed.getAns_id().toLowerCase().equals("none")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setTitle("Answer Box");
                        final EditText input = new EditText(itemView.getContext());
                        input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                        input.setSingleLine(false);
                        input.setLines(5);
                        input.setMaxLines(5);
                        input.setGravity(Gravity.START | Gravity.TOP);
                        input.setVerticalScrollBarEnabled(true);
                        builder.setView(input);
                        builder.setPositiveButton("Post", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                final ProgressDialog progressDialog=new ProgressDialog(itemView.getContext());
                                progressDialog.setMessage("Posting");
                                progressDialog.show();
                                final String answer=input.getText().toString();
                                RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
                                StringRequest request=new StringRequest(Request.Method.POST, "http://melvinmathew0102.000webhostapp.com/ans_group.php/", new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        Toast.makeText(itemView.getContext(),"Successfully Posted",Toast.LENGTH_SHORT).show();

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(itemView.getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() {
                                        HashMap<String,String> hashMap= new HashMap<>();
                                        hashMap.put("UserID",feed.getQn_id());
                                        hashMap.put("ans_by",ViewAnswerFragment.user_id);
                                        hashMap.put("ans",answer);
                                        hashMap.put("verified","No");
                                        hashMap.put("question",feed.getQuestion());
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(request);

                            }
                        }); builder.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                }
            });
        }
    }
}
