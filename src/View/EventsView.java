package View;

import Controller.GenericController;
import Model.Events;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author
 */
public class EventsView extends JFrame implements Observer {
       
    
    /**
     * Costruttore vuoto
     */
    public EventsView() {
        initComponents();        
        
        
        //Centrare la finestra a video
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    /**
     * Costruttore con Model e username admin
     * @param ed = Model degli eventi.
     * @param uname = useranme dell'admin.
     */
    public EventsView(Events ed, String uname) {
        initComponents();   
        eventsModel = ed;
        eventsModel.addObserver((EventsView)this); // Connect the View to the Model
        
        this.AdminName = uname;
        setAdminNameJLable();
        
        
        //Centrare la finestra a video
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

 
    /**
     * Getter
     * @return username admin
     */
    public String getAdminName(){
        return this.AdminName;
    }
    /**
     * Getter
     * @return nome = nome dell'evento
     */
    public String getNome(){
        return this.nome;
    }
    /**
     * Getter 
     * @return luogo = luogo dell'evento
     */
    public String getLuogo(){
        return this.luogo;
    }
    /**
     * Getter
     * @return prezzo = prezzo dell'evento
     */
    public float getPrezzo(){
        return this.prezzo;
    }
    /**
     * Getter
     * @return giorno = giorno dell'evento
     */
    public String getGiorno(){
        return this.giorno;
    }
    /**
     * Getter
     * Questo getter server per tenere tarccia dell'attributo considerato pk nella tabella.
     * Perchè se voglio effettuare un update devo avere una condizione where newX = oldX.
     * @return giorno al momento della selezione della riga.
     */
    public String getOldGiorno(){
        return this.old_giorno;
    }
    /**
     * Getter
     * Questo getter server per tenere tarccia dell'attributo considerato pk nella tabella.
     * Perchè se voglio effettuare un update devo avere una condizione where newX = oldX.
     * @return vecchio luogo
     */
    public String getOldLuogo(){
        return this.old_luogo;
    }
    /**
     * Getter
     * Questo getter server per tenere tarccia dell'attributo considerato pk nella tabella.
     * Perchè se voglio effettuare un update devo avere una condizione where newX = oldX.
     * @return vecchio nome evento
     */
    public String getOldNome(){
        return this.old_nome;
    }
    /**
     * Getter 
     * @return numero biglietti venduti
     */
    public int getSelledTickets(){
        return this.selledtickets;
    }
    /**
     * Setter
     * @param n = nome evento
     */
    public void setNome(String n){
        this.nome=n;
    }
    /**
     * Setter
     * @param l = luogo dell'evento
     */
    public void setLuogo(String l){
        this.luogo = l;
    }
    /**
     * Setter
     * @param p = prezzo dell'evento 
     */
    public void setPrezzo(float p){
        this.prezzo = p;
    }
    /**
     * Setter
     * @param g = giorno dell'evento
     */
    public void setGiorno(String g){
        this.giorno = g;
    }
    /**
     * Setter 
     * @param st = numero biglietti evento venduti
     */
    public void setSelledTickets(int st){
        this.selledtickets = st;
    }
   
    /**
     * initEventView.
     * Questo metodo rende visibile la JFrame 
    */
    public void initEventView(){
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
        
        int errorCheck = fillEventTable();        
        /*
          Creo uno spazio vuoto nel caso ci fossero problemi cn la tabella di qualsiasi genere. 
          Almeno l'app sopravvive e non crasha. Megj e nient.
        */        
        switch (errorCheck) {
            case -1:
                DefaultTableModel emptyTableModel1 = new DefaultTableModel();
                jTableEvents.setModel(emptyTableModel1);
                break;
            case -2:
                DefaultTableModel emptyTableModel2 = new DefaultTableModel();
                jTableEvents.setModel(emptyTableModel2);
                break;
            case 1:
                break;
            default:
                break;
        }
    }
    
    /**
     * fillEventTable.
     * Questo metodo non fa altro che fetchare il resultSet richiesto dal controllore, 
     * per riempire la jtabel con gli elementi avuti dalla attuale query.
     * @return 1 se il caricamento è andato a buon fine.
     *         -1 e -2 se ci è stato un errore tra SQL o Eccezione generale.
     */ 
    public int fillEventTable(){
        try {
            Vector data = new Vector();
            Vector columns;
            Vector row;
            
            ResultSet rset = controller.getCursorFromAllEvents();
                   
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
            jTableEvents.setModel(tableModel);
            
            TableColumn column;
            for (int i = 0; i < jTableEvents.getColumnCount(); i++) {
                column = jTableEvents.getColumnModel().getColumn(i);
                column.setMaxWidth(250);
            }
                        
            jTableEvents.repaint();
            
            return 1;
        } catch (SQLException ex) {
            DefaultTableModel emptyTableModel = new DefaultTableModel();
            jTableEvents.setModel(emptyTableModel);
            return -1;
           
        } catch (Exception es) {
            DefaultTableModel emptyTableModel = new DefaultTableModel();
            jTableEvents.setModel(emptyTableModel);
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
        JOptionPane.showMessageDialog(this, msg,"Event",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo errore, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayErrorDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"Event",JOptionPane.ERROR_MESSAGE);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jadmiNname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEvents = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldselledTickets = new javax.swing.JTextField();
        jLabelsuggest2 = new javax.swing.JLabel();
        jLabelprezzo = new javax.swing.JLabel();
        jLabelluogo = new javax.swing.JLabel();
        jLabelgiorno = new javax.swing.JLabel();
        jLabelnome = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextFieldluogo = new javax.swing.JTextField();
        jTextFieldprezzo = new javax.swing.JTextField();
        jTextFieldnome = new javax.swing.JTextField();
        bottoneUpdateEvent = new javax.swing.JButton();
        bottoneDeleteEvent = new javax.swing.JButton();
        bottoneInsertEvent = new javax.swing.JButton();
        jButtonlogout = new javax.swing.JButton();
        jButtonback = new javax.swing.JButton();
        bottoneChart = new javax.swing.JButton();
        jLabelsuggest3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Area: Events manager");
        setPreferredSize(new java.awt.Dimension(971, 500));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Welcome:");

        jadmiNname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jTableEvents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEvents.getTableHeader().setReorderingAllowed(false);
        jTableEvents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEventsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEvents);
        if (jTableEvents.getColumnModel().getColumnCount() > 0) {
            jTableEvents.getColumnModel().getColumn(0).setResizable(false);
            jTableEvents.getColumnModel().getColumn(1).setResizable(false);
            jTableEvents.getColumnModel().getColumn(2).setResizable(false);
            jTableEvents.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setText("Selled Tickets");

        jLabelsuggest2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelsuggest2.setForeground(new java.awt.Color(51, 51, 255));
        jLabelsuggest2.setText("Events attribute");

        jLabelprezzo.setText("Price €");

        jLabelluogo.setText("Place");

        jLabelgiorno.setText("Day");

        jLabelnome.setText("Event's name");

        bottoneUpdateEvent.setText("Update event");
        bottoneUpdateEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneUpdateEventActionPerformed(evt);
            }
        });

        bottoneDeleteEvent.setText("Delete event");
        bottoneDeleteEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneDeleteEventActionPerformed(evt);
            }
        });

        bottoneInsertEvent.setText("Insert new event");
        bottoneInsertEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneInsertEventActionPerformed(evt);
            }
        });

        jButtonlogout.setText("Logout");
        jButtonlogout.setPreferredSize(new java.awt.Dimension(55, 23));
        jButtonlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonlogoutActionPerformed(evt);
            }
        });

        jButtonback.setText("Back");
        jButtonback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbackActionPerformed(evt);
            }
        });

        bottoneChart.setText("Create Chart");
        bottoneChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneChartActionPerformed(evt);
            }
        });

        jLabelsuggest3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelsuggest3.setForeground(new java.awt.Color(51, 51, 255));
        jLabelsuggest3.setText("Events table");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bottoneUpdateEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelprezzo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelluogo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelnome, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldselledTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextFieldprezzo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldluogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jTextFieldnome, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelgiorno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bottoneInsertEvent)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bottoneDeleteEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelsuggest2))))
                                .addGap(23, 23, 23)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelsuggest3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bottoneChart))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jadmiNname, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonback, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonback, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jadmiNname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelsuggest2)
                    .addComponent(jLabelsuggest3)
                    .addComponent(bottoneChart))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelgiorno)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldprezzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelprezzo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldluogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelluogo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelnome))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldselledTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bottoneInsertEvent)
                            .addComponent(bottoneDeleteEvent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bottoneUpdateEvent))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //lista bottoni
    private void bottoneUpdateEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneUpdateEventActionPerformed
        try{
            setNome(jTextFieldnome.getText());
            setLuogo(jTextFieldluogo.getText());
            setGiorno(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            if("".equals(jTextFieldprezzo.getText()))
            setPrezzo(0);
            else
            setPrezzo(Float.parseFloat(jTextFieldprezzo.getText()));
            if("".equals(jTextFieldselledTickets.getText()))
            setSelledTickets(0);
            else
            setSelledTickets(Integer.parseInt(jTextFieldselledTickets.getText()));

            controller.bottoneModifyEventPremuto();
            
            //svuoto i campi JTextField a sinistra della tabella
            jTextFieldnome.setText("");
            jTextFieldluogo.setText("");
            ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
            jTextFieldprezzo.setText("");
            jTextFieldselledTickets.setText("");
            
             //svuoto gli attributi della classe.
            setNome(null);
            setLuogo(null);
            setGiorno(null);
            setPrezzo(0);
            setSelledTickets(0);

        } catch (NumberFormatException e) {
            this.displayErrorDialog("One or more fields are not an integer input. Check again please.");
            
        }

        

    }//GEN-LAST:event_bottoneUpdateEventActionPerformed

    private void bottoneDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneDeleteEventActionPerformed
        //svuoto i campi JTextField a sinistra della tabella
        jTextFieldnome.setText("");
        jTextFieldluogo.setText("");
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
        jTextFieldprezzo.setText("");
        jTextFieldselledTickets.setText("");

        controller.bottoneDeleteEventsPremuto();
        
        //svuoto gli attributi della classe.
        setNome(null);
        setLuogo(null);
        setGiorno(null);
        setPrezzo(0);
        setSelledTickets(0);
        
    }//GEN-LAST:event_bottoneDeleteEventActionPerformed

    private void bottoneInsertEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneInsertEventActionPerformed
        //riempio gli attributi dai campi immessi nel jTextField controllando se sono vuoti e se corrispondono al tipo effettivo
        try {
            setNome(jTextFieldnome.getText());
            setLuogo(jTextFieldluogo.getText());
            setGiorno(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText());
            if("".equals(jTextFieldprezzo.getText()))
            setPrezzo(0);
            else
            setPrezzo(Float.parseFloat(jTextFieldprezzo.getText()));
            if("".equals(jTextFieldselledTickets.getText()))
            setSelledTickets(0);
            else
            setSelledTickets(Integer.parseInt(jTextFieldselledTickets.getText()));

            controller.bottoneInsertEventPremuto();

             //svuoto i campi JTextField a sinistra della tabella
            jTextFieldnome.setText("");
            jTextFieldluogo.setText("");
            ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
            jTextFieldprezzo.setText("");
            jTextFieldselledTickets.setText("");
            
             //svuoto gli attributi della classe.
             setNome(null);
             setLuogo(null);
             setGiorno(null);
             setPrezzo(0);
             setSelledTickets(0);
        } catch (NumberFormatException e) {
            this.displayErrorDialog("One or more fields are not an integer input. Check again please.");
        }

       

       

    }//GEN-LAST:event_bottoneInsertEventActionPerformed

    private void jButtonlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonlogoutActionPerformed
        controller.bottoneLogoutPremuto(this);
    }//GEN-LAST:event_jButtonlogoutActionPerformed

    private void jButtonbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbackActionPerformed
        controller.bottonBackPremuto();
    }//GEN-LAST:event_jButtonbackActionPerformed
  
    /**
     * jTableEventsMouseClicked
     * Questo evento si attiva nel momento in cui l'admin clicca su una riga della tabella.
     * Questo evento catturato si occupa di riportare nei JTextField le righe presenti nella tabella.
     * @param evt 
     */
    private void jTableEventsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEventsMouseClicked
      
        //Riempio gli attributi sul click dalla tabella
        int row = jTableEvents.getSelectedRow();
        this.selledtickets=Integer.parseInt(jTableEvents.getModel().getValueAt(row, 4).toString());
        this.prezzo=Float.parseFloat(jTableEvents.getModel().getValueAt(row, 2).toString());
        this.luogo=jTableEvents.getModel().getValueAt(row, 1).toString();
        this.nome=jTableEvents.getModel().getValueAt(row, 0).toString();     
        this.giorno = (String) jTableEvents.getModel().getValueAt(row, 3);
    
        //riempio gli attributi old, per eventuale update. Tengo traccia della vecchia pk del database
        this.old_giorno=this.giorno;
        this.old_luogo=this.luogo;
        this.old_nome=this.nome;
        
        //riempio i campi JTextField a sinistra della tabella       
        jTextFieldselledTickets.setText(String.valueOf(getSelledTickets()));
        jTextFieldnome.setText(getNome());
        jTextFieldluogo.setText(getLuogo());
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(getGiorno());     
        jTextFieldprezzo.setText(String.valueOf(getPrezzo()));
      
    }//GEN-LAST:event_jTableEventsMouseClicked

    private void bottoneChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneChartActionPerformed
        controller.bottoneGenerateChartPremuto();
    }//GEN-LAST:event_bottoneChartActionPerformed
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneChart;
    private javax.swing.JButton bottoneDeleteEvent;
    private javax.swing.JButton bottoneInsertEvent;
    private javax.swing.JButton bottoneUpdateEvent;
    private javax.swing.JButton jButtonback;
    private javax.swing.JButton jButtonlogout;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelgiorno;
    private javax.swing.JLabel jLabelluogo;
    private javax.swing.JLabel jLabelnome;
    private javax.swing.JLabel jLabelprezzo;
    private javax.swing.JLabel jLabelsuggest2;
    private javax.swing.JLabel jLabelsuggest3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableEvents;
    private javax.swing.JTextField jTextFieldluogo;
    private javax.swing.JTextField jTextFieldnome;
    private javax.swing.JTextField jTextFieldprezzo;
    private javax.swing.JTextField jTextFieldselledTickets;
    private javax.swing.JLabel jadmiNname;
    // End of variables declaration//GEN-END:variables
    private Events eventsModel;
    private GenericController controller; 
    
    private String nome;
    private String luogo;
    private float prezzo;
    private String giorno;
    
    private String old_nome, old_luogo, old_giorno;
    
    private int selledtickets;
    private String AdminName;
}
