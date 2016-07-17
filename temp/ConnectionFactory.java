package br.med.nader.UTILS;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
  
  
 public class ConnectionFactory {  
    public Connection getConnection() {  
        try {  
            return DriverManager.getConnection("jdbc:mysql://localhost/atmedico","root","teste");  
        }  
        catch(SQLException e) {  
            throw new RuntimeException(e);  
        }  
    }  
}  

