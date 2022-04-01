package com.example.worldcinema.Chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.Adapter.ChatAdapter;
import com.example.worldcinema.Discussions.DiscussionsActivity;
import com.example.worldcinema.Network.Handler.ChatHandler;
import com.example.worldcinema.Network.Models.ChatMessageResponse;
import com.example.worldcinema.Network.Models.ChatResponse;
import com.example.worldcinema.Network.Models.MessageBody;
import com.example.worldcinema.Network.Service.IApiService;
import com.example.worldcinema.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private FrameLayout frameSendImage;
    private ChatAdapter chatAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private EditText message;
    private String movieId, chatId;
    private Bundle bundle;
    private TextView txtNameMovie;
    private String token;
    private IApiService service = ChatHandler.getInstance().getService();
    private SharedPreferences sharedPreferences;
    private String myFirstName, mySecondName;

    private ArrayList<ChatMessageResponse> chatMessageResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerChat);
        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        message = findViewById(R.id.txtEditMessage);

        myFirstName = sharedPreferences.getString("firstName", "");
        mySecondName = sharedPreferences.getString("lastName", "");
        Toast.makeText(this, myFirstName + "||" + mySecondName, Toast.LENGTH_SHORT).show();
        txtNameMovie = findViewById(R.id.txtChatName);
        bundle = getIntent().getExtras();
        if(bundle!=null){
            movieId = bundle.getString("movieId");
            getChats(movieId);
            Toast.makeText(this, "" + movieId, Toast.LENGTH_SHORT).show();
        }

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getChatMessages(token, chatId);
            }
        }, 0,3000);
        frameSendImage = findViewById(R.id.frameSendImage);
        frameSendImage.setOnClickListener(view -> {
            sendMessage(token, chatId, new MessageBody(message.getText().toString()));
            getChatMessages(token, chatId);
            chatAdapter.notifyDataSetChanged();
        });
    }

    public void goToDiscussion(View view) {
        startActivity(new Intent(ChatActivity.this, DiscussionsActivity.class));
        finish();
    }

    private void getChats(String movieId) {
        AsyncTask.execute(() -> {
            service.getChats(movieId).enqueue(new Callback<List<ChatResponse>>() {
                @Override
                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                    if(response.isSuccessful()){
                        if(response.body().size() == 0){
                            Toast.makeText(ChatActivity.this, "Такого чата нет!", Toast.LENGTH_SHORT).show();
                        } else {
                            txtNameMovie.setText(response.body().get(0).getName());
                            chatId = response.body().get(0).getChatId();
                            getChatMessages(token, chatId);
                        }
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "####", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка, как-нибудь починится", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void getChatMessages(String token, String chatId) {
        AsyncTask.execute(() -> {
            service.getChatMessages(token, chatId).enqueue(new Callback<List<ChatMessageResponse>>() {
                @Override
                public void onResponse(Call<List<ChatMessageResponse>> call, Response<List<ChatMessageResponse>> response) {
                    if(response.isSuccessful()){
                        chatMessageResponses = new ArrayList<>(response.body());
                        for (int i = 0; i < response.body().size(); i++) {
                            chatMessageResponses.get(i).setViewType(1);
                            if(chatMessageResponses.get(i).getFirstName().equals(myFirstName) &&
                                    chatMessageResponses.get(i).getLastName().equals(mySecondName) ) {
                                chatMessageResponses.get(i).setViewType(0);
                            }
                        }
                        chatAdapter = new ChatAdapter(chatMessageResponses);
                        recyclerView.setAdapter(chatAdapter);
                        recyclerView.scrollToPosition(chatMessageResponses.size() - 1);
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "####", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Какая-то неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<ChatMessageResponse>> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "Произошла прикольная ошибка", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void sendMessage(String token, String chatId, MessageBody text) {
        AsyncTask.execute(() -> {
            service.doMessage(token, chatId, text).enqueue(new Callback<List<ChatMessageResponse>>() {
                @Override
                public void onResponse(Call<List<ChatMessageResponse>> call, Response<List<ChatMessageResponse>> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "########", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "####", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatMessageResponse>> call, Throwable t) {

                }
            });
        });
    }
}
