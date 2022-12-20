package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Utilisateur {
    private int id;
    private String nom;
    private String mdp;


    public Utilisateur(int id, String nom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.mdp = mdp;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static Vector<Utilisateur> getAllUtilisateur() throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from utilisateur";
        ResultSet res = sta.executeQuery(req);
        Vector<Utilisateur> liste = null;
        try {

            liste = new Vector<Utilisateur>();
            res.first();
            res.beforeFirst();
            if(res.next()) {
                liste.add(new Utilisateur(res.getInt(1),res.getString(2),res.getString(3)));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null)
                res.close();
            if (sta != null)
                sta.close();

        }
        return liste;
    }

}
