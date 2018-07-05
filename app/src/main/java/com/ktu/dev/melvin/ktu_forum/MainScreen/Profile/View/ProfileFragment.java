package com.ktu.dev.melvin.ktu_forum.MainScreen.Profile.View;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View.AskPrivateFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View.AskPublicFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<items_rank> data_main;
    TextView t1,t2,t3;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView=view.findViewById(R.id.list_item);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 1);
        data_main=new ArrayList<>();
        loadFromServer(view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        t1=view.findViewById(R.id.point_display);
        t2=view.findViewById(R.id.rank_display);
        t3=view.findViewById(R.id.username);
        setHasOptionsMenu(true);
        return view;

    }
    private void loadFromServer(final Context context) {
        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Fetching Details!!!");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://melvinmathew0102.000webhostapp.com/reward_display.php/", new Response.Listener<String>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("s1s2");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject o=jsonArray.getJSONObject(i);
                        if(i<=9){
                            items_rank data=new items_rank(o.getString("UserName"),i+1+"",o.getString("Rewards"));
                            data_main.add(data);
                            adapter=new rank_adapter(context,data_main);
                            recyclerView.setAdapter(adapter);
                        }
                        if(o.getString("UserID").equals(ViewAnswerFragment.user_id)){
                            t1.setText(o.getString("Rewards"));
                            t2.setText(String.format("%d", i + 1));
                            t3.setText(o.getString("UserName"));
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Network Error",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.nav_profile_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_ask_private:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayout,new AskPrivateFragment()).commit();
                break;
            case R.id.action_ask_public:
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.frameLayout,new AskPublicFragment()).commit();
                break;
            case R.id.action_help:
                //help
                break;
            case R.id.action_refresh:
                //refresh
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
