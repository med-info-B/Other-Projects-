package vue;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clientbluetooth.R;

import java.util.Set;

import model.Client;
import controller.MyHandlerView;
import outils.Constantes;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter ;
    public TextView textView;
    Button button;
    LinearLayout linearLayout;
    MyHandlerView handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initialisation();
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        BluetoothDevice d = null;
        for(BluetoothDevice device : devices){
            d =device;
            break;
        }
                Client clientQuiz = new Client( d , bluetoothAdapter, handler, 21515602 , "med");
                clientQuiz.connect();

    }

    private void initialisation(){
        button = findViewById( R.id.button);
        textView = findViewById( R.id.textView );
        linearLayout = findViewById( R.id.linerLayout );
        handler = new MyHandlerView(linearLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!bluetoothAdapter.isEnabled()){
            Intent active  = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
            startActivityForResult( active, Constantes.REQEST_ENABLE_BLUETOOTH);
        }
    }
}






