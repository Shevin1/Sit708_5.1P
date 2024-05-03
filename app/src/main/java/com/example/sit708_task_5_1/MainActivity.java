package com.example.sit708_task_5_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<String> dataSource;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.LayoutManager layoutManager;
    private MyRvAdapter myRvAdapter;
    private RecyclerView newsRv;
    private RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.horizontalRv);

        //setting the data source
        dataSource = new ArrayList<>();
        dataSource.add("First News");
        dataSource.add("Second News");
        dataSource.add("Third News");
        dataSource.add("Fourth News");
        dataSource.add("Fifth News");
        dataSource.add("Sixth News");

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);

        int []array = new int[6];
        Arrays.fill(array, R.drawable.ic_launcher_background);

        newsRv = findViewById(R.id.newsRv);
        layoutManager = new GridLayoutManager(this, 2);
        newsRv.setLayoutManager(layoutManager);
        rvAdapter = new RvAdapter(array);
        newsRv.setAdapter(rvAdapter);
        newsRv.setHasFixedSize(true);

        rvAdapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String title = "Sample Title";
                String description = "Sample Description";
                int imageResId = R.drawable.ic_launcher_background;
                Log.d("NewsApp", "News item clicked, position: " + position);

                // Start a fragment transaction to display the NewsDetailFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.newsDetailFragmentContainer, NewsDetailFragment.newInstance(title, description, imageResId))
                        .addToBackStack(null)
                        .commit();

                // Make the container visible
                findViewById(R.id.newsDetailFragmentContainer).setVisibility(View.VISIBLE);
            }
        });

        myRvAdapter.setOnItemClickListener(new MyRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String title = dataSource.get(position);
                String description = "Sample Description";
                int imageResId = R.drawable.ic_launcher_background;

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.newsDetailFragmentContainer, NewsDetailFragment.newInstance(title, description, imageResId))
                        .addToBackStack(null)
                        .commit();

                // Make the container visible
                findViewById(R.id.newsDetailFragmentContainer).setVisibility(View.VISIBLE);
            }
        });
    }
}