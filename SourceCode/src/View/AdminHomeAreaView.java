package View;

import Controller.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author 
 */
public class AdminHomeAreaView extends JFrame{
 
    /**
     * Costruttore con lo username dell'admin
     * @param uname
     */
    public AdminHomeAreaView(String uname) {
        initComponents();        
        
        AdminName = uname;
        setAdminNameJLable();
        
        //Centrare la finestra a video
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
   
    /**
     * Getter
     * @return Username dell'admin
     */
    public String getAdminName(){
        return this.AdminName;
    }
    
    /**
     * Questo metodo rende visibile la JFrame 
     */
    public void initAdminHomeAreaView(){
        this.setVisible(true);
    }
    
    /**
     *  AddControllerListener.
     * Questo metodo permette di associare all'istanza this, il controllore
     * che lo gestisce, passato come parametro.
     * @param contrl 
     */
    public void AddControllerListener(GenericController contrl ){
        this.lc = contrl;  
    }
      
    /**
     * setAdminNameJLable.
     * Questo metodo preleva lo username dell admin e lo piazza
     * nella label in alto a sinistra della JFrama
     */
    private void setAdminNameJLable(){
        jadmiNname.setText(getAdminName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        bottoneEvents = new javax.swing.JButton();
        bottoneClients = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jtitle2 = new javax.swing.JLabel();
        bottoneLogout = new javax.swing.JButton();
        jadmiNname = new javax.swing.JLabel();
        jwelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Area: Home");
        setResizable(false);

        bottoneEvents.setText("Events manager");
        bottoneEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneEventsActionPerformed(evt);
            }
        });

        bottoneClients.setText("Clients manager");
        bottoneClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneClientsActionPerformed(evt);
            }
        });

        jtitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtitle2.setText("Operations avaiable");

        bottoneLogout.setText("Logout");
        bottoneLogout.setPreferredSize(new java.awt.Dimension(55, 23));
        bottoneLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneLogoutActionPerformed(evt);
            }
        });

        jadmiNname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jwelcome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jwelcome.setText("Welcome:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jwelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jadmiNname, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bottoneLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtitle2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bottoneEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bottoneClients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jadmiNname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bottoneLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtitle2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottoneClients, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bottoneEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Listeners di bottoni
    private void bottoneEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneEventsActionPerformed
       lc.bottoneEventsMangerPremuto();
    }//GEN-LAST:event_bottoneEventsActionPerformed
    private void bottoneClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneClientsActionPerformed
       lc.bottoneClientsManagerPremuto();
    }//GEN-LAST:event_bottoneClientsActionPerformed

    private void bottoneLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneLogoutActionPerformed
       lc.bottoneLogoutPremuto(this);
    }//GEN-LAST:event_bottoneLogoutActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneClients;
    private javax.swing.JButton bottoneEvents;
    private javax.swing.JButton bottoneLogout;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jadmiNname;
    private javax.swing.JLabel jtitle2;
    private javax.swing.JLabel jwelcome;
    // End of variables declaration//GEN-END:variables
    private GenericController lc;
    private final String AdminName;
}
