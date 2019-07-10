package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReqresApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}
