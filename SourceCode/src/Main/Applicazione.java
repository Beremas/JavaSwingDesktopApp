package Main;

import Model.Users;
import View.*;
import Controller.*;

/**
 *
 * @author 
 */
public class Applicazione {

    /**
     *
     * @param args
     */
    public static void main(String[] args){        
        
       
              Users usersModel = new Users();        
              LoginView lw = new LoginView();                
              GenericController controller = new LogController(lw,usersModel);        

              lw.initLoginView();
        
    }
}
