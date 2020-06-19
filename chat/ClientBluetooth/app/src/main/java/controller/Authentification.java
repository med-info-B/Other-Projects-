package controller;

import android.content.Context;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.Profil;
import outils.DataBase;

public class Authentification {
    private Profil profil;
    private static DataBase dataBase;
    private String passHash;
    private String algorithm = "MD5";

    public Authentification(Context context){
        this.dataBase = new DataBase( context );
    }

    /*
            Method pour create un compte
     */
    public void createAccount(String ine,String prenom, String nom, String formation,String mdp){
        try {
            passHash = generateHash( mdp,  algorithm );
            this.profil = new Profil(prenom,nom,ine,formation,mdp);
            dataBase.createEtudiant( profil );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public boolean exitUser(Integer icn){
        return  false;
    }

    public boolean verifyPassWord(String mdp){
        try {
            passHash = generateHash( mdp,  algorithm );
            /**
             *  il faut vérifié mot pass
             */
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String generateHash(String mdp, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance( algorithm );
        messageDigest.digest();
        byte[] hash = messageDigest.digest( mdp.getBytes());
        return bytesToString( hash );
    }

    private final char[]  hehArray = "01234567891BCDEF".toCharArray();
    private String bytesToString(byte[] bytes){
        char[] hexChars  = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++){
            int v = bytes[i] & 0XFF;
            hexChars[i * 2] = hehArray[v >>> 4];
            hexChars[i * 2 + 1] = hehArray[v & 0x0F];
        }
        return new String( hexChars );
    }

}
