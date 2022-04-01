package com.example.worldcinema.UI.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.worldcinema.Authorization.SignInActivity;
import com.example.worldcinema.Discussions.DiscussionsActivity;
import com.example.worldcinema.Network.Handler.ProfileHandler;
import com.example.worldcinema.Network.Models.ProfileResponse;
import com.example.worldcinema.Network.Service.IApiService;
import com.example.worldcinema.R;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String TAG = "ProfileFragment";
    private IApiService service = ProfileHandler.getInstance().getService();
    private String token;
    private TextView txtName;
    private TextView txtEmail;
    private ImageView imgProfile;
    private LinearLayout linearDiscussions;
    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        editor = getContext().getSharedPreferences("token", Context.MODE_PRIVATE).edit();
        token = sharedPreferences.getString("token", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        txtName = view.findViewById(R.id.txt_profile_name);
        txtEmail = view.findViewById(R.id.txt_profile_email);
        imgProfile = view.findViewById(R.id.img_profile_photo);

        linearDiscussions = view.findViewById(R.id.linear_layout);
        linearDiscussions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DiscussionsActivity.class));
            }
        });

        Button materialButton = view.findViewById(R.id.btnSignOut);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().remove("token").commit();
                startAuthorizationActivity();
            }
        });
        getUserInfo();
        return view;
    }

    private void startAuthorizationActivity(){
        startActivity(new Intent(getContext(), SignInActivity.class));
    }

    private void getUserInfo(){
        AsyncTask.execute(() -> {
            service.getData(token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    txtEmail.setText(response.body().get(0).getEmail());
                    txtName.setText(response.body().get(0).getFirstName() + " " + response.body().get(0).getLastName());
                    editor.putString("firstName", response.body().get(0).getFirstName()).apply();
                    editor.putString("lastName", response.body().get(0).getLastName()).apply();
                    Picasso.with(getContext()).load("http://cinema.areas.su/up/images/" + response.body().get(0).getAvatar()).into(imgProfile);
                }

                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
