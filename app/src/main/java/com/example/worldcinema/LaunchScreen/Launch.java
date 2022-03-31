package com.example.worldcinema.LaunchScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Authorization.SignInActivity;
import com.example.worldcinema.R;

public class Launch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Launch.this.startActivity(new Intent(Launch.this, SignInActivity.class));
                Launch.this.finish();
            }
        }, 1000);
    }
}
