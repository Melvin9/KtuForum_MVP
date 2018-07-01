package com.ktu.dev.melvin.ktu_forum.Login.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ktu.dev.melvin.ktu_forum.Login.Presenter.LoginPresenter;
import com.ktu.dev.melvin.ktu_forum.Login.Presenter.LoginPresenterImpl;
import com.ktu.dev.melvin.ktu_forum.MainScreen.MainActivity;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.R;

public class LoginActivity extends AppCompatActivity implements LoginView {
    Button login, signup;
    EditText userid, password;
    LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userid = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        loginPresenter=new LoginPresenterImpl(this);
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //signup
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.mlogin(userid.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
        ViewAnswerFragment.user_id=userid.getText().toString();
        try {
            Intent i1 = new Intent(LoginActivity.this, MainActivity.class);
            i1.putExtra("us", userid.getText().toString());
            startActivity(i1);
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showDialog() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Validating");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Progress Dialog");
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void usernameError() {
        userid.setError("Wrong UserName");
    }

    @Override
    public void passwordError() {
        password.setError("Wrong Password");
    }

    @Override
    public void toast(String Message) {
        Toast.makeText(LoginActivity.this,Message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void volley(StringRequest stringRequest) {
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }
}
