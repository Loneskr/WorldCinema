package com.example.worldcinema.UI.Collections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Menu.MainActivity;
import com.example.worldcinema.R;
import com.google.android.material.button.MaterialButton;

public class CollectionCreate extends AppCompatActivity {

    private MaterialButton btnChooseIcon;
    private Bundle bundle;
    private ImageView iconChoose;
    private Button btnSaveCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_create);

        btnChooseIcon = findViewById(R.id.btnSelectIcon);
        iconChoose = findViewById(R.id.btnChoose);
        btnSaveCollection = findViewById(R.id.bntSaveCollection);
        btnSaveCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CollectionCreate.this, MainActivity.class));
            }
        });
        bundle = getIntent().getExtras();
        if(bundle != null) {
            iconChoose.setImageResource(bundle.getInt("icon"));
        }
        btnChooseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CollectionCreate.this, IconActivity.class));
            }
        });
    }

    public void goBack(View view) {
        finish();
    }
}
