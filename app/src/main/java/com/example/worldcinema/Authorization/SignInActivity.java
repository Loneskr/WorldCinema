package com.example.worldcinema.Authorization;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.MainActivity;
import com.example.worldcinema.Network.Handler.ApiHandler;
import com.example.worldcinema.Network.Handler.ErrorUtils;
import com.example.worldcinema.Network.Models.LoginBody;
import com.example.worldcinema.Network.Models.LoginResponse;
import com.example.worldcinema.Network.Service.IApiService;
import com.example.worldcinema.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private TextInputEditText editEmail, editPassword;

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;
    private boolean isSignIn = false;
    IApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        initializeViews();
        token = preferences.getString("token", "");
        if(token != ""){
            startMenu();
        }
    }


    private void initializeViews() {
        editEmail = findViewById(R.id.text_email);
        editPassword = findViewById(R.id.text_password);
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(SignInActivity.this, SignInActivity.class));
        finish();
    }

    public void goToMenu(View view) {
        doLogin();
    }
    private void doLogin(){
        AsyncTask.execute(() -> {
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        editor.putString("token", response.body().getToken()).apply();
                        startMenu();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(SignInActivity.this, serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignInActivity.this, "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    public void startMenu() {
        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        finish();
    }
    public LoginBody getLoginData() {
        return new LoginBody(editEmail.getText().toString(), editPassword.getText().toString());
    }
}
