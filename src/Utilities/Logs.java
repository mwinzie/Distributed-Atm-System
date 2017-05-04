
package Utilities;

import java.util.Properties;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Prime
 */
public class Logs {
  
    public String getinfo(String details) {
String FetchedDetails="";
        Properties properties = new Properties();
        try {
                          
          final String dir = System.getProperty("user.dir");             
          String path = dir + "\\database\\atm.properties";
             properties.load(new FileInputStream(path));
             FetchedDetails = properties.getProperty(details);           
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return FetchedDetails;

    }

    
        public String getbalance(String details) {
String FetchedDetails="";
        Properties properties = new Properties();
        try {
                          
          final String dir = System.getProperty("user.dir");             
          String path = dir + "\\database\\balance.txt";
             properties.load(new FileInputStream(path));
             FetchedDetails = properties.getProperty(details);           
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return FetchedDetails;

    }
    

            public  boolean CheckPin(String mypin) throws IOException 
    {
        boolean exists = false;
        //String result = "";
String pinexists = getinfo("pin");
        if (pinexists.equalsIgnoreCase(mypin)) 
        {
exists = true;
        }
        return exists;
    } 
    
       
            
              public void updatebalance(double newbalance){

                  final String directory = System.getProperty("user.dir");             
          String path = directory + "\\database\\balance.txt";

          File dir = new File(path);
        BufferedWriter writer = null;
        if (dir.exists()) {         
        } else {
            dir.mkdirs();
        }
        
                try {                    
            writer = new BufferedWriter(new FileWriter(dir));
            writer.write("balance" + "=" + newbalance);
        } catch (Exception e) {
            //log("Logs: " + e.getMessage(),"","");
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
                 //log("Logs: " + e.getMessage(),"","");
            }
        }
    }
     
            
            
            
}


