package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Transient;

/**
 * Model degli admin presenti nel db
 * @author 
 */
public class Users extends Observable implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    private String nome;
    private String cognome;
    private String cf;
    private String password; 
    private String username;

    /**
     * Costruttore vuoto
     */
    public Users() {
    }
    /**
     * Costruttore con codice fiscale
     * @param cf = codice fiscale admin
     */
    public Users(String cf) {
        this.cf = cf;
    }
    /**
     * Costruttore con codice fiscale nome e cognome admin
     * @param cf = codice fiscale admin
     * @param nome = nome admin 
     * @param cognome = cognome admin
     */
    public Users(String cf, String nome, String cognome) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
    }

    /**
     * Getter
     * @return nome dell'admin
     */
    public String getNome() {
        return nome;
    }
    /**
     * Setter
     * @param nome = nome dell admin
     */
    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }
    /**
     * Getter 
     * @return cognome admin
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * Setter
     * @param cognome = cognome admin
     */
    public void setCognome(String cognome) {
        String oldCognome = this.cognome;
        this.cognome = cognome;
        changeSupport.firePropertyChange("cognome", oldCognome, cognome);
    }
    /**
     * Getter
     * @return codice fiscale admin
     */
    public String getCf() {
        return cf;
    }
    /**
     * Setter
     * @param cf = codice fiscale admin
     */
    public void setCf(String cf) {
        String oldCf = this.cf;
        this.cf = cf;
        changeSupport.firePropertyChange("cf", oldCf, cf);
    }
    /**
     * Getter
     * @return password dell'admin
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setter
     * @param password = password scelta dall'admin
     */
    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }
    /**
     * Getter 
     * @return username scelto dall'admin
     */
    public String getUsername() {
        return username;
    }
    /**
     * Setter
     * @param username = username scelto dall'admin
     */
    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    /**
     * hashCode.
     * Funzione Hash generatrice di un intero hash basato sul codice fiscale
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cf != null ? cf.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.cf == null && other.cf != null) || (this.cf != null && !this.cf.equals(other.cf))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "View.Users[ cf=" + cf + " ]";
    }

    /**
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
   
   
    /**
     * getHowManyAdminAreSigned. //metodo usato per testing
     * Questo metodo ritorna il numero di Admin registrati al momento della chiamata.
     * @param con = socket per la connessione al Database.
     * @return count = numero degli admin presenti nel db
     */   
    public int getHowManyAdminAreSigned(Connection con){
         PreparedStatement preparedStmt;
         ResultSet rset;
         int count=0;
         try {   
            // the mysql insert statement
            String query = "select * from Users";
            // create the mysql insert preparedstatement
            preparedStmt = con.prepareStatement(query);
            // execute the preparedstatement
            rset=preparedStmt.executeQuery();  
                  
            while(rset.next()){
                count++;
            }
            return count;
         } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
         }
    }
    
    /**
    * Validate Users.
    * @param con = socket per la connessione al Database.
    * @param username = Useraname dell'admin.
    * @param password = Password dell'admin.
    * @return True se esiste un match tra i parametri e una riga nel DB. 
    *         Falso altrimenti.
    */
    public boolean validateLogin(Connection con, String username, String password){
        
            Statement stmt;
            ResultSet rset;
        try {   
            // create the statement and execute the query
            stmt = con.createStatement();
            rset = stmt.executeQuery("select * from USERS where USERNAME='"+username+"' and PASSWORD='"+password+"'");
                  
            return rset.next();
        } catch (SQLException ex) {
            //Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        catch (Exception ex) {
            //Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
    }
    
    /**
     * Insert new Users.
     * @param con = socket per la connessione al Database.
     * @param nome = nome immesso dal nuovo admin in atto di registrazione.
     * @param cognome = cognome immesso dal nuovo admin in atto di registrazione.
     * @param cf = codice fiscale immesso dal nuovo admin in atto di registrazione.
     * @param username = username immesso dal nuovo admin in atto di registrazione.
     * @param password = password immesso dal nuovo admin in atto di registrazione.
     * 
     * Si ricorca che gli unici attributi che devono essere validi ai fini di un 
     * corretto inserimento nel database sono: 
     *  Connection con, e String cf; 
     * I restanti parametri non sono necessari, (possono essere anche  null)
     * 
     * @return 1 se l'inserimento è andato a buon fine.
     *        -1 se è stato violato un vincolo di integrità ( primarykey, o unique).s
     *        -2 se cè stato un errore a livello SQL. 
     *        -3 un qualsiasi altro errore
     */
    public int insertNewAdmin(Connection con, String nome, String cognome, String cf, String username, String password){
         try {
          
            // the mysql insert statement
            String query = "insert into USERS values(?,?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString (1, nome);
            preparedStmt.setString (2, cognome);
            preparedStmt.setString (3, cf);
            preparedStmt.setString (4, password);
            preparedStmt.setString (5, username);


            // execute the preparedstatement
            preparedStmt.executeUpdate();  

         
            return 1;
       }catch (SQLIntegrityConstraintViolationException e){
           return -1;   
       }catch (SQLException ex) {
           return -2;
       }catch (Exception ex) {
           return -3;
       }
    }
    
    /**
     * deleteAdmin.  //metodo usato per testing
     * Questo metodo non fa altro che cancellare dalla tabella l'admin il quale cf corrisponde al cf passato come 
     * parametro al metodo stesso
     * @param con = socket per la connessione al Database.
     * @param cf = codice fiscale del admin 
     * @return 1 se il delete è avvenuto cn successo, -2 per un qualche errore SQL, -3 per tutt gli altri errori.
     */
    public int deleteAdmin(Connection con, String cf){
         try {
          
            // the mysql insert statement
            String query = "delete from USERS where cf ='"+cf+"'";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.executeQuery();

            return 1;
       }catch (SQLException ex) {
           return -2;
       }catch (Exception ex) {
           return -3;
       }
    }
}
