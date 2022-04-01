package com.example.worldcinema.Authorization;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Network.Handler.ErrorUtils;
import com.example.worldcinema.Network.Handler.RegistrationHandler;
import com.example.worldcinema.Network.Models.RegistrationBody;
import com.example.worldcinema.Network.Models.RegistrationResponse;
import com.example.worldcinema.Network.Service.IApiService;
import com.example.worldcinema.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private IApiService service = RegistrationHandler.getInstance().getService();
    private Button btnRegister;
    private Button btnHaveAccount;
    private EditText editTextEmail, editTextFirstName, editTextLastName, editTextPassword, editTextRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextEmail = findViewById(R.id.text_email);
        editTextFirstName = findViewById(R.id.text_name);
        editTextLastName = findViewById(R.id.text_second_name);
        editTextPassword = findViewById(R.id.text_password);
        editTextRepeatPassword = findViewById(R.id.text_repeat_password);
        btnRegister = findViewById(R.id.btnLogIn);
        btnHaveAccount = findViewById(R.id.btnSignIn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
        btnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAuthorization();
            }
        });

    }

    private void doRegister(){
        AsyncTask.execute(() -> {
            service.doRegistration(setRegistrationBody()).enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Успешная регистрация!", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(SignUpActivity.this, serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Произошла неизвестная ошибка!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    startAuthorization();
                }
            });
        });
    }

    private RegistrationBody setRegistrationBody() {
        return new RegistrationBody(editTextEmail.getText().toString(), editTextPassword.getText().toString(),
                editTextFirstName.getText().toString(), editTextLastName.getText().toString());
    }

    private void startAuthorization(){
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        finish();
    }
}
