package com.ktu.dev.melvin.ktu_forum.Login.Model;

import com.android.volley.toolbox.StringRequest;

public interface LoginModel {
interface validate{
    void onSuccess();
    void onUsernameError();
    void onPasswordError();
    void showDialog();
    void hideDialog();
    void showToast(String Message);
    void volley(StringRequest stringRequest);
}
void validate_login(String Username,String Password,validate listener);
}
