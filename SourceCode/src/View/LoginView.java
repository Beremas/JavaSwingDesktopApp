package View;

import Controller.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class LoginView extends JFrame{
    
    /**
     * Costruttore vuoto
     */
    public LoginView() {
        initComponents();        
       
        //Centrare la finestra a video
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        
    }    
   
    /**
     * Getter
     * @return Username
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * Getter
     * @return Password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * Setter
     * @param u = username dell'admin
     */
    public void setUsername(String u){
        this.username = u;
    }
    /**
     * Setter
     * @param p = password dell'admin
     */
    public void setPassword(String p){
        this.password = p;
    }
  
    /**
     * initLoginView.
     * Questo metodo rende visibile la JFrame 
     */
    public void initLoginView(){        
        this.setVisible(true); 
    }
    
    /**
     *  AddControllerListener.
     * Questo metodo permette di associare all'istanza this, il controllore
     * che lo gestisce, passato come parametro.
     * @param contrl 
     */
    public void AddControllerListener( GenericController contrl ){
        controller = contrl;  
    }
    
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo informativo, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayMessagDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"Login",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo errore, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayErrorDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"Login",JOptionPane.ERROR_MESSAGE);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bottoneLogin = new javax.swing.JButton();
        bottoneSignUp = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();
        jLabeltitle = new javax.swing.JLabel();
        jLabelpassword = new javax.swing.JLabel();
        jLabelusername = new javax.swing.JLabel();
        jTextFieldusername = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Area: Login");
        setResizable(false);

        bottoneLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bottoneLogin.setText("Login");
        bottoneLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneLoginActionPerformed(evt);
            }
        });

        bottoneSignUp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bottoneSignUp.setText("Sign up");
        bottoneSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneSignUpActionPerformed(evt);
            }
        });

        jPasswordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabeltitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabeltitle.setText("Login");

        jLabelpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelpassword.setText("Password");

        jLabelusername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelusername.setText("Username");

        jTextFieldusername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelusername)
                    .addComponent(jLabelpassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bottoneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bottoneSignUp))
                    .addComponent(jPasswordField)
                    .addComponent(jTextFieldusername))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabeltitle, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabeltitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldusername, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelusername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bottoneLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Listeners di bottoni
    private void bottoneSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneSignUpActionPerformed
        controller.bottoneSignUpPremuto();
    }//GEN-LAST:event_bottoneSignUpActionPerformed
    private void bottoneLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneLoginActionPerformed
        setUsername(jTextFieldusername.getText());
        setPassword(jPasswordField.getText());
        
        controller.bottoneLoginPremuto();
    }//GEN-LAST:event_bottoneLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneLogin;
    private javax.swing.JButton bottoneSignUp;
    private javax.swing.JLabel jLabelpassword;
    private javax.swing.JLabel jLabeltitle;
    private javax.swing.JLabel jLabelusername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldusername;
    // End of variables declaration//GEN-END:variables
    private String username;
    private String password;
    
    private GenericController controller;
}
