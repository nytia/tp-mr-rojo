package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Kilometrage {
    private int id;

    public Kilometrage(int idavion, float totalkm) {
        this.idavion = idavion;
        this.totalkm = totalkm;
    }

    public int getIdavion() {
        return idavion;
    }

    public void setIdavion(int idavion) {
        this.idavion = idavion;
    }

    private int idavion;
    private int debutkm;
    private int finkm;
    private String datekm;

    private float totalkm;

    public Kilometrage(int id, int idavion, int debutkm, int finkm, String datekm) {
        this.id = id;
        this.idavion = idavion;
        this.debutkm = debutkm;
        this.finkm = finkm;
        this.datekm = datekm;
    }

    public Kilometrage() {
    }

    public static Vector<Kilometrage> selectAllKilometrage() throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_kilometrage ";
        ResultSet res = sta.executeQuery(req);
        Vector<Kilometrage> liste = null;
        try {

            liste = new Vector<Kilometrage>();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste.add(new Kilometrage(res.getInt(1),res.getFloat(2)));
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
    public static Kilometrage selectKilometrage(String idv) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String req = "select * from v_kilometrage where idavion="+idv;
        ResultSet res = sta.executeQuery(req);
        Kilometrage liste = null;
        try {

            liste = new Kilometrage();
            res.first();
            res.beforeFirst();
            while (res.next()) {
                liste=new Kilometrage(res.getInt(1),res.getFloat(2));
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

    public static void insertKilometrage( Kilometrage ve) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "insert into kilometrage(idavion,debutkm,finkm,datekm) values (" + ve.getIdavion() + ","
                    + ve.getDebutkm() + "," + ve.getFinkm() +",'"+ve.getDatekm()+"')";
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
    public static void updateKilometrage( Kilometrage ve,String idv) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "update kilometrage set debutkm="+ve.getDebutkm()+",finkm="+ ve.getFinkm() +" where id="+idv;
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
    public static void deleteKilometrage(String idv) throws Exception {
        Connection con= DriverManager.getConnection("jdbc:postgresql://babar.db.elephantsql.com:5432/umbuxruw", "umbuxruw", "bJz2Gl4zb5ljx_tA9Wumm-HIIyPcDjUn");
        Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            String req = "delete from kilometrage where id ="+idv;
            sta.executeUpdate(req);
        } catch (Exception e) {
            throw e;
        } finally {
            if (sta != null)
                sta.close();
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDebutkm() {
        return debutkm;
    }

    public void setDebutkm(int debutkm) {
        this.debutkm = debutkm;
    }

    public int getFinkm() {
        return finkm;
    }

    public void setFinkm(int finkm) {
        this.finkm = finkm;
    }

    public String getDatekm() {
        return datekm;
    }

    public void setDatekm(String datekm) {
        this.datekm = datekm;
    }

    public float getTotalkm() {
        return totalkm;
    }

    public void setTotalkm(float totalkm) {
        this.totalkm = totalkm;
    }
}
