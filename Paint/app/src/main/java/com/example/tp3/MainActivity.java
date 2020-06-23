package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dessin dessin = findViewById(R.id.dessin);

        Intent i = getIntent();
        int size = i.getIntExtra(Main2Activity.keySize, 0);
        int color = i.getIntExtra(Main2Activity.keyColor,0);
        dessin.setColor(color);
        dessin.setEpsseur((float) size);


    }
}
