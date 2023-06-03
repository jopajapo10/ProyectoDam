/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author josepa
 */
public class Conexion {
    String usuario="root";
    String password="";
    String driver="com.mysql.cj.jdbc.Driver";
    Connection cn;

    public Connection getConexion() {
        try{
            String db = "jdbc:mysql://localhost/almacenprueba";
            cn = DriverManager.getConnection(db, usuario, password);
            return cn;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
    
    
    //nos desconecta
    public void desconectar(){
        cn=null;
        if(cn == null){
            System.out.println("Conexcion terminada.");
        }
    }
}
