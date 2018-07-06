package com.ktu.dev.melvin.ktu_forum.MainScreen.Bookmark.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class BookmarkFragment extends Fragment {
    String a;
    private RecyclerView recyclerView;
    private BookmarkAdapter adapter;
    private List<BookmarkData> bookmarkData;
    public static TextView textView;
    public BookmarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bookmark, container, false);
        a="false";
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 1);
        bookmarkData=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recycler1);
        textView=view.findViewById(R.id.teacher_hide);
        recyclerView.setHasFixedSize(true);
        loadFromServer(view.getContext());
        setHasOptionsMenu(true);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
    private void loadFromServer(final Context context) {
        final ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Fetching Details!!!");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://melvinmathew0102.000webhostapp.com/ans_specific_view.php/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("ans");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject o=jsonArray.getJSONObject(i);
                        if(o.getString("UserID").equals(ViewAnswerFragment.user_id)){
                            BookmarkData data=new BookmarkData(o.getString("Ask_specific"),o.getString("ans"),o.getString("UserID"), o.getString("spec_id"));
                            bookmarkData.add(data);
                            adapter=new BookmarkAdapter(bookmarkData,context);
                            recyclerView.setAdapter(adapter);
                            a ="true";
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
        }
        return super.onOptionsItemSelected(item);
    }
}
