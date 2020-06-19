package controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.clientbluetooth.R;

import outils.Constantes;


public class MyHandlerView extends Handler {

    Context context;
    TextView textView;
    LinearLayout linearLayout;
    Button button;
    public MyHandlerView(LinearLayout linearLayout ){
        this.context = context;
        this.linearLayout = linearLayout;
        textView = linearLayout.findViewById( R.id.textView );
        button = linearLayout.findViewById( R.id.button );
    }
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage( msg );
        switch (msg.what){
            case Constantes.COONNECTEDC : textView.setText( "Connected " );
            break;
            case Constantes.NAMEOK: textView.setText( "Name Ok  .... " );
            break;
        }

        String str = msg.getData().getString( "Name ok" );
        textView.setText( str );


    }
}
