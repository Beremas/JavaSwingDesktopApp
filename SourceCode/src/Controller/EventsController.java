package Controller;

import Model.Events;
import Model.Users;
import View.*;
import Database.ConnectionController;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/** 
 * Controllore per la gestione degli eventi.
 * @author 
 */
public class EventsController implements GenericController{
    private Events ed;
    private EventsView ew;
    private ChartsView cw;
    
    /**
     * Costruttore contenente una JFrame e il model degli eventi
     * @param ed = Model degli eventi
     * @param ew = Finestra contenente la tabelal degli eventi ed altre features
     */
    public EventsController(Events ed, EventsView ew){
        this.ed = ed;
        this.ew = ew;
        ew.AddControllerListener((GenericController)this);
    }
    
    
    /** Delete event.
     * Questo metodo è richiamato dal bottone 'Delete event' della finestra 'EventsView'.
     * La logica di questo metodo è la seguente; ovvero quello di cancellare l'evento selezionato 
     * dall'admin.
     * Questo metodo tramite l'apposito getter della classe 'EventsView' preleva l'id dell'evento che corrisponde
     * alla primarykey nel database.
     * 
     * Effettua un controllo sulla presenza effettiva dell'id, perché in caso contrario viene inviato alla finestra
     * 'EventsView' un messaggio di errore. Prima di poter cancellare qualcosa, va prima selezionato!.
     * 
     * In caso positivo, viene prelevata la connessione dalla classe 'ConnectionController' con l'apposito metodo
     * getConnection() e successivamente inoltrato i parametri ottenuti al metodo del Model EventsDAO tramite il suo metodo
     * deleteEvents che cancellerà l'evento passato dal database.
     */
    @Override
    public void bottoneDeleteEventsPremuto(){
         try {   
              String nome=ew.getNome();
              String luogo=ew.getLuogo();
              String giorno=ew.getGiorno();

              
            if (nome == null || luogo == null || giorno == null){
                ew.displayErrorDialog("Click on the event that you want to delete before!");
            }else{
                Connection con = ConnectionController.getConnection();            
                ed.deleteEvents(con, nome,luogo,giorno);            
                ew.displayMessagDialog("Event deleted succesfully!");

                
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
             ew.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
        } 
    }
    
    
    /** Insert new event.
     * Questo metodo è richiamato dal bottone 'Insert event' della finestra 'EventsView'.
     * La logica di questo metodo è la seguente; ovvero quello di inserire il nuovo evento creato
     * dall'admin.
     * Questo metodo tramite i getter della classe 'EventsView' preleva i parametri dell'evento.
     * 
     * Viene effettuato un primo check riguardo la effettiva immissione di alcuni campi. Non è possibile inserire eventi
     * vuoti all'interno del database.
     * In caso di mancanza di attributi verra mandato un messaggio di errore alla finestra EventsView.
     * 
     * Effettua un controllo sulla presenza effettiva dell'id, perché in caso contrario viene inviato alla finestra
     * 'EventsView' un messaggio di errore. Prima di poter cancellare qualcosa, va prima selezionato!.
     * 
     * Superato il primo check, viene prelevata la connessione dalla classe ConnectionController col metodo 
     * getConnection dopodichè verranno inoltrati i parametri prelevati precedentemente al Model EventsDAO al 
     * metodo insertEvents. (Per le politiche di errore, si legga il JavaDoc del metodo interessato).
     */
    @Override
    public void bottoneInsertEventPremuto() {
        
            String nome=ew.getNome();
            String luogo=ew.getLuogo();
            String giorno=ew.getGiorno();
            float prezzo=ew.getPrezzo();
            int selledTickets=ew.getSelledTickets();
         
         
                        
        
            if("".equals(nome)||"".equals(luogo)||"".equals(giorno)){
                    ew.displayErrorDialog("Fill all the fields before insert a new event!");
            }else{
                try {  
                    Connection con = ConnectionController.getConnection();
                    int val=ed.insertEvents(con, nome, luogo, giorno, prezzo,selledTickets);                    
                     switch (val) {
                          case 1:
                              ew.displayMessagDialog("Event correctly inserted!");
                              break;
                          case -1:
                              ew.displayErrorDialog("Something went wrong. Event's already exsist");
                              break;
                          case -2:
                              ew.displayErrorDialog("Something went wrong. SQL Statement exception catched!");
                              break;
                          default:
                              break;
                        }


                } catch (ClassNotFoundException | SQLException ex) {
                     ew.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
                } 
            }
            
    }
    
    
    /** Update event.
     * Questo metodo è richiamato dal bottone 'Update event' della finestra 'EventsView'.
     * La logica di questo metodo è la seguente; ovvero quello di aggiornare un evento modificato
     * dall'admin.
     * Questo metodo tramite i getter della classe 'EventsView' preleva i parametri dell'evento modificato.
     * 
     * Viene effettuato un primo check riguardo la effettiva immissione di alcuni campi. Non è possibile aggiornare eventi
     * vuoti all'interno del database.
     * In caso di mancanza di attributi verra mandato un messaggio di errore alla finestra 'EventsView'.
     * 
     * Superato il primo check, viene prelevata la connessione dalla classe ConnectionController col metodo 
     * getConnection dopodichè verranno inoltrati i parametri prelevati precedentemente al Model EventsDAO al 
     * metodo modifyEvents. (Per le politiche di errore, si legga il JavaDoc del metodo interessato).
     * 
     * In caso di corretto aggiornamento verrà mandato un messaggio alla finestra 'EventsView' con la notifica di 
     * corretta operazione.
     */
    @Override
    public void bottoneModifyEventPremuto(){
       
        String nome=ew.getNome();
        String luogo=ew.getLuogo();
        String giorno=ew.getGiorno();
        float prezzo=ew.getPrezzo();
        int selledTickets=ew.getSelledTickets();
        
        String oldnome=ew.getOldNome();
        String oldluogo=ew.getOldLuogo();
        String oldgiorno=ew.getOldGiorno();
        
        if("".equals(nome)||"".equals(luogo)||"".equals(giorno)){
                    ew.displayErrorDialog("Fill all the fields before update an event!");
        }else{
            try {  
                Connection con = ConnectionController.getConnection();
                int val=ed.modifyEvents(con, nome, luogo, giorno, prezzo,selledTickets,oldnome,oldluogo,oldgiorno);
                 switch (val) {
                      case 1:
                          ew.displayMessagDialog("Event correctly updated!");
                          break;
                      case -1:
                          ew.displayErrorDialog("Something went wrong. This event already exsist!");
                          break;
                      case -2:
                          ew.displayErrorDialog("Something went wrong. SQL Statement exception catched!");
                          break;
                      default:
                          break;
                    }
                

            } catch (ClassNotFoundException | SQLException ex) {
                ew.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
            }
        }
    }
    
    
    /** Generate Statistic richiesta finestra delle statistiche con passaggio parametri per la costruzione delle sue ComboBox.
     * Questo metodo è richiamato dal bottone 'Create chart' dalal finestra 'EventsView'.
     * La logica di questo metodo è semplicemente di creare la finestra per la gestione delle 
     * statistiche e permettere così all'admin di poter interagire e scegliere la statistica desiderata.
     *
     * Viene prelevata la connessione dalla classe  ConnectionController con il metodo getConnection, questo perché
     * la nuova finestra conterrà dei comboBox contenente alcuni valori presi dal database.
     * Effettuate le query desiderate ed inserite in 2 cursori, verrano poi passati al costruttore della nuova finestra
     * (ovvero: ChartsView) il quale avrà il compito di fetchare i risultati e riempirsi le comboBox.
     * 
     */
    @Override
    public void bottoneGenerateChartPremuto(){
        try {
            
            Connection con = ConnectionController.getConnection();
            
            ResultSet RS_eventsname=ed.getEventsName(con);
            ResultSet RS_eventplace=ed.getEventsPlace(con);
            
            cw = new ChartsView(RS_eventsname,RS_eventplace);
            cw.AddControllerListener(this);
            cw.setVisible(true);
            
        } catch (SQLException | ClassNotFoundException ex) {
             cw.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
        } 
    }
    
    /** Back
     * Questo metodo è richiamato dal bottone 'Back' della finestra 'EventsView'.
     * Ha il semplice scopo di ritornare alla finestra "Chiamate", ovvero quella di 'AdminHomeAreaView',
     * annullando l'operazione corrente.
     */
    @Override
    public void bottonBackPremuto(){
        
        String AdminName = ew.getAdminName();
        
        Users ad = new Users();
        AdminHomeAreaView ahw = new AdminHomeAreaView(AdminName);
        LogController lc = new LogController(ahw,ad);
        
        ew.setVisible(false);
        ew.dispose();
        
        ahw.setVisible(true);
    }
    
    /** Logout.
     * Questo metodo è richiamato dal bottone 'logout' della finestra 'EventsView'.
     * Ha il semplice scopo di ritornare alla finestra 'LoginView',
     * @param ev = finestra di tipo EventsView.
     */
    @Override
    public void bottoneLogoutPremuto(EventsView ev){
        Users ad = new Users();
        LoginView lw = new LoginView();
        new LogController(lw,ad);
        
        ev.setVisible(false);
        ev.dispose();
        
        lw.initLoginView();
    }
    
    /**
     * getCursorFromAllEvents.
     * Questo metodo permette di ritornare un insieme contenente tutte le righe della tabella eventi
     * utilizzata per riempire la tabella
     * @return ResultSet di clienti
     */
    @Override
    public  ResultSet getCursorFromAllEvents(){
        try {
              
            Connection con = ConnectionController.getConnection();            
            return ed.cursorFromAllEvents(con);
            
        } catch (SQLException | ClassNotFoundException ex) {
             ew.displayErrorDialog("Ops! There are problems with the server. Please try leter or contact abc@gmail.com for support. Thanks");
            return null;
        } 

       
    }   
    //Eventi STATISTICHE
    
    /** Preparazione Query1.
     * Questo metodo viene richiamato dal bottone 'jButtonquery1' dalla finestra 'ChartsView'.
     * La logica di questo metodo è semplicemente quello di controllare se i parametri selezionati 
     * dall'admin all'atto della pressione del bottone, sono corretti: Ovvero
     *     1) Devono essere selezionati obbligatoriamente i giorni di inizio e fine.
     *     2) Il giorno di inizio deve venire prima del giorno di fine.
     * In caso di successo, verra richiamato il metodo del Model EventsDAO e cioè query1(...) la quale si occupera
     * di ritornare il cursorse sul set di dati ricavati.
     * @param dayFrom = Data iniziale scelta per la query.
     * @param dayTo = Data finale scelta per la query.
     * @return ResultSet (Per maggiori dettagli sul ritorno della query, controllare il javadoc del metodo desiderato)
     */
    @Override
    public ResultSet bottoneQuery1Premuto(String dayFrom, String dayTo){
            if("".equals(dayFrom)|| "".equals(dayTo)){
                cw.displayErrorDialog("Select a date before generate a chart");
                return null;
            }else{
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                    java.util.Date d1 = sdf.parse( dayFrom );
                    java.util.Date d2 = sdf.parse( dayTo );
                    if(d1.after(d2)){
                        cw.displayErrorDialog("Day 'from' cannot be greater then day 'to'");
                        return null;
                    }else{
                        ResultSet rs = ed.query1(dayFrom,dayTo);          
                        return rs;
                    }
                } catch (ParseException ex) {
                    cw.displayErrorDialog("A ParseEception was detected! ");
                    return null;
                }
            }
    }
    /** Preparazione Query2.
     * Questo metodo viene richiamato dal bottone 'jButtonquery2' dalla finestra 'ChartsView'.
     * La logica di questo metodo è semplicemente quello di controllare se i parametri selezionati 
     * dall'admin all'atto della pressione del bottone, sono corretti: Ovvero
     *     1) Devono essere selezionati obbligatoriamente i giorni di inizio e fine.
     *     2) Il giorno di inizio deve venire prima del giorno di fine.
     * In caso di successo, verra richiamato il metodo del Model EventsDAO e cioè query2(...) la quale si occupera
     * di ritornare il cursorse sul set di dati ricavati.
     * @param dayFrom = Data iniziale scelta per la query.
     * @param dayTo = Data finale scelta per la query.
     * @return ResultSet (Per maggiori dettagli sul ritorno della query, controllare il javadoc del metodo desiderato)
     */
    @Override
    public ResultSet bottoneQuery2Premuto(String dayFrom, String dayTo){
            if("".equals(dayFrom)|| "".equals(dayTo)){
                cw.displayErrorDialog("Select a date before generate a chart");
                return null;
            }else{
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                    java.util.Date d1 = sdf.parse( dayFrom );
                    java.util.Date d2 = sdf.parse( dayTo );
                    if(d1.after(d2)){
                        cw.displayErrorDialog("Day 'from' cannot be greater then day 'to'");
                        return null;
                    }else{
                        ResultSet rs = ed.query2(dayFrom,dayTo);
                        return rs;
                    }
                } catch (ParseException ex) {
                    cw.displayErrorDialog("A ParseEception was detected! ");
                    return null;
                }
            }
    }
    /** Preparazione Query3.
     * Questo metodo viene richiamato dal bottone 'jButtonquery3' dalla finestra 'ChartsView'.
     * La logica di questo metodo è semplicemente quello di controllare se i parametri selezionati 
     * dall'admin all'atto della pressione del bottone, sono corretti: Ovvero
     *    1) Devono essere selezionati obbligatoriamente i giorni di inizio e fine.
     *    2) Deve essere selezionato il nome dell'evento desiderato.
     *    3) Il giorno di inizio deve venire prima del giorno di fine.
     * In caso di successo, verra richiamato il metodo del Model EventsDAO e cioè query3(...) la quale si occupera
     * di ritornare il cursorse sul set di dati ricavati.
     * @param dayFrom = Data iniziale scelta per la query.
     * @param dayTo = Data finale scelta per la query.
     * @param eventsName = Nome dell'evento desiderato utilizzato per la query.
     * @return ResultSet (Per maggiori dettagli sul ritorno della query, controllare il javadoc del metodo desiderato)
     */
    @Override
    public ResultSet bottoneQuery3Premuto(String dayFrom, String dayTo, String eventsName){
            if("".equals(dayFrom)|| "".equals(dayTo)){
                cw.displayErrorDialog("Select a date before generate a chart");
                return null;
            }else if("Select an Event".equals(eventsName)){
                cw.displayErrorDialog("Select an event's name before generate a chart");
                return null;
            }else{
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                    java.util.Date d1 = sdf.parse( dayFrom );
                    java.util.Date d2 = sdf.parse( dayTo );
                    if(d1.after(d2)){
                        cw.displayErrorDialog("Day 'from' cannot be greater then day 'to'");
                        return null;
                    }else{
                        ResultSet rs = ed.query3(dayFrom,dayTo,eventsName);
                        return rs;
                    }
                } catch (ParseException ex) {
                    cw.displayErrorDialog("A ParseEception was detected! ");
                    return null;
                }
            }
    }
    /** Preparazione Query3.
     * Questo metodo viene richiamato dal bottone 'jButtonquery4' dalla finestra 'ChartsView'.
     * La logica di questo metodo è semplicemente quello di controllare se i parametri selezionati 
     * dall'admin all'atto della pressione del bottone, sono corretti: Ovvero
     *   1) Devono essere selezionati obbligatoriamente i giorni di inizio e fine.
     *    2) Deve essere selezionato il luogo desiderato.
     *    3) Il giorno di inizio deve venire prima del giorno di fine.
     * In caso di successo, verra richiamato il metodo del Model EventsDAO e cioè query4(...) la quale si occupera
     * di ritornare il cursorse sul set di dati ricavati.
     * @param dayFrom = Data iniziale scelta per la query.
     * @param dayTo = Data finale scelta per la query.
     * @param eventsPlace = Nome del luogo desiderato utilizzato per la query.
     * @return ResultSet (Per maggiori dettagli sul ritorno della query, controllare il javadoc del metodo desiderato)
     */
    @Override
    public ResultSet bottoneQuery4Premuto(String dayFrom, String dayTo, String eventsPlace){
            if("".equals(dayFrom)|| "".equals(dayTo)){
                cw.displayErrorDialog("Select a date before generate a chart");
                return null;
            }else if("Select a Place".equals(eventsPlace)){
                cw.displayErrorDialog("Select an event's place before generate a chart");
                return null;
            }else{
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                    java.util.Date d1 = sdf.parse( dayFrom );
                    java.util.Date d2 = sdf.parse( dayTo );
                    if(d1.after(d2)){
                        cw.displayErrorDialog("Day 'from' cannot be greater then day 'to'");
                        return null;
                    }else{
                        ResultSet rs = ed.query4(dayFrom,dayTo,eventsPlace);
                        return rs;
                    }
                } catch (ParseException ex) {
                    cw.displayErrorDialog("A ParseEception was detected! ");
                    return null;
                }
            }
    }

    /**
     *
     */
    @Override
    public void bottoneBackPremuto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * @return
     */
    @Override
    public ResultSet getCursorFromAllClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
