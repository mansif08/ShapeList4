package com.example.shapelist4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView recycleImage;
    private TextView recycleText;
    Shape selectedRecycling;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpViews();
        getRecycling();
    }
    private void setUpViews()
    {
        recycleImage = (ImageView) findViewById(R.id.shapeImage);
        recycleText = (TextView) findViewById(R.id.shapeName);
    }
    private void getRecycling()
    {
        Intent prevIntent = getIntent();
        int position = prevIntent.getIntExtra("position",0);
        selectedRecycling = MainActivity.shapeList.get(position);
        recycleImage.setImageResource(selectedRecycling.getImage());
        recycleText.setText(selectedRecycling.getName());
    }
}