package Controller;

import Model.Users;
import Model.Clients;
import View.*;

import Database.ConnectionController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controllore per la gestione dei clienti*
 * @author 
 */
public class ClientsController implements GenericController{
    private final ClientsView cv;
    private final Clients clientModel;

    /**
     * Costruttore con Model dei clienti e una JFrame
     * @param cv = finestra per la gestione dei clienti
     * @param clientModel = model per la gestione dei clienti
     */
    public ClientsController(ClientsView cv, Clients clientModel) {
        this.cv = cv;
        this.clientModel = clientModel;        
        cv.AddControllerListener((GenericController)this);
    }
  
    /** Back.
     * Questo metodo è richiamato dal bottone 'Back' della finestra 'ClientView'.
     * Ha il semplice scopo di ritornare alla finestra "Chiamate", ovvero quella di 'AdminHomeAreaView',
     * annullando l'operazione corrente.
     */
    @Override
    public void bottoneBackPremuto(){
      
      String AdminName = cv.getAdminName();
    
        
      Users ad=new Users();
      AdminHomeAreaView ahw = new AdminHomeAreaView(AdminName);
      LogController lc=new LogController(ahw,ad);      
       
      cv.setVisible(false);
      cv.dispose();
      
      ahw.initAdminHomeAreaView();
    }
    
    /** Delete client.
     * Questo metodo è richiamato dal bottone 'Delete client' della finestra 'ClientView'.
     * La logica di questo metodo è la seguente; ovvero quello di cancellare il cliente selezionato 
     * dall'admin.
     * Questo metodo tramite l'apposito getter della classe 'ClientsView' preleva il cf del cliente che corrisponde
     * alla primarykey nel database.
     * 
     * Effettua un controllo sulla presenza effettiva del cf, perché in caso contrario viene inviato alla finestra
     * 'ClientsView' un messaggio di errore. Prima di poter cancellare qualcosa, va prima selezionato!.
     * 
     * In caso positivo, viene prelevata la connessione dalla classe 'ConnectionController' con l'apposito metodo
     * getConnection() e successivamente inoltrato i parametri ottenuti al metodo del Model UserDAO tramite il suo metodo
     * deleteClient che cancellerà il cliente.
     */
    @Override
    public void bottoneDeleteClientPremuto(){
        try {   
            String cf = cv.getCf();
            if (cf==null){
                cv.displayErrorDialog("Click something before!");
            }else{
                Connection con = ConnectionController.getConnection();
                clientModel.deleteClient(con,cf);

                cv.displayMessagDialog("Client deleted succesfully!");
                
                cv.setCf(null);
            }
        } catch (SQLException | ClassNotFoundException ex) {
             cv.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
        } 
        
    
    }
    
    /** Logout.
     * Questo metodo è richiamato dal bottone 'logout' della finestra 'ClientsView'.
     * Ha il semplice scopo di ritornare alla finestra 'LoginView',
     * @param cv = finestra di tipo ClientsView.
     */
    @Override
    public void bottoneLogoutPremuto(ClientsView cv){
        Users userModel = new Users();
        LoginView lw = new LoginView();
        new LogController(lw,userModel);
        
        cv.setVisible(false);
        cv.dispose();
        
        lw.initLoginView();
    }

    /**
     * getCursorFromAllClients
     * Questo metodo permette di ritornare un insieme contenente tutte le righe della tabella clienti
     * utilizzata per riempire la tabella
     * @return ResultSet di clienti
     */
    @Override
    public ResultSet getCursorFromAllClients() {
         try {
              
            Connection con = ConnectionController.getConnection();            
            return clientModel.cursorFromAllClients(con);
            
        } catch (SQLException | ClassNotFoundException ex) {
             cv.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
            return null;
        }
    }
    
    /**
     *
     */
    @Override
    public void bottoneLoginPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneSignUpPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneSignUpPremuto2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneClientsManagerPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneEventsMangerPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param ahav
     */
    @Override
    public void bottoneLogoutPremuto(AdminHomeAreaView ahav) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param ev
     */
    @Override
    public void bottoneLogoutPremuto(EventsView ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneDeleteEventsPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneInsertEventPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneModifyEventPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneGenerateChartPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param dayFrom
     * @param dayTo
     * @return
     */
    @Override
    public ResultSet bottoneQuery1Premuto(String dayFrom, String dayTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param dayFrom
     * @param dayTo
     * @return
     */
    @Override
    public ResultSet bottoneQuery2Premuto(String dayFrom, String dayTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param dayFrom
     * @param dayTo
     * @param eventsName
     * @return
     */
    @Override
    public ResultSet bottoneQuery3Premuto(String dayFrom, String dayTo, String eventsName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param dayFrom
     * @param dayTo
     * @param eventsPlace
     * @return
     */
    @Override
    public ResultSet bottoneQuery4Premuto(String dayFrom, String dayTo, String eventsPlace) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottonBackPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public ResultSet getCursorFromAllEvents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
