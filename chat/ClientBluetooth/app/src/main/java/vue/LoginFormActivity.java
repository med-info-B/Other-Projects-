package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clientbluetooth.R;

import controller.Authentification;

public class LoginFormActivity extends AppCompatActivity {

    EditText editcne, editnom, editprenom, editfor, editmdp , editmpdc;
    Authentification authentification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_form );
        init();
    }



    private void init(){
        editcne = findViewById( R.id.editText13 );
        editnom =findViewById( R.id.editText12);
        editprenom = findViewById( R.id.editText11);
        editfor = findViewById( R.id.editText14 );
        editmdp = findViewById( R.id.editText15 );
        editmpdc = findViewById( R.id.editText16 );
        authentification = new Authentification(this);
    }
    public void create(View v){
        String cne = "";
        String nom= "";
        String prenom = "";
        String formation = "";
        String pass = "";
        String passC  = "";

        try {

            cne =  editcne.getText().toString();
            nom = editnom.getText().toString();
            prenom = editprenom.getText().toString();
            formation = editfor.getText().toString();
            pass = editmdp.getText().toString();
            passC = editmpdc.getText().toString();
        }catch(Exception e){
            Toast.makeText( LoginFormActivity.this,"Saisie Incorrecte",Toast.LENGTH_SHORT ).show();
        }
        if(!cne.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !formation.isEmpty()){
            if((!pass.isEmpty() && !passC.isEmpty()) && (pass.equals( passC ))){
                authentification.createAccount( cne,nom,prenom,formation,pass);
                Toast.makeText( LoginFormActivity.this," voilà ",Toast.LENGTH_SHORT ).show();
            }

        }





    }
}
