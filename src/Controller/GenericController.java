package Controller;

import View.EventsView;
import View.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public interface GenericController {
    
    public void bottoneBackPremuto();
    public void bottonBackPremuto();
    
    public void bottoneLoginPremuto();
    
    public void bottoneSignUpPremuto();
    public void bottoneSignUpPremuto2();
    public void bottoneClientsManagerPremuto();
    public void bottoneEventsMangerPremuto();
    
    public void bottoneLogoutPremuto(AdminHomeAreaView ahav);
    public void bottoneLogoutPremuto(EventsView ev);
    public void bottoneLogoutPremuto(ClientsView cv);
            
    public void bottoneDeleteEventsPremuto();
    public void bottoneInsertEventPremuto();
    public void bottoneModifyEventPremuto();
    public void bottoneGenerateChartPremuto();
    
    public ResultSet bottoneQuery1Premuto(String dayFrom, String dayTo);
    public ResultSet bottoneQuery2Premuto(String dayFrom, String dayTo);
    public ResultSet bottoneQuery3Premuto(String dayFrom, String dayTo, String eventsName);
    public ResultSet bottoneQuery4Premuto(String dayFrom, String dayTo, String eventsPlace);
    
    public void bottoneDeleteClientPremuto();

    public ResultSet getCursorFromAllEvents();

    public ResultSet getCursorFromAllClients();

    
    
    
}
