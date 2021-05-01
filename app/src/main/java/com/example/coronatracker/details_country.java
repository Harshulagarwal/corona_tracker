package com.example.coronatracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class details_country extends AppCompatActivity {
    TextView textView, textView2, textView3, textView4, textView5, textView6, textView7;
    ImageView imageView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recylerlayout);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView.setText(appdata.locallist.get(appdata.curr_pos).name);
        textView2.setText("CASES " + "\n " + appdata.locallist.get(appdata.curr_pos).cases);
        textView3.setText("DEATHS" + "\n " + (appdata.locallist.get(appdata.curr_pos).deaths));
        textView6.setText("CASES TODAY" + "\n " + appdata.locallist.get(appdata.curr_pos).todaycases);
        textView7.setText("DEATHS TODAY" + "\n " + appdata.locallist.get(appdata.curr_pos).todaydeaths);
        textView4.setText("RECOVERED" + "\n " + appdata.locallist.get(appdata.curr_pos).recoverd);
        textView5.setText("ACTIVE" + "\n " + appdata.locallist.get(appdata.curr_pos).active);
        imageView.setVisibility(View.GONE);

        textView.setGravity(Gravity.CENTER);
    }
}
