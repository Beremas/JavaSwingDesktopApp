package Model;

import Database.ConnectionController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Date;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Transient;

/**
 * Model degli eventi presenti nel db
 * @author 
 */
public class Events extends Observable implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    private BigDecimal id;
    private String name;
    private String place;
    private Double price;
    private Date day;
    private BigInteger selledtickets;

    /**
     * Costruttore vuoto
     */
    public Events() { }
    /**
     * Costruttore con id
     * @param id
     */
    public Events(BigDecimal id) {
        this.id = id;
    }

    /**
     * Getter
     * @return id
     */
    public BigDecimal getId() {
        return id;
    }
    /**
     * Setter
     * @param id 
     */
    public void setId(BigDecimal id) {
        BigDecimal oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }
    /**
     * Getter
     * @return nome evento
     */
    public String getName() {
        return name;
    }
    /**
     * Setter
     * @param name nome evento
     */
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }
    /**
     * Getter
     * @return luogo evento
     */
    public String getPlace() {
        return place;
    }
    /**
     * Setter
     * @param place = luogo dell'evento
     */
    public void setPlace(String place) {
        String oldPlace = this.place;
        this.place = place;
        changeSupport.firePropertyChange("place", oldPlace, place);
    }
    /**
     * Getter
     * @return prezzo dell'evento
     */
    public Double getPrice() {
        return price;
    }
    /**
     * Setter
     * @param price = prezzo dell'evento
     */
    public void setPrice(Double price) {
        Double oldPrice = this.price;
        this.price = price;
        changeSupport.firePropertyChange("price", oldPrice, price);
    }
    /**
     * Getter
     * @return giorno dell'evento
     */
    public Date getDay() {
        return day;
    }
    /**
     * Setter 
     * @param day = giorno dell'evento
     */
    public void setDay(Date day) {
        Date oldDay = this.day;
        this.day = day;
        changeSupport.firePropertyChange("day", oldDay, day);
    }
    /**
     * Getter 
     * @return numero biglietti evento venduto
     */
    public BigInteger getSelledtickets() {
        return selledtickets;
    }
    /**
     * Setter
     * @param selledtickets = numero biglietti evento venduti
     */
    public void setSelledtickets(BigInteger selledtickets) {
        BigInteger oldSelledtickets = this.selledtickets;
        this.selledtickets = selledtickets;
        changeSupport.firePropertyChange("selledtickets", oldSelledtickets, selledtickets);
    }

    /**
     * hashCode.
     * Funzione Hash generatrice di un intero hash basato sul codice fiscale
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "View.Events[ id=" + id + " ]";
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
     * cursor From All Events
     * Questo metodo ritorna il cursore sulla tabella Eventi di tutte le righe.
     * Utilizzato dal controllore per far riempire la JtabellaEventi alla finestra.
     * @param con = socket per la connessione al Database.
     * @return ResultSet della tabella Eventi.
     */
    public ResultSet cursorFromAllEvents(Connection con){
        ResultSet rs;
        try {       
         
          // the mysql insert statement
          String query="select name,place,price,to_char(day,'dd-mon-yyyy')as day,selledtickets from events";
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
     * get Events Name
     * Al termine della query vengono notificati gli elementi che osservano questa classe, di avvenuta modifica al db.
     * @param con = socket per la connessione al Database.
     * @return ResultSet = cursorse su insieme dei nomi degli eventi presenti nel DB (non ripetuti).
     * 
     * 
     */
    public ResultSet getEventsName(Connection con){
        ResultSet rs;
        try {          
          // the mysql insert statement
          String query = "select unique name from events";
          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query);
          // execute the preparedstatement
          rs=preparedStmt.executeQuery();  
          return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     /**
     * get Events Place
     * Al termine della query vengono notificati gli elementi che osservano questa classe, di avvenuta modifica al db.
     * @param con = socket per la connessione al Database.
     * @return ResultSet = cursorse su insieme dei nomi degli luoghi presenti nel DB (non ripetuti).
     */
    public ResultSet getEventsPlace(Connection con){
        ResultSet rs;
        try {          
          // the mysql insert statement
          String query = "select place from events";
          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query);
          // execute the preparedstatement
          rs=preparedStmt.executeQuery();  
          return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
   
    /**
     * Update event
     * Al termine della query vengono notificati gli elementi che osservano questa classe, di avvenuta modifica al db.
     * @param con = socket per la connessione al Database.
     * @param nome = Nome dell'evento selezionato
     * @param luogo = Luogo dell'evento selezionato
     * @param data = Data di svolgimento dell'evento selezionato
     * @param prezzo = prezzo dell'evento selezionato
     
     * @param oldnome
     * @param selledT = biglietti venduti dell'evento selezionato
     * @param oldluogo
     * @param oldgiorno 
     
     * @return 1 se la modifica è andata a buon fine.
     *        -1 in caso contrario.
     */
    public int modifyEvents(Connection con,String nome,String luogo,String data,float prezzo,int selledT,String oldnome, String oldluogo, String oldgiorno){
        try {
          
            
          String query_update = "update events set name=?,place=?,price=?,day=?,selledtickets=? where name=? and place=? and day=?";
          
          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query_update);
      
          preparedStmt.setString (1, nome);    
          preparedStmt.setString (2, luogo); 
          preparedStmt.setFloat (3, prezzo); 
          preparedStmt.setString (4, data); 
          preparedStmt.setInt (5, selledT); 
          preparedStmt.setString (6, oldnome); 
          preparedStmt.setString (7, oldluogo); 
          preparedStmt.setString (8, oldgiorno); 
          
          
          
          // execute the preparedstatement
          preparedStmt.executeUpdate();  
         
         setChanged();
         notifyObservers();
         return 1;
       } catch (SQLException ex) {
            //Logger.getLogger(EventsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
       }      
       
       
    }
    
    /** 
     * Insert new event.
     * Al termine della query vengono notificati gli elementi che osservano questa classe, di avvenuta modifica al db.
     * @param con = socket per la connessione al Database.
     * @param nome = Nome dell'evento inserito
     * @param luogo = Luogo dell'evento inserito
     * @param data = Data di svolgimento dell'evento inserito
     * @param prezzo = prezzo dell'evento inserito
     * @param selledT = biglietti venduti dell'evento inserito
     * @return 1 se l'inserimento è andato a buon fine.
     *        -1 se è stato violato un vincolo di integrità (primarykey o unique)
     *        -2 se è stato catturato una eccezione SQL
     */
    public int insertEvents(Connection con,String nome,String luogo, String data,Float prezzo,int selledT){
        try {
       
          // the mysql insert statement
          String query = "insert into EVENTS values(?,?,?,?,?)";

          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query);
      
          preparedStmt.setString (1, nome);    
          preparedStmt.setString (2, luogo); 
          preparedStmt.setFloat (3, prezzo); 
          preparedStmt.setString (4, data);
          preparedStmt.setInt (5, selledT); 
        
          // execute the preparedstatement
          preparedStmt.execute();
         
          setChanged();
          notifyObservers();
          return 1;
       } catch (SQLIntegrityConstraintViolationException ex){           
           return -1;
       } catch (SQLException ex) { 
           Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
           return -2;
       }    
    
    }
    
    /**
     * Delete event.
     * @param con 
     * @param nome 
     * @param luogo 
     * @param giorno 
     */
    public void deleteEvents(Connection con,String nome, String luogo, String giorno){
        try {
          
          // the mysql insert statement
          String query = "delete from events where name=? and place=? and day=?";

          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = con.prepareStatement(query);
      
          preparedStmt.setString (1, nome);    
          preparedStmt.setString (2, luogo); 
          preparedStmt.setString (3, giorno); 
       
          // execute the preparedstatement
          preparedStmt.execute();  
         
         
          
       } catch (SQLException ex) {
               
         
       }      
       setChanged();
       notifyObservers();
    }

                                     



//STATISTICHE    
    
    
    /**
     * Query 1.
     * @param dayfrom = Tempo selezionato di inizio.
     * @param dayto = Tempo selezionato di fine.
     * @return ResultSet contenente l'insieme delle coppie (nomi evento e  guadagno (biglietti venduti * prezzo ) ) di tutti gli eventi presenti nel DB, 
     * nell'arco temporale selezionato.
     */
    public ResultSet query1(String dayfrom, String dayto){
    
        try {
            Statement stmt;
            ResultSet rset;
            
            // create the statement and execute the query
            Connection con = ConnectionController.getConnection();
            stmt = con.createStatement();
            rset = stmt.executeQuery("select name as NAME,SUM(price)*SUM(selledtickets) as PRICE from events where DAY between '"+dayfrom+"' and '"+dayto+"' group by name");
                  
            
            return rset;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
    /**
     * Query 2.
     * @param dayfrom = Tempo selezionato di inizio.
     * @param dayto = Tempo selezionato di fine.
     * @return ResultSet contenente l'insieme delle coppie(luogo, numero eventi ) di tutti i luoghi presenti nel DB,
     * nell'arco temporale selezionato.
     */
    public ResultSet query2(String dayfrom, String dayto){
        try {
            Statement stmt;
            ResultSet rset;
            
            // create the statement and execute the query
            Connection con = ConnectionController.getConnection();
            stmt = con.createStatement();
            rset = stmt.executeQuery("select place as PLACE, COUNT(name)as NAME from events where DAY between '"+dayfrom+"' and '"+dayto+"' group by place");
                  
            
            return rset;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Query 3.
     * @param dayfrom = Tempo selezionato di inizio.
     * @param dayto = Tempo selezionato di fine.
     * @param eventsName = nome dell'evento 
     * @return ResultSet contenente l'insieme delle coppie (luogo , guadatno (prezzo*biglietti venduti)) dell'evento selezionato
     * compreso tra le due date passate.
     */
    public ResultSet query3(String dayfrom, String dayto, String eventsName){
     try {
            Statement stmt;
            ResultSet rset;
            
            // create the statement and execute the query
            Connection con = ConnectionController.getConnection();
            stmt = con.createStatement();
            rset = stmt.executeQuery("select place as PLACE, SUM(price)*SUM(selledtickets) as PRICE from events where (DAY between '"+dayfrom+"' and '"+dayto+"') and ( name = '"+eventsName+"') group by place");    
            return rset;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
    /**
     * Query 4.
     * @param dayfrom = Tempo selezionato di inizio.
     * @param dayto = Tempo selezionato di fine.
     * @param eventsPlace = Luogo dell'evento
     * @return ResultSet contenente l'insieme delle coppie (Evento, quanti eventi ) per un dato luogo selezionat, nelle date 
     * selezionate.
     */
    public ResultSet query4(String dayfrom, String dayto, String eventsPlace){
        try {
            Statement stmt;
            ResultSet rset;
            
            // create the statement and execute the query
            Connection con = ConnectionController.getConnection();
            stmt = con.createStatement();
            rset = stmt.executeQuery("select NAME, COUNT(NAME)NEventi from events where (DAY between '"+dayfrom+"' and '"+dayto+"') and ( place = '"+eventsPlace+"') group by NAME ");    
            return rset;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
