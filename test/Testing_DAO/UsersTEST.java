package Testing_DAO;

import Database.ConnectionController;
import Model.Users;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class UsersTEST {
    private Connection myCon;
     
    
    
    public UsersTEST() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    

    
    @Before
    public void setUp() {
        try {
            myCon = ConnectionController.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsersTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            myCon.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /*
        Precondizione: Tabella <<USERS>> VUOTA, altrimenti alcuni test falliscono.
        Logica dei test, descritta dettagliatamente nel <<documento di Testing del sistema>>
    */
    @Test
    public void checkIf_returnValueOF_insertNewAdmin_and_validateLogin_worksCorrectly(){
        
        int howManyAdmin;
        int errorCheck;     
        boolean errorCheck2;
        String nome,cognome,cf,username,password;
        
        nome="prova";
        cognome="prova";
        cf="123456789asdweqr";
        username="prova";
        password="prova";
        
        //Creo l'instance della DAO da testare.
        Users instance = new Users();
        
    
        
       
        /*
          1) Inserisco nel db un nuovo admin.
          2) Richiedo il numero degli admin presenti nel DB.
        
          primo assert) Verifico se effettivamente la 2) mi ritorna il conteggio di una sola riga,
                        e quindi essere uguale a 1.
          secondo assert) Verifico se la funzione di inserimento non viola alcun vincolo di chiave primaria/unique
                          e che quindi il metodo restituisce correttamente il valore di notifica, ovvero 1  
        */
        errorCheck = instance.insertNewAdmin(myCon, nome, cognome, cf, username, password);
        howManyAdmin = instance.getHowManyAdminAreSigned(myCon);
        assertEquals(1,howManyAdmin);
        assertEquals(1,errorCheck);

        /*
            Verifico che sia violato il vincolo di di chiave primaria/unique, provando 
            ad inserire le stesse credenziali, precedentemente inserite.
            Quindi verifico se il metodo restituisce correttamente il valore di notifica, ovvero -1
        */
        errorCheck = instance.insertNewAdmin(myCon, nome, cognome, cf, username, password);        
        assertEquals(-1,errorCheck);
        
        /*
            Verifico che sia violato il vincolo di di chiave primaria/unique, provando 
            ad inserire un cf nullo.
            Quindi verifico se il metodo restituisce correttamente il valore di notifica, ovvero -1
        */
        errorCheck = instance.insertNewAdmin(myCon, nome, cognome, null, username, password);        
        assertEquals(-1,errorCheck);
        
        /*
            Verifico che sia violato il vincolo di di chiave primaria/unique, provando 
            ad inserire un cf nullo e una connessione nulla.
            Quindi verifico se il metodo restituisce correttamente il valore di notifica, ovvero -1
        */
        errorCheck = instance.insertNewAdmin(null, nome, cognome, null, username, password);        
        assertEquals(-3,errorCheck);
        
        /*
            Al posto della connessione, passo null e verifico se il metodo restituisce correttamente 
            il valore di notifica, ovvero -3.
        */
        errorCheck = instance.insertNewAdmin(null, nome, cognome, cf, username, password);        
        assertEquals(-3,errorCheck);
        
        
        /*
            Testare se le credenziali dell'admin precedentemente inserito
            risultano essere realmente presenti nel db.
            
            primo assert) Verifico che il metodo ritorni effettuvamente true 
                          nella verifica della presenza delle credenziali 
                          dell'Admin precedentemente inserito.
            secondo e terzo assert) Verifico se il metodo ritorna effettuvamente false
                                    nella verifica delle non presenza di credenziali
                                    non presenti nel db.
            quarto assert) Verifico che il metodo ritorna effettivamente false 
                           qualora non esista una socket per la connessione.
        */
        errorCheck2 = instance.validateLogin(myCon, username, password);
        /*1*/
        assertEquals(true,errorCheck2);
        
        errorCheck2 = instance.validateLogin(myCon, "ciao", password);
        /*2*/
        assertEquals(false,errorCheck2);
        errorCheck2 = instance.validateLogin(myCon, username, "ciao");
        /*3*/
        assertEquals(false,errorCheck2);
        
        errorCheck2 = instance.validateLogin(null, username, password);
        /*4*/
        assertEquals(false,errorCheck2);

        
        //ripulisco il database lasciandolo nello stato precedente all'esecuzione di test
        instance.deleteAdmin(myCon, cf);
    }
    
    
    
 
        

}
