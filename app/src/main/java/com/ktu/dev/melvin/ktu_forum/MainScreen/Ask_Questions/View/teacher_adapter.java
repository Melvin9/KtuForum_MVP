package com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View;

import android.annotation.SuppressLint;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Melvin on 12/22/2017.
 */

public class teacher_adapter extends RecyclerView.Adapter<teacher_adapter.ViewHolder> {
    private List<teacher_data> listitems;
    private List<teacher_data> listitemsfiltered;

    private Context context;

    teacher_adapter(List<teacher_data> listitems, Context context) {
        this.listitems = new ArrayList<teacher_data>();
        this.listitemsfiltered=listitems;
        this.listitems.addAll(listitemsfiltered);
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.available_teacher_card,parent,false);
                return new ViewHolder(v1);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        teacher_data list=listitemsfiltered.get(position);
        holder.tid.setText(list.getTid());
        holder.tname.setText(list.getTname());
        holder.feed=list;
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
            for (teacher_data wp : listitems) {
                if (wp.getTid().toLowerCase(Locale.getDefault()).contains(charText)||wp.getTname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listitemsfiltered.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tid,tname;RelativeLayout relativeLayout;
        teacher_data feed;
        ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout= itemView.findViewById(R.id.layout_rv);
            tid= itemView.findViewById(R.id.t_id);
            tname= itemView.findViewById(R.id.t_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint({"ResourceAsColor", "RtlHardcoded"})
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("Question Box- Ask "+feed.getTname());
                    final EditText input = new EditText(itemView.getContext());
                    input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                    input.setSingleLine(false);
                    input.setLines(5);
                    input.setMaxLines(5);
                    input.setGravity(Gravity.LEFT | Gravity.TOP);
                    input.setVerticalScrollBarEnabled(true);
                    builder.setView(input);
                    builder.setPositiveButton("Ask", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            final ProgressDialog progressDialog=new ProgressDialog(itemView.getContext());
                            progressDialog.setMessage("Posting");
                            progressDialog.show();
                            final String ask=input.getText().toString();
                            RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
                            StringRequest request=new StringRequest(Request.Method.POST, "https://melvinmathew0102.000webhostapp.com/Student_ask_specific.php", new Response.Listener<String>() {
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
                                    hashMap.put("UserID", ViewAnswerFragment.user_id);
                                    hashMap.put("ask",ask);
                                    hashMap.put("T_id",feed.getTid());
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
            });
        }
    }
}
