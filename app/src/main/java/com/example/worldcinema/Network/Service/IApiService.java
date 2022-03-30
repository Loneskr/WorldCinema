package com.example.worldcinema.Network.Service;

import com.example.worldcinema.Network.Models.ChatMessageResponse;
import com.example.worldcinema.Network.Models.ChatResponse;
import com.example.worldcinema.Network.Models.LoginBody;
import com.example.worldcinema.Network.Models.LoginResponse;
import com.example.worldcinema.Network.Models.MessageBody;
import com.example.worldcinema.Network.Models.MovieCoverResponse;
import com.example.worldcinema.Network.Models.MovieResponse;
import com.example.worldcinema.Network.Models.ProfileResponse;
import com.example.worldcinema.Network.Models.RegistrationBody;
import com.example.worldcinema.Network.Models.RegistrationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody loginBody);
    @POST("register")
    Call<RegistrationResponse> doRegistration(@Body RegistrationBody registerBody);
    @GET("cover")
    Call<MovieCoverResponse> getCovers();
    @GET("movies?filter=new")
    Call<List<MovieResponse>> getMovies();
    @GET("user")
    Call<List<ProfileResponse>> getData(@Header("Authorization") String token);
    @GET("chats/{movieId}")
    Call<List<ChatResponse>> getChats(@Path("movieId") String movieId);
    @GET("chats/{chatId}/messages")
    Call<List<ChatMessageResponse>> getChatMessages(@Header("Authorization") String token, @Path("chatId") String chatId);
    @POST("chats/{chatId}/messages")
    Call<List<ChatMessageResponse>> doMessage(@Header("Authorization") String token, @Path("chatId") String chatId, @Body MessageBody message);
}
