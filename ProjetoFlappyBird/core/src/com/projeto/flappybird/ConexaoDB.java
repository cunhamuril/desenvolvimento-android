package com.projeto.flappybird;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Handler;

import javax.naming.Context;

import sun.rmi.runtime.Log;

public class ConexaoDB implements ActionResolver {
    Handler uiThread;
    Context appContext;

    public ConexaoDB(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
    }

    @Override
    public Connection getConnection() {
        String url = "jdbc:sqlite:data.sqlite";
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public Connection getConnection() {
        String url = "jdbc:sqldroid:/data/data/my.app.name/databases/data.sqlite";
        try {
            Class.forName("org.sqldroid.SQLDroidDriver").newInstance();
            return DriverManager.getConnection(url);
        } catch (InstantiationException e) {
            Log.e("sql", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e("sql", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("sql", e.getMessage());
        } catch (SQLException e) {
            Log.e("sql", e.getMessage());
        }
        return null;
    }

}
