package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Assurance {
    private int id;
    private int idavion;
    private float montant;
    private String datepayement;
    private String dateexpiration;
    private String nommarque;
    private String modele;
    private String matricule;


    public Assurance() {
    }

    public Assurance(int id, int idavion, float montant, String datepayement, String dateexpiration, String nommarque, String modele, String matricule) {
        this.id = id;
        this.idavion = idavion;
        this.montant = montant;
        this.datepayement = datepayement;
        this.dateexpiration = dateexpiration;
        this.nommarque = nommarque;
        this.modele = modele;
        this.matricule = matricule;
    }

    public static Vector<Assurance> selectAssurance1Mois() throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_assurance1mois order by dateexpiration asc ";
        ResultSet res = sta.executeQuery(req);
        Vector<Assurance> liste = null;
        try {

            liste = new Vector<Assurance>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Assurance(res.getInt(1),res.getInt(2),res.getFloat(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)));
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
    public static Vector<Assurance> selectAssurance3Mois() throws Exception {
            Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String req = "select * from v_assurance3mois order by dateexpiration asc ";
            ResultSet res = sta.executeQuery(req);
            Vector<Assurance> liste = null;
            try {

                liste = new Vector<Assurance>();
                res.first();
                res.beforeFirst();
                while (res.next()) {
                    liste.add(new Assurance(res.getInt(1),res.getInt(2),res.getFloat(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)));
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdavion() {
        return idavion;
    }

    public void setIdavion(int idavion) {
        this.idavion = idavion;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDatepayement() {
        return datepayement;
    }

    public void setDatepayement(String datepayement) {
        this.datepayement = datepayement;
    }

    public String getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(String dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getNommarque() {
        return nommarque;
    }

    public void setNommarque(String nommarque) {
        this.nommarque = nommarque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
