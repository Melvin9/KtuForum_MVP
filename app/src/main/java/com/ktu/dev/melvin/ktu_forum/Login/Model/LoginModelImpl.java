package com.ktu.dev.melvin.ktu_forum.Login.Model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginModelImpl implements LoginModel {
private boolean u=true,pa=true,p=true;

    @Override
    public void validate_login(final String Username, final String Password, final validate listener) {
        listener.showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://melvinmathew0102.000webhostapp.com/StudentRegister.php/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.hideDialog();
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("Details");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = jsonArray.getJSONObject(i);
                        if (Username.equals(o.getString("UserID"))) u = false;
                        if (Username.equals(o.getString("UserID")) && Password.equals(o.getString("Password")))
                            pa = false;
                        if (Username.equals(o.getString("UserID")) && Password.equals(o.getString("Password"))) {
                            p = false;
                            listener.onSuccess();
                            break;
                        } else p = true;
                    }
                    if (p) listener.showToast("Login Unsuccessful");
                    if (u) listener.onUsernameError();
                    if (pa) listener.onPasswordError();
                } catch (JSONException e) {
                    listener.showToast(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.hideDialog();
                listener.showToast(error.getMessage());
            }
        });
       listener.volley(stringRequest);
    }

    @Override
    public void validate_signup(final String Username, String Password,final validate listener) {
        if (Username.isEmpty() || Password.isEmpty())
            listener.showToast("Ensure All details are Correct");
        else listener.showALert();
    }
}
