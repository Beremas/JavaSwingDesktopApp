package View;

import Controller.GenericController;
import Model.Clients;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author conti
 */
public class ClientsView extends JFrame implements Observer{

    /**
     * Costruttore vuoto
     */
    public ClientsView(){}

    /**
     * Costruttore con Model dei clienti, e l'username dell'admin
     * @param ud = Model dei clienti
     * @param uname = useranme dell'admin.
     */
    public ClientsView(Clients ud,String uname) {
        initComponents();  
        AdminName = uname;
        clientModel = ud;
        clientModel.addObserver((ClientsView)this); // Connect the View to the Model
              
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
     * Getter
     * @return codice fiscale dell'cliente
     */
    public String getCf() {    
        return cf;
    }
    /**
     * Setter
     * @param cf = codice fiscale del cliente
     */
    public void setCf(String cf){
        this.cf=cf;
    }
    /**
     * Getter
     * @return nome del cliente
     */
    public String getNome() {
        return nome;
    }
    /**
     * Getter 
     * @return cognome del cliente
     */
    public String getCognome() {
        return cognome;
    }
    
    /**
     * initClientsView.
     * Questo metodo rende visibile la JFrame 
     */
    public void initClientsView(){
        this.setVisible(true);
    }
    
    /**
     * setAdminNameJLable.
     * Questo metodo preleva lo username dell admin e lo piazza
     * nella label in alto a sinistra della JFrama
     */
    private void setAdminNameJLable(){
        jadmiNname.setText(getAdminName());
    }
    
    /**
     * upadate
     * Questo metodo è chiamato quando è successo qualcosa allos tato  
     * dell'obesravble.
     * @param o
     * @param o1
     */
    @Override
    public void update(Observable o, Object o1) {
         fillClientTable();
    }
    
    /**
     * fillClientTable.
     * Questo metodo non fa altro che fetchare il resultSet richiesto dal controllore, 
     * per riempire la jtabel con i clienti avuti dalla attuale query.
     * @return 1 se il caricamento Ã¨ andato a buon fine.
     *         -1 e -2 se ci Ã¨ stato un errore tra SQL o Eccezione generale.
     */ 
    public int fillClientTable(){
        try {
            Vector data = new Vector();
            Vector columns;
            Vector row;
            
            ResultSet rset = controller.getCursorFromAllClients();
                   
            ResultSetMetaData metaData = rset.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            columns = new Vector(columnCount);
            
            //store column names
            for(int i=1; i<=columnCount; i++)
                columns.add(metaData.getColumnName(i));      
            
            //fatching data from db (rows)
            while (rset.next()) {
                row = new Vector(columnCount);
                for(int i=1; i<=columnCount; i++)
                {
                    row.add(rset.getString(i));
                }
                data.add(row);
            };
            
            DefaultTableModel tableModel = new DefaultTableModel(data, columns);
            jTableClients.setModel(tableModel);
            
            TableColumn column;
            for (int i = 0; i < jTableClients.getColumnCount(); i++) {
                column = jTableClients.getColumnModel().getColumn(i);
                column.setMaxWidth(250);
            }
                        
            jTableClients.repaint();
            
            
            
            return 1;
        } catch (SQLException ex) {
            DefaultTableModel emptyTableModel = new DefaultTableModel();
            jTableClients.setModel(emptyTableModel);
            return -1;
           
        } catch (Exception es) {
            DefaultTableModel emptyTableModel = new DefaultTableModel();
            jTableClients.setModel(emptyTableModel);
            return -2;
           
        }

    }
    
    /**
     *  AddControllerListener.
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
        JOptionPane.showMessageDialog(this, msg,"Client",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo errore, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayErrorDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"Client",JOptionPane.ERROR_MESSAGE);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonback = new javax.swing.JButton();
        jButtonlogout = new javax.swing.JButton();
        jwelcomelabel = new javax.swing.JLabel();
        jadmiNname = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClients = new javax.swing.JTable();
        bottoneDeleteClient = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonback.setText("Back");
        jButtonback.setMaximumSize(new java.awt.Dimension(65, 23));
        jButtonback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbackActionPerformed(evt);
            }
        });

        jButtonlogout.setText("Logout");
        jButtonlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonlogoutActionPerformed(evt);
            }
        });

        jwelcomelabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jwelcomelabel.setText("welcome: ");

        jadmiNname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jTableClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClients);

        bottoneDeleteClient.setText("Delete client");
        bottoneDeleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneDeleteClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bottoneDeleteClient, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jwelcomelabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jadmiNname, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonback, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonback, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jwelcomelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jadmiNname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottoneDeleteClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbackActionPerformed
        controller.bottoneBackPremuto();
    }//GEN-LAST:event_jButtonbackActionPerformed

    private void jButtonlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonlogoutActionPerformed
         controller.bottoneLogoutPremuto(this);
    }//GEN-LAST:event_jButtonlogoutActionPerformed

    private void bottoneDeleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneDeleteClientActionPerformed
         controller.bottoneDeleteClientPremuto();
    }//GEN-LAST:event_bottoneDeleteClientActionPerformed

    private void jTableClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientsMouseClicked
       int row = jTableClients.getSelectedRow();               
        this.cf=jTableClients.getModel().getValueAt(row, 2).toString();
    }//GEN-LAST:event_jTableClientsMouseClicked
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneDeleteClient;
    private javax.swing.JButton jButtonback;
    private javax.swing.JButton jButtonlogout;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableClients;
    private javax.swing.JLabel jadmiNname;
    private javax.swing.JLabel jwelcomelabel;
    // End of variables declaration//GEN-END:variables
    private GenericController controller;
    private String AdminName;
    private String cf;
    private String nome;
    private String cognome;
    
    
    private Clients clientModel;

    
}
