package View;

import Model.Users;
import Controller.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class SignUpView extends JFrame {
     
    /**
     * Costruttore vuoto
     */
    public SignUpView(){}
    /**
     * Costruttore con Model
     * @param ad
     */
    public SignUpView(Users ad) {
        initComponents();
        
        //Prendo le dimensioni dello schermo, per poi poter accentrare la finestra creata
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.userModel = ad;
    }
    
    /**
     * Getter 
     * @return Username 
     */
    public String getUsername(){
        return this.StringUsername;
    }
    /**
     * Getter
     * @return password
     */
    public String getPassword(){
        return this.StringPassword;
    }
    /**
     * Getter 
     * @return Nome
     */
    public String getNome(){
        return this.StringName;
    }
    /**
     * Getter 
     * @return cognome
     */
    public String getCognome(){
        return this.StringCognome;
    }
    /**
     * Getter
     * @return Codice Fiscale
     */
    public String getCF(){
        return this.Stringcf;
    }        
    /**
     * Setter 
     * @param username = Username scelto dell'admin 
     */
    public void setUsername(String username){
        this.StringUsername=username;
    }
    /**
     * Setter
     * @param password = passoword scelto dell'admin 
     */
    public void setPassword(String password){
        this.StringPassword=password;
    }
    /**
     * Setter Nome
     * @param nome = Nome scelto dell'admin 
     */
    public void setNome(String nome){
        this.StringName=nome;
    }
    /**
     * Setter 
     * @param cognome = Cognome scelto dell'admin 
     */
    public void setCognome(String cognome){
        this.StringCognome=cognome;
    }
    /**
     * Setter
     * @param cf = codice fiscale scelto dall'utente
     */
    public void setCF(String cf){
        this.Stringcf=cf;
    }        
   
    /**
     * initSignUpView.
     * Questo metodo rende visibile la JFrame 
     */
    public void initSignUpView(){
        this.setVisible(true);
    }
    
    /**
     * AddControllerListener.
     * Questo metodo permette di associare all'istanza this, il controllore
     * che lo gestisce, passato come parametro.
     * @param contrl 
     */
    public void AddControllerListener( GenericController contrl ){
        this.controller = contrl;  
    }
    
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo informativo, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayMessagDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"SignUp",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo errore, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayErrorDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"SignUp",JOptionPane.ERROR_MESSAGE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jname = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jsurname = new javax.swing.JLabel();
        jTextFieldSurname = new javax.swing.JTextField();
        jcf = new javax.swing.JLabel();
        jTextFieldCF = new javax.swing.JTextField();
        jpassword = new javax.swing.JLabel();
        jusername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        bottoneSingUp = new javax.swing.JButton();
        bottoneBack = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Area: Sign up");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Welcome new Admin. Fill the fields to complete the registration.");

        jname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jname.setText("Name");

        jsurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jsurname.setText("Surname");

        jcf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcf.setText("CF");

        jpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpassword.setText("Password");

        jusername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jusername.setText("Username");

        bottoneSingUp.setText("Sign up");
        bottoneSingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneSingUpActionPerformed(evt);
            }
        });

        bottoneBack.setText("Back");
        bottoneBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bottoneBack, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bottoneSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jcf)
                                    .addComponent(jpassword)
                                    .addComponent(jsurname)
                                    .addComponent(jname)
                                    .addComponent(jusername))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCF)
                                    .addComponent(jTextFieldUsername)
                                    .addComponent(jPasswordField)
                                    .addComponent(jTextFieldName))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jname)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsurname)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcf))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpassword)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jusername)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bottoneSingUp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Listeners di bottoni    
    private void bottoneBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneBackActionPerformed
        controller.bottoneBackPremuto();
    }//GEN-LAST:event_bottoneBackActionPerformed
    private void bottoneSingUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneSingUpActionPerformed
        setUsername(jTextFieldUsername.getText());
        setPassword(jPasswordField.getText());
        setNome(jTextFieldName.getText());
        setCognome(jTextFieldSurname.getText());
        setCF(jTextFieldCF.getText());
            
        controller.bottoneSignUpPremuto2();
        
    }//GEN-LAST:event_bottoneSingUpActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneBack;
    private javax.swing.JButton bottoneSingUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldCF;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldSurname;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JLabel jcf;
    private javax.swing.JLabel jname;
    private javax.swing.JLabel jpassword;
    private javax.swing.JLabel jsurname;
    private javax.swing.JLabel jusername;
    // End of variables declaration//GEN-END:variables
    private String StringUsername;
    private String StringPassword;
    private String StringName;
    private String StringCognome;
    private String Stringcf;
    
    private Users userModel;
    private GenericController controller;
    
}
