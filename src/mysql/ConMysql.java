/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class ConMysql {

    public static Connection conn = null;                   //inicia a conexão

    public  String connURL = null;                    // define a url
    public  String userName = null;
    public  String password = null;
    public  String dbName = null;

    public ResultSet rs = null;     // guardar informação da consulta feita no banco de dados
    public Statement stmt = null;

    public ConMysql() {
        connURL = "jdbc:mysql://localhost:3306/";     // "jdbc" é o driver mysql corresponde ao banco de dados  e lh é a localização do bd 3306 é a porta padrão.
        userName = "root";
        password = "fatec";
        dbName = "bd_fatec";
    }

    public ConMysql(String url, String port, String user, String password, String dbName) {
        this.connURL = "jdbc:mysql://" + url + ":" + port + "/";
        this.userName = user;
        this.password = password;
        this.dbName = dbName;
    }

    public void abreConexao() {
                   try {
                Class.forName("com.Mysql.jdbc.Driver");//registro de classe externa
                conn = DriverManager.getConnection(this.connURL + this.dbName, this.userName, this.password);
                } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConMysql.class.getName()).log(Level.SEVERE, null, ex);
            }
                  
        }
  
    
    

    public boolean execSql(String sql) {
    try {
        conn.setAutoCommit(false);
        stmt = conn.prepareStatement(sql);
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        conn.commit();
        conn.setAutoCommit(true);
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ConMysql.class.getName()).log(Level.SEVERE, null, ex);
    }
return false;
}

    public void selectSql(String sql) {
rs = null;
        try {
            conn.createStatement();
             rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void fechaConexao() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConMysql.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
