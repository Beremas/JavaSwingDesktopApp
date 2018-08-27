package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class ConnectionController {
    private static Connection con;
    private static String class_for_name ="oracle.jdbc.driver.OracleDriver";
    private static String username_database="MARCO";
    private static String password_database="lollol1010";
    private static String path_database="jdbc:oracle:thin:@localhost:1521:XE";
                 
    /**
     * getConnection.
     * Questo metodo si occupa di creare la connessione con il database sottostante, in base ai parametri sopra 
     * immessi.
     * Non è stata prevista una gestione della scelta dei parametri di connessione
     * ma l'intera applicazione è indipendente dal database sottostante. E' possibile scegliere un qualsiasi database relazionale
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        
         if( con == null){
            try{
                Class.forName(class_for_name);
                con = DriverManager.getConnection(path_database,username_database,password_database);            
           
             }catch(ClassNotFoundException e){
                Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, e);
             }
             return con;
         }else 
             return con;
     }

    /**
     * closeConnection
     * Questo metodo si occupa di chiudere la socket col database
     * @param conn
     */
    public static void closeConnection(Connection conn){
            
            try {
                 if( conn != null){ 
                    conn.close();
                    con = null;
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
         
    
      
    
}  

