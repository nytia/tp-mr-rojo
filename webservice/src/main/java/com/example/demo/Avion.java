package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Avion {
    private int id;
    private String marque;
    private String modele;

    private String matricule;

    private int idmarque;

    private String img;
    private int nombrePlace;



    public Avion(int id, String marque, String modele) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
    }
    public Avion() {
    }

    public Avion(int id, String marque, String modele, String matricule, int idmarque,String img,int nombrePlace) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.matricule = matricule;
        this.idmarque = idmarque;
        this.img = img;
        this.nombrePlace = nombrePlace;
    }

    public int getId() {
        return id;
    }

    public static Vector<Avion> selectAllAvion() throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_avion ";
        ResultSet res = sta.executeQuery(req);
        Vector<Avion> liste = null;
        try {
            liste = new Vector<Avion>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Avion(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6),res.getInt(7)));
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
    public static Avion selectAvion(String idv) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_avion where id="+idv;
        ResultSet res = sta.executeQuery(req);
        Avion liste = null;
        try {

            liste = new Avion();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste=new Avion(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6),res.getInt(7));
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

    public static void insertAvion( Avion ve) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into avion(idmarque,modele,matricule) values (" + ve.getIdmarque() + ",'"
                    + ve.getModele() + "','" + ve.getMatricule() +"')";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
public static void updateAvion(Avion ve, String idv) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "update avion set modele='"+ve.getModele()+"',matricule='"+ ve.getMatricule() +"' where id="+idv;
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
    public static void deleteAvion(String idv) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "delete from avion where id ="+idv;
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
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

    public int getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }
}
