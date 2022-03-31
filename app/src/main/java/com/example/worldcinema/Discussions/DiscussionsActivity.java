package com.example.worldcinema.Discussions;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.Adapter.DiscussionsAdapter;
import com.example.worldcinema.Network.Handler.ChatHandler;
import com.example.worldcinema.Network.Service.IApiService;
import com.example.worldcinema.R;

import java.util.ArrayList;

public class DiscussionsActivity extends AppCompatActivity {
    private RecyclerView recyclerDiscussions;
    private LinearLayoutManager layoutManager;
    private DiscussionsAdapter discussionsAdapter;
    private ArrayList<Discussions> discussionsList = new ArrayList<>();;
    private IApiService service = ChatHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussions);

        recyclerDiscussions = findViewById(R.id.recyclerDiscussions);

        discussionsList.add(new Discussions(R.drawable.arrow_avatar, "Стрела", "Иван: Смотрели уже последнюю серию? Я просто поверить не могу в..."));
        discussionsList.add(new Discussions(R.drawable.arrow_avatar, "Стрела", "Иван: Смотрели уже последнюю серию? Я просто поверить не могу в..."));
        discussionsList.add(new Discussions(R.drawable.arrow_avatar, "Стрела", "Иван: Смотрели уже последнюю серию? Я просто поверить не могу в..."));

        layoutManager = new LinearLayoutManager(DiscussionsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerDiscussions.setLayoutManager(layoutManager);

        discussionsAdapter = new DiscussionsAdapter(discussionsList, DiscussionsActivity.this);
        recyclerDiscussions.setAdapter(discussionsAdapter);
    }

    public void goToProfile(View view) {
        finish();
    }

}
