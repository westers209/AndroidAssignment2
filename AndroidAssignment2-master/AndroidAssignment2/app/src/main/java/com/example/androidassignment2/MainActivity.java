package com.example.androidassignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MusicClassic apiclassic;
    MusicPop apipop;
    MusicRock apirock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRetrofit();
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        getMusics();
    }

    private void getMusics() {
        apiclassic.getMusic().enqueue(new Callback<PojoMusic>() {
            @Override
            public void onResponse(Call<PojoMusic> call, Response<PojoMusic> response) {
                recyclerView.setAdapter(new CustomAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<PojoMusic> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something broke",Toast.LENGTH_SHORT).show();
            }
        });
        apipop.getMusic().enqueue(new Callback<PojoMusic>() {
            @Override
            public void onResponse(Call<PojoMusic> call, Response<PojoMusic> response) {
                recyclerView.setAdapter(new CustomAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<PojoMusic> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something broke",Toast.LENGTH_SHORT).show();
            }
        });
        apirock.getMusic().enqueue(new Callback<PojoMusic>() {
            @Override
            public void onResponse(Call<PojoMusic> call, Response<PojoMusic> response) {
                recyclerView.setAdapter(new CustomAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<PojoMusic> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something broke",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://itunes.apple.com/").addConverterFactory(GsonConverterFactory.create()).build();
        apiclassic = retrofit.create(MusicClassic.class);
        apipop = retrofit.create(MusicPop.class);
        apirock = retrofit.create(MusicRock.class);
    }
}
