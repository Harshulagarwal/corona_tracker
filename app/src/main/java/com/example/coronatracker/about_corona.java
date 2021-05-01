package com.example.coronatracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class about_corona extends AppCompatActivity {
    TextView textView10, textView11, textView12, textView13;
    Button button;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_corona);
        button = findViewById(R.id.livebutton);
        scrollView = findViewById(R.id.scroll);


        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        String intro = "Coronavirus disease (COVID-19) is an infectious disease caused by a new virus.The disease causes respiratory illness (like the flu) with symptoms such as a cough, fever, and in more severe cases, difficulty breathing. You can protect yourself by washing your hands frequently, avoiding touching your face, and avoiding close contact (1 meter or 3 feet) with people who are unwell.";
        String spread = "Coronavirus disease spreads primarily through contact with an infected person when they cough or sneeze. It also spreads when a person touches a surface or object that has the virus on it, then touches their eyes, nose, or mouth. ";
        String symptoms = "People may be sick with the virus for 1 to 14 days before developing symptoms. The most common symptoms of coronavirus disease (COVID-19) are fever, tiredness, and dry cough. Most people (about 80%) recover from the disease without needing special treatment.\n" +
                "More rarely, the disease can be serious and even fatal. Older people, and people with other medical conditions (such as asthma, diabetes, or heart disease), may be more vulnerable to becoming severely ill.";
        String cure = "There is no specific medicine to prevent or treat coronavirus disease (COVID-19). People may need supportive care to help them breathe.\n" +
                "If you have mild symptoms, stay at home until you’ve recovered. You can relieve your symptoms if you:" +
                "rest and sleep, " +
                "keep warm, " +
                "drink plenty of liquids, " +
                "use a room humidifier or take a hot shower to help ease a sore throat and cough ";
        textView10.setText(intro);
        textView11.setText(spread);
        textView12.setText(symptoms);
        textView13.setText(cure);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(about_corona.this, MainActivity.class));
                finish();
            }
        });

    }
}

