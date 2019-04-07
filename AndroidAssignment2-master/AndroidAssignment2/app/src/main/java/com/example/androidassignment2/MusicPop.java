package com.example.androidassignment2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MusicPop {
    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<PojoMusic> getMusic();
}
