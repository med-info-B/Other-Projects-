package model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;

import controller.HandleConnection;

import outils.Constantes;
import controller.MyHandlerView;

import static android.content.ContentValues.TAG;

public class Client {

    private BluetoothSocket socket;
    private BluetoothDevice device;
    private BluetoothAdapter adapter;
    private HandleConnection handleConnction;
    private MyHandlerView handler;
    private String cne;
    private String name;





    public Client(BluetoothDevice device, BluetoothAdapter adapter, MyHandlerView handler, Integer cne, String name) {
        this.cne = String.valueOf(cne);
        this.name = name;
        BluetoothSocket tmp = null;
        this.device = device;
        this.handler = handler;
        this.adapter = adapter;
        try {
            tmp = device.createRfcommSocketToServiceRecord( Constantes.MY_UUID );
        } catch (IOException e) {
            Log.e( TAG, "non", e );
        }
        socket = tmp;
    }

    public void connect() {
        adapter.cancelDiscovery();
        try {
            socket.connect();
            handleConnction = new HandleConnection( socket, handler );
            handleConnction.start();
            handleConnction.sendIdentity( cne,name);

        } catch (IOException e) {
            Log.e( TAG, "non coonect", e );
        }
    }
}
