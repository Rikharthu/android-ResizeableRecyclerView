package com.example.uberv.resizeablerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;

import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        CustomLayoutManager manager = new CustomLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.TOP);
        snapHelper.attachToRecyclerView(mRecyclerView);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.wallpaper_1);
        images.add(R.drawable.wallpaper_2);
        images.add(R.drawable.wallpaper_4);
        images.add(R.drawable.wallpaper_3);
        images.add(R.drawable.wallpaper_1);
        images.add(R.drawable.wallpaper_2);
        images.add(R.drawable.wallpaper_4);
        images.add(R.drawable.wallpaper_3);
        images.add(R.drawable.wallpaper_1);
        images.add(R.drawable.wallpaper_2);
        images.add(R.drawable.wallpaper_4);
        images.add(R.drawable.wallpaper_3);

        CustomAdapter customAdapter = new CustomAdapter(this,images);
        mRecyclerView.setAdapter(customAdapter);
    }
}
