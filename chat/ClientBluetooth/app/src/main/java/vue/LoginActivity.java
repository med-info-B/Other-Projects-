package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientbluetooth.R;

import controller.Authentification;


/**
 *
 class takes responsibility for authenticating users and directing it to suitable interfaces
 */
public class LoginActivity extends AppCompatActivity {

    private Intent intent_sign, intent_login;
    private EditText editLogin, editMdp;
 //   private Authentification authentification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        init();
//        authentification = new Authentification( getApplicationContext() );

    }

    public void init(){
        editLogin  = (EditText) findViewById( R.id.editText5 );
        editMdp = (EditText) findViewById( R.id.editText6 );
    }

    /**
     *    listennig du boutton login
     * @param v
     */
    public void login(View v){
        Integer login = 0;
        String mdp = "";
        try {
            login = Integer.parseInt(editLogin.getText().toString());
            mdp = editMdp.getText().toString();
        }catch(Exception e){
            if(login == 0 || mdp.equals( "" )){
                Toast.makeText( LoginActivity.this,"Saisie Incorrecte",Toast.LENGTH_SHORT ).show();
            }
        }
    //    if(authentification.exitUser( login ) && authentification.verifyPassWord( mdp )){
            intent_login = new Intent( LoginActivity.this, MenuActivity.class );
            startActivity( intent_login );
    //    }


    }

    /**
     *   listening du boutton sign up
     * @param v
     */
    public void signup(View v){
        intent_sign = new Intent( LoginActivity.this, LoginFormActivity.class );
        startActivity( intent_sign );
    }
}
