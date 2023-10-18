/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

/**
 *
 * @author Nacho
 */
import java.sql.DriverManager;
import java.sql.Connection;
import javax.swing.JOptionPane;
/**
 *
 * @author MASIS
 */
public class Conexion {
    
    Connection cn = null;
    
    public Connection Conectar(){
        
        try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-AODFD76:1433;databaseName=GLOBAL_WAY;user=sa;password=123;");
            
             //JOptionPane.showMessageDialog(null, "Conexion Realizada con Exito a la base de Datos");
        }catch (Exception e){
             JOptionPane.showMessageDialog(null, "Error En : "+ e);
        }
        return cn;
    }
    
    
}
