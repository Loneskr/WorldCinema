package com.example.worldcinema.UI.Collections;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Adapter.IconAdapter;
import com.example.worldcinema.R;

import java.util.ArrayList;
import java.util.List;

public class IconActivity extends AppCompatActivity {

    private ImageView imageView;
    private GridView gridIcons;
    private List<ItemIcons> iconList  = new ArrayList<>();
    private IconAdapter iconAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_choice);
        gridIcons = findViewById(R.id.gridChoiceIcons);
        imageView = findViewById(R.id.goChoiceIcons);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iconAdapter = new IconAdapter(this, R.layout.icon_item, iconList);
        setIconList();
        gridIcons.setAdapter(iconAdapter);

    }

    private void setIconList() {
        for (int i = 0; i < 36; i++) {
            iconList.add(new ItemIcons(R.drawable.airplane));
        }
        iconAdapter.notifyDataSetChanged();
    }

}
