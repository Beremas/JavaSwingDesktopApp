package Controller;

import Model.Events;
import Model.Clients;
import Model.Users;
import View.*;
import Database.ConnectionController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controllore per la gestione di login e logout di un admin
 * @author 
 */
public class LogController implements GenericController{
    private LoginView lw;
    private SignUpView suw;
    private AdminHomeAreaView ahaw;  
    private Users userModel;
 
    /**
     * Costruttore con il model degli admin e una JFrame
     * @param ahaw = Finestra successiva al login. La homePage dell'admin
     * @param userModel = Model per la gestione degli admin 
     */
    public LogController(AdminHomeAreaView ahaw, Users userModel) {
         this.lw=null;
         this.suw=null;
         this.ahaw = ahaw;
         this.userModel = userModel;         
         this.ahaw.AddControllerListener((GenericController)this); // Pass controller to view here.
    }
    /**
     * Costruttore con il model degli admin e una JFrame
     * @param lw = Finestra princiaple, quella dove si effettua il login
     * @param userModel =  Model per la gestione degli admin 
     */
    public LogController(LoginView lw, Users userModel){
         this.lw = lw;
         this.suw = null;
         this.ahaw = null;
         this.userModel = userModel;     
         this.lw.AddControllerListener((GenericController)this); // Pass controller to view here.
     }
    /**
     * Costruttore con il model degli admin e una JFrame
     * @param suw = finestra per la registrazione di un nuovo admin
     * @param userModel =  Model per la gestione degli admin 
     */
    public LogController(SignUpView suw, Users userModel){
         this.lw = null; 
         this.suw = suw; 
         this.ahaw = null;
         this.userModel = userModel;           
         this.suw.AddControllerListener((GenericController)this); // Pass controller to view here.
    }
  
    
    /** Login.
     * Questo metodo è richiamato dal bottone 'login' della finestra 'LoginView'. 
     * La logica del metodo è la seguente: Prelevare gli attributi username e password
     * dalla finestra 'LoginView' con i relativi getter, prelevare la connessione al database 
     * col metodo 'getConnection' della classe 'ConnectionController' dopodiché richiamare
     * il metodo 'validateLogin' della classe Model 'AdminDAO' che effettuerà la query al 
     * database per validare i parametri.
     * 
     * Se il validateLogin va a buon fine, allora verrà passato un messaggio di conferma
     * al metodo 'displayMessagDialog' della classe 'LoginView' per confermare la validazione, e verrà
     * creata l'istanza della nuova finestra 'AdminHomeAreaView'.
     * 
     * In caso negativo verrà passato un messaggio di errore al metodo 'displayMessagDialog' della classe 'LoginView' 
     * per notificare l'insuccesso dell'operazione.
     */
    @Override
    public void bottoneLoginPremuto(){
          String admin = lw.getUsername();
          String psw =  lw.getPassword();
          try {                  
                     
                Connection con = ConnectionController.getConnection();

                if(userModel.validateLogin(con, admin, psw)){
                    //lw.displayMessagDialog("Credential valid!"); 
                    lw.setVisible(false);
                    lw.dispose();


                    ahaw = new AdminHomeAreaView(admin);
                    ahaw.setVisible(true);
                    ahaw.AddControllerListener(this); 
                                
                
                }else
                    lw.displayErrorDialog("Credential not valid! Please try again!");
     
            } catch (SQLException | ClassNotFoundException ex) {
                  lw.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
            }
    }
    
    /** SignUp richiesta finestra.
     * Questo metodo è richiamato dal bottone 'signup' della finestra 'LoginView'.
     * La logica è semplicissima ovvero richiedere la nuova finestra per l'immissione dei campi.
     * La nuova finestra è: SignUpView
     */
    @Override
    public void bottoneSignUpPremuto(){
        lw.setVisible(false);
        lw.dispose();
        
        suw = new SignUpView(userModel);
        suw.AddControllerListener(this); 
        suw.initSignUpView();
    }
    
    /** SignUp.
     * Questo metodo è richiamato dal bottone 'SignUp' della finestra 'SignUpView'. 
     * La logica del metodo è la seguente: Prelevare gli attributi username,password,cf,nome,cognome
     * dalla finestra 'SignUpView' con i relativi getter, prelevare la connessione al database 
     * col metodo 'getConnection' della classe 'ConnectionController' dopodiché richiamare
     * il metodo 'insertNewAdmin' della Model 'AdminDAO' che effettuerà la query al 
     * database per inserire una nuova riga nel database.
     * 
     * Se il insertNewAdmin va a buon fine, allora verrà passato un messaggio di conferma
     * al metodo 'displayMessagDialog' della classe 'SignUpView' per confermare l'inserimento, e verrà
     * creata l'istanza della nuova finestra 'LoginView'.
     * 
     * In caso negativo verrà passato un messaggio di errore al metodo 'displayMessagDialog' della classe 'SignUpView' 
     * per notificare l'insuccesso dell'operazione.
     */
    @Override
    public void bottoneSignUpPremuto2(){
          String user = suw.getUsername();
          String psw =  suw.getPassword();
          String cf = suw.getCF();
          String nome = suw.getNome();
          String cogno = suw.getCognome();
          
           
          if(16 != cf.length()){
                suw.displayErrorDialog("The CF field must be lenght 16 charcter!");
          }else{            
                try {              

                      Connection con = ConnectionController.getConnection();

                      int val=userModel.insertNewAdmin(con, nome, cogno, cf, user, psw);            
                      switch (val) {
                        case 1:
                            suw.displayMessagDialog("Admin correctly signed!");
                            break;
                        case -1:
                            suw.displayErrorDialog("Username or CF already exsist.");
                            break;
                        case -2:
                            suw.displayErrorDialog("Something went wrong. SQL Statement exception catched!");
                            break;
                        default:
                            break;
                      }
                      suw.setVisible(false);
                      suw.dispose();

                      lw = new LoginView();
                      lw.setVisible(true);
                      lw.AddControllerListener(this); 


                  } catch (SQLException | ClassNotFoundException ex) {
                     lw.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
                  }
          }
    }
   
    /** Client Manager richiesta finestra.
     * Questo metodo è richiamato dal bottone 'Clients manager' della finestra 'AdminHomeAreaView'.
     * La logica di questo bottone è quello di richiedere la nuova finestra con tutta la gestione dei clienti.
     * La nuova finestra è la seguente: ClientsView.
     */
    @Override
    public void bottoneClientsManagerPremuto(){
        
        String AdminName = ahaw.getAdminName();
        
        Clients clientModel = new Clients();
        ClientsView cw = new ClientsView(clientModel,AdminName);
        new ClientsController(cw,clientModel);
        
        ahaw.setVisible(false);
        ahaw.dispose();
        
        cw.fillClientTable();
        cw.initClientsView();
    }
    
    /** Event Manager richiesta finestra.
     * Questo metodo è richiamato dal bottone 'Events manager' della finestra 'AdminHomeAreaView'.
     * La logica di questo bottone è quello di richiedere la nuova finestra con tutta la gestione degli eventi.
     * La nuova finestra è la seguente: EventsView.
     */
    @Override
    public void bottoneEventsMangerPremuto(){
        
        String adminName = ahaw.getAdminName();
        
        Events ed = new Events();
        EventsView ev = new EventsView(ed,adminName);
        EventsController ec = new EventsController(ed,ev);
       
        
        ahaw.setVisible(false);
        ahaw.dispose();
        
        ev.fillEventTable();
        ev.initEventView();
    }
    
    /** Back.
     * Questo metodo è richiamato dal bottone 'Back' della finestra 'SignUpView'.
     * Ha il semplice scopo di ritornare alla finestra "Chiamate", ovvero quella di 'LoginView',
     * annullando l'operazione corrente.
     */
    @Override
    public void bottoneBackPremuto(){
        Users ad = new Users();
        lw = new LoginView();
        new LogController(lw,ad);
       
        
        suw.setVisible(false);
        suw.dispose();
        
        lw.initLoginView();
    }
    
    /** Logout.
     * Questo metodo è richiamato dal bottone 'logout' della finestra 'AdminHomeAreaView'.
     * Ha il semplice scopo di ritornare alla finestra 'LoginView',
     * @param ahav = finestra di tipo AdminHomeAreaView.
     */
    @Override
    public void bottoneLogoutPremuto(AdminHomeAreaView ahav){
        userModel = new Users();
        lw = new LoginView();
        new LogController(lw,userModel);
        
        ahav.setVisible(false);
        ahav.dispose();
        
        lw.initLoginView();
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
     * @param cv
     */
    @Override
    public void bottoneLogoutPremuto(ClientsView cv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void bottoneDeleteClientPremuto() {
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

    /**
     *
     * @return
     */
    @Override
    public ResultSet getCursorFromAllClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
  
    

}
