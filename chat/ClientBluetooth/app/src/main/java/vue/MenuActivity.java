package vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.clientbluetooth.R;

public class MenuActivity extends AppCompatActivity {

    Button histB, sessionB, paraB, homeB;
    Intent histT, sessionT, paraT, homeT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu );
        init();

        histB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                histT = new Intent( MenuActivity.this, HistoricalActivity.class );
                startActivity( histT );
            }
        } );

        sessionB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionT = new Intent( MenuActivity.this, HistoricalActivity.class );
                startActivity( sessionT );
            }
        } );

        paraB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paraT = new Intent( MenuActivity.this, HistoricalActivity.class );
                startActivity( paraT );
            }
        } );

        homeB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeT = new Intent( MenuActivity.this, HomeActivity.class );
                startActivity( homeT );
            }
        } );


    }



    private void init(){
        histB  = findViewById( R.id.button11 );
        sessionB = findViewById( R.id.session );
        paraB = findViewById( R.id.parametre );
        homeB = findViewById( R.id.button10 );
    }
}
