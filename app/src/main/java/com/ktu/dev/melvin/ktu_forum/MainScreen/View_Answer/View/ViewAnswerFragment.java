package com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ktu.dev.melvin.ktu_forum.Login.View.LoginActivity;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View.AskPrivateFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View.AskPublicFragment;
import com.ktu.dev.melvin.ktu_forum.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewAnswerFragment extends Fragment{
    public static String user_id;
    private RecyclerView recyclerView;
    private List<public_data> data_main;
    private public_adapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    public ViewAnswerFragment() {
        // Required empty public constructor
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_view_answer, container, false);
        recyclerView=view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 1);
        data_main=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle = (Objects.requireNonNull(getActivity())).getIntent().getExtras();
        assert bundle != null;
        user_id=bundle.getString("us");
        loadFromServer(view.getContext());
        return view;
    }
    private void loadFromServer(final Context context) {
        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Fetching Details!!!");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://melvinmathew0102.000webhostapp.com/Teacher_ans.php/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("Questions");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject o=jsonArray.getJSONObject(i);
                        public_data data=new public_data(o.getString("Ask_global"),o.getString("ans"),o.getString("UserID"), o.getString("ans_by"),o.getString("verified").toLowerCase());
                        data_main.add(data);
                        mAdapter=new public_adapter(data_main,context);
                        recyclerView.setAdapter(mAdapter);
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
                Intent i=new Intent(context,LoginActivity.class);
                startActivity(i);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.nav_main_items, menu);
        MenuItem item = menu.findItem(R.id.search_questions);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.filter(newText);
                return false;
            }
        });
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

