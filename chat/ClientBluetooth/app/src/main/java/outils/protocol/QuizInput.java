package outils.protocol;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizInput {


    private QuizProtocol handle;
    private InputStream in;
    private boolean stop = false;

    public QuizInput(InputStream in, QuizProtocol handle){
        this.in =in;
        this.handle = handle;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void doRun(){
        String msg, msg1;
        try(BufferedReader is = new BufferedReader( new InputStreamReader( in ) )){
            while (!stop){

                String line = is.readLine();
                switch (line){
                    case "ID" :      msg = is.readLine();
                                     msg1 = is.readLine();
                                     handle.sendIdentity(msg,msg1);
                                     break;
                    case "ID OK" :   handle.sendIdOK();
                                     break;
                    case "ID BAD" :  handle.sendIdBad();
                                     break;


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
