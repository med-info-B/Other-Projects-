package outils.protocol;

import java.io.OutputStream;
import java.io.PrintWriter;

public class QuizOutput implements QuizProtocol {

    private PrintWriter os;

    public QuizOutput(OutputStream out){
        os = new PrintWriter( out, true );
    }

    @Override
    public void sendIdentity(String cne, String name) {
        os.println("ID");
        os.println(cne);
        os.println(name);
    }


    @Override
    public void sendReady() {
        os.println("Ready");
    }
}
