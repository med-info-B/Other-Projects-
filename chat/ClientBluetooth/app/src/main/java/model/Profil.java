package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Profil")

public class Profil {


    @DatabaseField(columnName = "ine")
    String ine;
    @DatabaseField(columnName = "nom")
    String nom;
    @DatabaseField(columnName = "prenom")
    String prenom;
    @DatabaseField(columnName = "formation")
    String formation;
    @DatabaseField(columnName = "mdp")
    String mdp;


    public Profil(String prenom, String nom, String ine, String formation, String mdp){
        this.prenom = prenom;
        this.nom = nom;
        this.ine = ine;
        this.formation = formation;
        this.mdp = mdp;
    }




    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getIne() {
        return ine;
    }

    public void setIne(String  ine) {
        this.ine = ine;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
