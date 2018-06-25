package com.ktu.dev.melvin.ktu_forum.Login.Presenter;

import com.android.volley.toolbox.StringRequest;
import com.ktu.dev.melvin.ktu_forum.Login.Model.LoginModel;
import com.ktu.dev.melvin.ktu_forum.Login.Model.LoginModelImpl;
import com.ktu.dev.melvin.ktu_forum.Login.View.LoginView;

public class LoginPresenterImpl implements LoginPresenter,LoginModel.validate{
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginModel=new LoginModelImpl();
    }

    @Override
    public void mlogin(String Username, String Password) {
        loginModel.validate_login(Username,Password,this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSuccess() {
        loginView.success();
    }

    @Override
    public void onUsernameError() {
        loginView.usernameError();
    }

    @Override
    public void onPasswordError() {
        loginView.passwordError();
    }

    @Override
    public void showDialog() {
        loginView.showDialog();
    }

    @Override
    public void hideDialog() {
        loginView.hideDialog();
    }

    @Override
    public void showToast(String Message) {
        loginView.toast(Message);
    }

    @Override
    public void volley(StringRequest stringRequest) {
        loginView.volley(stringRequest);
    }
}
