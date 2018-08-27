package View;

import Controller.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author 
 */
public class ChartsView extends JFrame {
    
    /**
     * Costruttore vuoto
     */
    public ChartsView() {
        initComponents();
        this.setLocationRelativeTo(null);
        pnChart.setLayout(new java.awt.BorderLayout());
    }

    /**
     * Costruttore con i resultSet delle query per riempire le combobox
     * @param RS_eventsname = cursore contenente tutti i nomi degli eventi presenti nel db
     * @param RS_eventplace = cursore contenente tutti i luoghi degli eventi presenti nel db
     */
    public ChartsView(ResultSet RS_eventsname, ResultSet RS_eventplace){
        try {
            initComponents();
            this.setLocationRelativeTo(null);
            pnChart.setLayout(new java.awt.BorderLayout());
            
            
            while(RS_eventsname.next()){
                jComboBoxeventsname.addItem(RS_eventsname.getString("NAME"));
            }
            while(RS_eventplace.next()){
                jComboBoxeventsplace.addItem(RS_eventplace.getString("PLACE"));
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(ChartsView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *  AddControllerListener.
     * Questo metodo permette di associare all'istanza this, il controllore
     * che lo gestisce, passato come parametro.
     * @param contrl 
     */
    public void AddControllerListener( GenericController contrl ){
        ec = contrl;  
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnChart = new javax.swing.JPanel();
        jLabeltitle = new javax.swing.JLabel();
        jButtonquery1 = new javax.swing.JButton();
        jComboBoxcharttype = new javax.swing.JComboBox<>();
        jDateChooserfrom = new com.toedter.calendar.JDateChooser();
        jDateChooserto = new com.toedter.calendar.JDateChooser();
        jLabelstartdate = new javax.swing.JLabel();
        jLabelenddate = new javax.swing.JLabel();
        jLabelcharttype = new javax.swing.JLabel();
        jLabelQuery1 = new javax.swing.JLabel();
        jLabelQuery2 = new javax.swing.JLabel();
        jButtonquery2 = new javax.swing.JButton();
        jLabeltitle1 = new javax.swing.JLabel();
        jLabeltitle2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jComboBoxeventsname = new javax.swing.JComboBox<>();
        jLabelQuery3 = new javax.swing.JLabel();
        jButtonquery3 = new javax.swing.JButton();
        jLabelQuery4 = new javax.swing.JLabel();
        jComboBoxeventsplace = new javax.swing.JComboBox<>();
        jButtonquery4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin Area: Statistics");

        pnChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnChartLayout = new javax.swing.GroupLayout(pnChart);
        pnChart.setLayout(pnChartLayout);
        pnChartLayout.setHorizontalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnChartLayout.setVerticalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabeltitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabeltitle.setText("Statistics");

        jButtonquery1.setText("Generate Chart");
        jButtonquery1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonquery1ActionPerformed(evt);
            }
        });

        jComboBoxcharttype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3D Pie Chart", "Line Chart", "Bar Chart"}));

        jLabelstartdate.setText("From");

        jLabelenddate.setText("To");

        jLabelcharttype.setText("Chart");

        jLabelQuery1.setText("<<Guadagno complessivo di tutti gli eventi>>");

        jLabelQuery2.setText("<<Numero eventi per luogo>>");

        jButtonquery2.setText("Generate Chart");
        jButtonquery2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonquery2ActionPerformed(evt);
            }
        });

        jLabeltitle1.setForeground(new java.awt.Color(0, 0, 204));
        jLabeltitle1.setText("Select the date 'from' 'to' and chart type and then select ");

        jLabeltitle2.setForeground(new java.awt.Color(0, 0, 204));
        jLabeltitle2.setText("the statistic that you want to see ");

        jComboBoxeventsname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an Event" }));

        jLabelQuery3.setText("<<Guadagno gruppato per luogo di: evento>>");

        jButtonquery3.setText("Generate Chart");
        jButtonquery3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonquery3ActionPerformed(evt);
            }
        });

        jLabelQuery4.setText("<<Numero eventi per: luogo >>");

        jComboBoxeventsplace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Place" }));

        jButtonquery4.setText("Generate Chart");
        jButtonquery4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonquery4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addComponent(jLabeltitle)
                .addContainerGap(434, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelQuery4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxeventsplace, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonquery4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabeltitle1)
                                .addComponent(jLabeltitle2)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelQuery1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelcharttype)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBoxcharttype, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabelenddate, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabelstartdate, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jDateChooserfrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jDateChooserto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addComponent(jButtonquery1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelQuery3)
                                .addComponent(jLabelQuery2)
                                .addComponent(jButtonquery2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jComboBoxeventsname, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonquery3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(46, 46, 46)
                        .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabeltitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabeltitle1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabeltitle2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelstartdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelenddate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelcharttype)
                            .addComponent(jComboBoxcharttype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addComponent(jLabelQuery1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonquery1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelQuery2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonquery2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelQuery3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxeventsname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonquery3)))
                    .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelQuery4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxeventsplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonquery4))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
      
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo informativo, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayMessagDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"Chart",JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Questo metodo permette di creare una finestra di messaggio di tipo JOptionPane
     * di tipo errore, con il messaggio impostato nel parametro.
     * @param msg 
     */
    public void displayErrorDialog(String msg){
        JOptionPane.showMessageDialog(this, msg,"Chart",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Questo metodo crea un dataset per il grafico a barre e linea (Prezzo,...,Luogo).
     * Il grafico a torta sarà costruito sulla base della query ottenuta, 
     * costruito in modo da avere un stringa 'x'  e un valore 'y' corrispondente da presentare nel grafico.
     * @param rs = cursore sui risultati della query.
     * @param part1 = Nome della colonna nella tabella, corrispondente alla stringa 'x'.
     * @param part2 =  Nome della colonna nella tabella, corrispondente al valore 'y' di 'x'.
     * @return DefaultPieDataset; 
     */
    private DefaultCategoryDataset setData1(ResultSet rs,String part1,String part2){
       try {
            /*
              DefaultCategoryDataset() -  Creates a new (empty) dataset.
            */
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()){
                 String place = rs.getString(part1);
                 float price = Float.parseFloat(rs.getString(part2));   
                 /*
                    Adds a value to the dataset.
                    addValue(double value, Comparable rowKey, Comparable columnKey) 
                  */
                 dataset.addValue(price, "Gain", place);
            }
            return dataset;
        } catch (SQLException ex) {
            //Logger.getLogger(ChartsView.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Questo metodo crea un dataset per il grafico a torta (Prezzo,Luogo).
     * Il grafico a torta sarà costruito sulla base della query ottenuta, 
     * costruito in modo da avere un stringa 'x'  e un valore 'y' corrispondente da presentare nel grafico.
     * @param rs = cursore sui risultati della query.
     * @param part1 = Nome della colonna nella tabella, corrispondente alla stringa 'x'.
     * @param part2 =  Nome della colonna nella tabella, corrispondente al valore 'y' di 'x'.
     * @return DefaultPieDataset; 
     */
    private DefaultPieDataset setData2(ResultSet rs, String part1, String part2){
         
         try {
           DefaultPieDataset dataset = new DefaultPieDataset();
            while(rs.next()){
                 //Adds or updates a value in the table and sends a DatasetChangeEvent to all registered listeners.
                 dataset.setValue(rs.getString(part1),Double.parseDouble(rs.getString(part2)));
            }
            return dataset;
        } catch (SQLException ex ) {
            //Logger.getLogger(ChartsView.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Questo metodo mi prermette di creare il grafico a torta 3D
     *    @ChartFactory        = Collezione di utility per la creazione di grafici con JFreeCharts
     *    @createPieChart3D    = Crea un grafico a torta 3D con lo specifico dataset
     *              createPieChart3D(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls)
     *    @param rs     = cursore sui risultati della query.
     *    @param part1  =  Nome della colonna nella tabella, corrispondente alla stringa 'x'.
     *    @param part2  =  Nome della colonna nella tabella, corrispondente al valore 'y' di 'x'.            
     *    @param chartTitle = Titolo del grafico
     */
    public void create3DPieChart(ResultSet rs,String part1, String part2, String chartTitle){
         JFreeChart chart = ChartFactory.createPieChart3D(
                        chartTitle, // chart title                   
                        setData2(rs,part1,part2), // data 
                        true, // include legend                   
                        true,
                        false);
        // set chart properties
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);

        // create chart panel the add it to swing panel in  jframe
        ChartPanel chartPanel = new ChartPanel(chart);
        pnChart.removeAll();        // clear panel before add new chart
        pnChart.add(chartPanel, BorderLayout.CENTER);
        pnChart.validate();         // refresh panel to display new chart
    }
    /**
     * Questo metodo mi prermette di creare il grafico a torta 3D
     *    @ChartFactory        = Collezione di utility per la creazione di grafici con JFreeCharts
     *    @createBarChart      = Crea un grafico a barre verticali.
     *            createBarChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset)       
     *    @param rs     =  Cursore sui risultati della query.
     *    @param part1  =  Nome della colonna nella tabella, corrispondente alla stringa 'x'.
     *    @param part2  =  Nome della colonna nella tabella, corrispondente al valore 'y' di 'x'.  
     *    @param chartTitle = Titolo del grafico.
     *    @param YTitle = Titolo dei parametri dell'asse Y del grafico
     *    @param XTitle = Titolo dei parametri dell'asse X del grafico
     */
    public void createBarChart(ResultSet rs,String part1, String part2,String chartTitle, String YTitle, String XTitle){
        // create bar chart 
         JFreeChart barChart = ChartFactory.createBarChart(
                 chartTitle,
                 XTitle, YTitle,
                 setData1(rs,part1,part2),
                 PlotOrientation.VERTICAL,
                 true, true, false);
         // set color
         CategoryPlot plot = (CategoryPlot) barChart.getPlot();
         plot.getRenderer().setSeriesPaint(0, Color.BLUE);
         // create chart panel the add it to swing panel in  jframe
         ChartPanel chartPanel = new ChartPanel(barChart);
         pnChart.removeAll();        // clear panel before add new chart
         pnChart.add(chartPanel, BorderLayout.CENTER);
         pnChart.validate();          // refresh panel to display new chart
    }
    /**
     *  Questo metodo mi prermette di creare il grafico a torta 3D
     *    @ChartFactory        = Collezione di utility per la creazione di grafici con JFreeCharts
     *    @createLineChart      = Crea un grafico a linee.
     *          createLineChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset)
     *    @param rs            = Cursore sui risultati della query.
     *    @param part1         = Nome della colonna nella tabella, corrispondente alla stringa 'x'.
     *    @param part2         = Nome della colonna nella tabella, corrispondente al valore 'y' di 'x'.
     *    @param chartTitle    = Titolo del grafico.
     *    @param YTitle        = Titolo dei parametri dell'asse Y del grafico
     *    @param XTitle        = Titolo dei parametri dell'asse X del grafico
     */
    public void createLineChart(ResultSet rs,String part1,String part2,String chartTitle,String YTitle, String XTitle){
        // create line chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                XTitle, YTitle,
                setData1(rs,part1,part2),
                PlotOrientation.VERTICAL,
                true, true, false);
        // set color
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);
        // create chart panel the add it to swing panel in  jframe
        ChartPanel chartPanel = new ChartPanel(lineChart);
        pnChart.removeAll();    // clear panel before add new chart
        pnChart.add(chartPanel, BorderLayout.CENTER);
        pnChart.validate();       // refresh panel to display new chart
    }    
    
    
    private void jButtonquery1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonquery1ActionPerformed
            
            String dayFrom = ((JTextField)jDateChooserfrom.getDateEditor().getUiComponent()).getText();
            String dayTo = ((JTextField)jDateChooserto.getDateEditor().getUiComponent()).getText();
            String charttype=(String)jComboBoxcharttype.getSelectedItem();
            
            String title="Gain for events";
            String XTitle="Events";
            String YTitle="Gains";
                        
            ResultSet rs = ec.bottoneQuery1Premuto(dayFrom,dayTo);
            
            if(rs != null){
                switch (charttype) {
                    case "3D Pie Chart":
                        create3DPieChart(rs,"NAME","PRICE",title);
                        break;
                    case "Line Chart":
                        createLineChart(rs,"NAME","PRICE",title,YTitle,XTitle);
                        break;
                    case "Bar Chart":
                        createBarChart(rs,"NAME","PRICE",title,YTitle,XTitle);
                        break;
                    default:
                        break;
                }
            }
           
    }//GEN-LAST:event_jButtonquery1ActionPerformed

    private void jButtonquery2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonquery2ActionPerformed
            
            String dayFrom = ((JTextField)jDateChooserfrom.getDateEditor().getUiComponent()).getText();
            String dayTo = ((JTextField)jDateChooserto.getDateEditor().getUiComponent()).getText();
            String charttype=(String)jComboBoxcharttype.getSelectedItem();
            
            String title="Events for places";
            String XTitle="Events";
            String YTitle="N° e/p";
            
            ResultSet rs = ec.bottoneQuery2Premuto(dayFrom, dayTo);
            
            if(rs!=null){
                switch (charttype) {
                    case "3D Pie Chart":
                        create3DPieChart(rs,"PLACE","NAME",title);
                        break;
                    case "Line Chart":
                        createLineChart(rs,"PLACE","NAME",title,YTitle,XTitle);
                        break;
                    case "Bar Chart":
                        createBarChart(rs,"PLACE","NAME",title,YTitle,XTitle);
                        break;
                    default:
                        break;
                }
            }
    }//GEN-LAST:event_jButtonquery2ActionPerformed

    private void jButtonquery3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonquery3ActionPerformed
            
            String dayFrom = ((JTextField)jDateChooserfrom.getDateEditor().getUiComponent()).getText();
            String dayTo = ((JTextField)jDateChooserto.getDateEditor().getUiComponent()).getText();
            String charttype=(String)jComboBoxcharttype.getSelectedItem();
            String eventsName= (String)jComboBoxeventsname.getSelectedItem();
            
            String title="Gains of: "+eventsName;
            String XTitle="Place";
            String YTitle="Gains";
            
            ResultSet rs = ec.bottoneQuery3Premuto(dayFrom, dayTo, eventsName);
            
            if(rs!=null){
                switch (charttype) {
                    case "3D Pie Chart":
                        create3DPieChart(rs,"PLACE","PRICE",title);
                        break;
                    case "Line Chart":
                        createLineChart(rs,"PLACE","PRICE",title,YTitle,XTitle);
                        break;
                    case "Bar Chart":
                        createBarChart(rs,"PLACE","PRICE",title,YTitle,XTitle);
                        break;
                    default:
                        break;
                }
            }
    }//GEN-LAST:event_jButtonquery3ActionPerformed

    private void jButtonquery4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonquery4ActionPerformed
       
            String dayFrom = ((JTextField)jDateChooserfrom.getDateEditor().getUiComponent()).getText();
            String dayTo = ((JTextField)jDateChooserto.getDateEditor().getUiComponent()).getText();
            String charttype=(String)jComboBoxcharttype.getSelectedItem();
            String eventsPlace= (String)jComboBoxeventsplace.getSelectedItem();
            
            String title="Total Event's in: "+eventsPlace;
            String XTitle="N° events";
            String YTitle="Events";
            
            ResultSet rs = ec.bottoneQuery4Premuto(dayFrom,dayTo,eventsPlace); 
            
            if(rs!=null){
                switch (charttype) {
                    case "3D Pie Chart":
                        create3DPieChart(rs,"NAME","NEventi",title);
                        break;
                    case "Line Chart":
                        createLineChart(rs,"NAME","NEventi",title,XTitle,YTitle);
                        break;
                    case "Bar Chart":
                        createBarChart(rs,"NAME","NEventi",title,XTitle,YTitle);
                        break;
                    default:
                        break;
                }
            }
    }//GEN-LAST:event_jButtonquery4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonquery1;
    private javax.swing.JButton jButtonquery2;
    private javax.swing.JButton jButtonquery3;
    private javax.swing.JButton jButtonquery4;
    private javax.swing.JComboBox<String> jComboBoxcharttype;
    private javax.swing.JComboBox<String> jComboBoxeventsname;
    private javax.swing.JComboBox<String> jComboBoxeventsplace;
    private com.toedter.calendar.JDateChooser jDateChooserfrom;
    private com.toedter.calendar.JDateChooser jDateChooserto;
    private javax.swing.JLabel jLabelQuery1;
    private javax.swing.JLabel jLabelQuery2;
    private javax.swing.JLabel jLabelQuery3;
    private javax.swing.JLabel jLabelQuery4;
    private javax.swing.JLabel jLabelcharttype;
    private javax.swing.JLabel jLabelenddate;
    private javax.swing.JLabel jLabelstartdate;
    private javax.swing.JLabel jLabeltitle;
    private javax.swing.JLabel jLabeltitle1;
    private javax.swing.JLabel jLabeltitle2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pnChart;
    // End of variables declaration//GEN-END:variables
    private GenericController ec;
}
