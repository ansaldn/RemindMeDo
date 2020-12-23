package com.example.remindmedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {
    Button logIn, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logIn = (Button)findViewById(R.id.logIn);
        signUp = (Button)findViewById(R.id.signUp);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(splashScreen.this, newLogOn.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(splashScreen.this, newsignUp.class);
                //startActivity(intent);
            }
        });
    }

}