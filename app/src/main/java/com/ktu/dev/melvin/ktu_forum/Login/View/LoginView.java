package com.ktu.dev.melvin.ktu_forum.Login.View;

import com.android.volley.toolbox.StringRequest;

public interface LoginView {
void success();
void showAlert();
void showDialog();
void hideDialog();
void usernameError();
void passwordError();
void toast(String Message);
void volley(StringRequest stringRequest);
}
