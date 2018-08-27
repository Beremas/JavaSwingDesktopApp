package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import javax.persistence.Transient;

/**
 * Model dei clienti registrati usanti l'app web.
 * @author 
 */
public class Clients extends Observable implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    private String nome;
    private String cognome;   
    private String cf;

    /**
     * Costruttore vuoto
     */
    public Clients() {
    }

    /**
     * Costruttore con CodiceFiscale
     * @param cf
     */
    public Clients(String cf) {
        this.cf = cf;
    }

    /**
     * Getter
     * @return Nome del cliente registrato 
     */
    public String getNome() {
        return nome;
    }
    /**
     * Setter 
     * @param nome Nome del cliente
     */
    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }
    /**
     * Getter 
     * @return cognome del cliente
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * Setter
     * @param cognome = cognome del cliente
     */
    public void setCognome(String cognome) {
        String oldCognome = this.cognome;
        this.cognome = cognome;
        changeSupport.firePropertyChange("cognome", oldCognome, cognome);
    }
    /**
     * Getter
     * @return il codice fiscale del cliente
     */
    public String getCf() {
        return cf;
    }
    /**
     * Setter
     * @param cf = codice fiscale del cliente
     */
    public void setCf(String cf) {
        String oldCf = this.cf;
        this.cf = cf;
        changeSupport.firePropertyChange("cf", oldCf, cf);
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
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
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
        return "View.Clients[ cf=" + cf + " ]";
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
     * cursorFromAllClients.
     * 
     * @param con = socket per la connessione al Database.
     * @return 
     */
    public ResultSet cursorFromAllClients(Connection con){
        ResultSet rs;
        try {          
          // the mysql insert statement
          String query = "select * from clients";
          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query);
          // execute the preparedstatement
          rs=preparedStmt.executeQuery();  
          return rs;
        } catch (SQLException ex) {
            
            return null;
        }
    
    }
    
    /**
     * Delete client from DB
     * @param con = socket per la connessione al Database.
     * @param cf  = CodiceFiscale del cliente. Corrispone alla chiave primaria nella tabella CLIENTS contenente i clienti
     * 
     */
    public void deleteClient(Connection con,String cf){
        try {
          
          // the mysql insert statement
          String query = "delete from clients where cf=?";

          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query);
      
          preparedStmt.setString (1, cf);        
       
          // execute the preparedstatement
          preparedStmt.execute();  
         
         
          
       } catch (SQLException ex) {
               
         
       }      
       setChanged();
       notifyObservers();
    }
    
    
}
