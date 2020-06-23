package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    public   int size = 0;
    public   int color;
    public static String keySize = "KeySize";
    public static String keyColor = "Keycolor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Spinner list = findViewById(R.id.spinner);
        SeekBar seekBar = findViewById(R.id.seekBar);
        Button valide = findViewById(R.id.button);
        final Intent intent = new Intent(Main2Activity.this,MainActivity.class);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                size = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Main2Activity.this, "Seek bar progress is :" + size,
                        Toast.LENGTH_SHORT).show();
            }
        });
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Blue");
        colors.add("Red");
        colors.add("Green");
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        list.setAdapter(adapter);


        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((String)adapter.getItem(position)){
                    case "Blue" : color = Color.BLUE;
                    break;
                    case "Red" : color = Color.RED;
                    break;
                    case "Green" : color = Color.GREEN;
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + (String) adapter.getItem(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {




            }
            });


        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent.putExtra(keySize, size);
                    intent.putExtra(keyColor,color);
                    startActivity(intent);
            }
        });

            
    }



}
