package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.clientbluetooth.R;

public class PlayQuizActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_play_quiz_a_ctivity );

    }
    public void start(View v){
        intent = new Intent( PlayQuizActivity.this, MainActivity.class );
        startActivity( intent );
      //  startActivityForResult( intent, Constantes.REQUEST_CODE);
    }

}
