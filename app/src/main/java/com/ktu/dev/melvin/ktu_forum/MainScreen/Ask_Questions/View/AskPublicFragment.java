package com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.R;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AskPublicFragment extends Fragment {
    ImageButton send;
    EditText question;


    public AskPublicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v=inflater.inflate(R.layout.fragment_ask_public,container,false);
        send= v.findViewById(R.id.send);
        question= v.findViewById(R.id.qn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Toast.makeText(v.getContext(),"Posting",Toast.LENGTH_SHORT).show();
                    RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());
                    StringRequest request=new StringRequest(Request.Method.POST, "http://melvinmathew0102.000webhostapp.com/Student_main.php/", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(v.getContext(),"Successfully Posted",Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(v.getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            HashMap<String,String> hashMap= new HashMap<>();
                            hashMap.put("UserID", ViewAnswerFragment.user_id);
                            hashMap.put("ask_global",question.getText().toString());
                            hashMap.put("ask_specific","None");
                            hashMap.put("spec_id","None");
                            return hashMap;
                        }
                    };
                    requestQueue.add(request);
                }catch (Exception e){
                    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
        return v;
    }
}
