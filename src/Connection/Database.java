/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utilities.Logs;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Prime
 */
public class Database {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultset = null;
     Logs logs = new Logs();
        public Database() throws ClassNotFoundException, SQLException {
        connection = getConnection();
    }

    public Connection getConnection(){
        
          final String dir = System.getProperty("user.dir");      
         String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        String Dbdetails = dir + "\\database//bella.mdb";
       try {
             Class.forName(driver);

Connection connx = DriverManager.getConnection("jdbc:ucanaccess://" + Dbdetails + "");

      connection = connx;
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public int insert(String query){
        try {
            int result = 0;
            statement = connection.createStatement();
            result = statement.executeUpdate(query);
            
            return result;
        } catch (SQLException ex) {
            // logs.log("Database ~ insert: " + ex, "", "4");
//            MyLogger.log("Radiuslogs/Database", "Database - insert() " + ex.getMessage(), "exceptions.log");
            return 0;
        } finally {
            closeConnection();
        }
    }
    
    public ResultSet select(String query){
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            return resultset;
        } catch (SQLException ex) {
            //logs.log("Database ~ select: " + ex, "", "4");
            return null;
        } finally {
            //closeConnection();
        }
    }
    
    public int count(String query){
        try {
            int result = 0;
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            
            while(resultset.next()){
                result++;
            }
            
            return result;
        } catch (SQLException ex) {
         //   logs.log("Database ~ count: " + ex, "", "4");

            return 0;
        } finally {
            //closeConnection();
        }

    }
    
    public int update(String query){
        try {
            int result = 0;
            statement = connection.createStatement();
            result = statement.executeUpdate(query);
            
            return result;
        } catch (SQLException ex) {
           // logs.log("Database ~ update: " + ex, "", "4");

            return 0;
        } finally {
            closeConnection();
        }
    }
    
    public int delete(String query){
        try {
            int result = 0;
            statement = connection.createStatement();
            result = statement.executeUpdate(query);
            
            return result;
        } catch (SQLException ex) {
             //  logs.log("Database ~ delete: " + ex, "", "4");

            return 0;
        } finally {
            closeConnection();
        }
    }
    
    public void closeConnection(){
        try {

            if(statement != null){
                statement.close();
            }

            if(resultset != null){
                resultset.close();
            }     
            if(connection != null){
                connection.close();  
            }
        } catch (SQLException ex) {
           //  logs.log("Database ~ closeConnection: " + ex, "", "4");

        }
    }
    
}
