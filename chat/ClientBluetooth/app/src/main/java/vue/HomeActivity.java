package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clientbluetooth.R;


/**
 *     Home activity class  : root
 */
public class HomeActivity extends AppCompatActivity {

     private Intent intent;
     private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
    }

    /**
     *   listening of button which is identified by id button
     * @param v
     */
    public void start(View v ){
        if(player != null){
            player.start();
        }
        intent = new Intent( HomeActivity.this, LoginActivity.class );
        startActivity( intent );
    }


    /**
     *  this method represents a state in the life cycle of this activity
     *  which is implemented when the activity is launched
     */
    @Override
    protected void onResume() {
        super.onResume();
        player = MediaPlayer.create( this, R.raw.clic );
    }
}


