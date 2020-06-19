package outils.protocol;


import java.util.Collection;

public interface QuizProtocol {
        default  void sendIdentity(String cne, String name){};
        default void sendIdOK(){};
        default void sendReady(){};
        default void sendIdBad(){};

        default void sendAskQuestion(){};
        default void sendQuestion(String question , Collection<String> r) {};
}
