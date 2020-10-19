package com.harish.usdrivinglicensetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class ScoreActivity extends AppCompatActivity {

    private TextView scored,total, summary;
    private Button doneBtn;
    private Double percentage;
    private Double score,total_score;
    GifDrawable gifFromResource;
    GifImageView gifImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score= Double.valueOf(6);
        total_score= Double.valueOf(100);
        scored= findViewById(R.id.scored);
        total= findViewById(R.id.total);
        doneBtn= findViewById(R.id.done_btn);
        summary=findViewById(R.id.summary);
        gifImageView=findViewById(R.id.gifImageView2);


        loadAds();
        try{
           score= Double.valueOf(getIntent().getIntExtra("score",0));
           total_score= Double.valueOf(getIntent().getIntExtra("total",0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        scored.setText(String.valueOf(score));
        total.setText("OUT OF "+String.valueOf(total_score));
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            percentage = Double.valueOf(Math.round ((score / total_score) * 100));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(percentage>80)
        {
            summary.setText("Congratulations! You got "+percentage+"% in the test");

            gifImageView.setImageResource(R.drawable.celebration);

        }
        else {
            summary.setText("Better luck next time! You got "+percentage+"% in the test");
            gifImageView.setImageResource(R.drawable.lost);
        }




    }
    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}