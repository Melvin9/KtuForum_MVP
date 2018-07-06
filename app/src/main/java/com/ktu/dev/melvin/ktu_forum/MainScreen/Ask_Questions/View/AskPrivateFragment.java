package com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View;


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
import com.ktu.dev.melvin.ktu_forum.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AskPrivateFragment extends Fragment {
    private RecyclerView recyclerView;
    private teacher_adapter adapter;
    private List<teacher_data> data_main;
    TextView t;

    public AskPrivateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_ask_private2, container, false);
        recyclerView=view.findViewById(R.id.recycler);
        t= view.findViewById(R.id.loading);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 1);
        data_main=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        setHasOptionsMenu(true);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://melvinmathew0102.000webhostapp.com/Student_ask_specific.php/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    t.setAlpha(0);
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("details");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject o=jsonArray.getJSONObject(i);
                        teacher_data data=new teacher_data(o.getString("T_ID"),o.getString("T_Name"));
                        data_main.add(data);
                        adapter=new teacher_adapter(data_main,view.getContext());
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t.setAlpha(0);
                Toast.makeText(view.getContext(),"Network Error",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
        return view;
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
        }
        return super.onOptionsItemSelected(item);
    }

}
