package com.example.androidassignment2;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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


        Toolbar toolbar = findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tl_tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.classic));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.pop));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.rock));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        getMusicClassic();
        getMusicPop();
        getMusicRock();
    }

    private void getMusicClassic() {
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
    }

    private void getMusicRock() {
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

    private void getMusicPop() {
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
    }



    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://itunes.apple.com/").addConverterFactory(GsonConverterFactory.create()).build();
        apiclassic = retrofit.create(MusicClassic.class);
        apipop = retrofit.create(MusicPop.class);
        apirock = retrofit.create(MusicRock.class);
    }





}
