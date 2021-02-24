/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pouya Nikbakhsh
 */
public final class CalendarPage extends javax.swing.JFrame {
    
    public static DefaultTableModel model;
    public static int select_cell_row = -1 ;
    public static int select_cell_column = -1;
    public static int currentDayCell_row;
    public static int currentDayCell_column;
    public static int firstDayCell_row;
    public static int firstDayCell_column;
    public static int selected_month;
    public static int selected_year;
            
    public static int change_month;
    public static int change_year;
    public static int change_day = 0;
    
    
    
    
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    int current_day = calendar.get(Calendar.DATE);
    
    Calendar cal = new GregorianCalendar();
    String month_name;
    /**
     * Creates new form CalendarPage
     */
    
/**
 * this method gets all events of User from database and displays them as a jTable1
 * @param evt 
 */
  public void show_Table(){
        
        int c;
        Connection connection = DBconnection.connectToDatabase();
        DefaultTableModel RecordTable = (DefaultTableModel)jTableEvent.getModel();
        
        if(connection != null){
            try {
                
                int id = ICalendarFrame.user_id;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from events where UserID = '" +id+ "'");
                ResultSetMetaData Rsmd = rs.getMetaData();
                c = Rsmd.getColumnCount();
                RecordTable.setRowCount(0);
                
                while(rs.next()){
                    //Data will be added until finish
                    Vector v = new Vector();
                    for(int j= 1; j<= c; j++){
                        
                        v.add(rs.getString("eventName"));
                        v.add(rs.getString("eventDate"));
                        v.add(rs.getString("priority"));
                
                    }
                    
                    RecordTable.addRow(v);
                    
                   
                }  
                connection.close();
            }catch (SQLException ex){
                Logger.getLogger(ICalendarFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO DATABASE CONNECTION!");
        }
    }
  
  
    public CalendarPage() {
        initComponents();
        show_Table();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Swing Calandar");
        
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        
       jButton1_Previous.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            cal.add(Calendar.MONTH, -1);
            updateMonth();
          }
        });
      
        jButton2_next.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            cal.add(Calendar.MONTH, +1);
            updateMonth();
          }
        });
        
        
        jMonthChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jMonthChooser1PropertyChange(evt);
            }
        });
        
        jYearChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }   
        });  
             
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        
        
        jLabel1_PickDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1_PickDateMouseClicked(evt);
            }
        });
        
        jbtnCurrentDay.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
              jButton1_currentDayActionPerformed(evt);
            }
        });
        
        
        model = new DefaultTableModel();   
        model.addColumn("Mo");
        model.addColumn("Di");
        model.addColumn("Mi");
        model.addColumn("Do");
        model.addColumn("Fr");
        model.addColumn("Sa");
        model.addColumn("So");
        jTable1.setModel(model);
        this.updateMonth();
        setCurrentAndFirstDayRowandColumn();
        
        
}

    

    /**
     * Update Months
     */
    final void updateMonth() {
        
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.GERMANY);
        int year = cal.get(Calendar.YEAR);
        this.jLabel1_currentDate.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        
        model.setRowCount(0);
        model.setRowCount(6);
        int i = startDay-2;
        /**
         * if the startDay is sunday.
         */
        if(startDay == 1){
            i= i+7;
        }
                
        for(int day=1;day<=numberOfDays;day++){
          model.setValueAt(day, i/7 , i%7 );    
          i = i + 1;
        }
        
       CalendarPage.change_month = cal.get(Calendar.MONTH);
       CalendarPage.change_year =year;
        
       int month_inRenderer = cal.get(Calendar.MONTH) + 1;
       int year_inRenderer = year;
       
       setCurrentAndFirstDayRowandColumn();
       
       //with value -1 can I access just one time to the Cell of current day from the start program
       if(CalendarPage.select_cell_column == -1 && CalendarPage.select_cell_row == -1){
           CalendarPage.select_cell_column = CalendarPage.currentDayCell_column;
           CalendarPage.select_cell_row = CalendarPage.currentDayCell_row;
       }
  
       
       // print selected day
       if(model.getValueAt(select_cell_row, select_cell_column) != null
          &&CalendarPage.selected_month == CalendarPage.change_month 
          &&CalendarPage.selected_year == CalendarPage.change_year){
           int int_atCell = Integer.parseInt(model.getValueAt(select_cell_row, select_cell_column).toString());
           this.jLabel1_PickDate.setText(int_atCell+", "+month + " " + year);
       }
       
       
       TableColorCellRenderer cr = new TableColorCellRenderer(month_inRenderer, year_inRenderer);
       jTable1.setDefaultRenderer(Object.class, cr);

}
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2_Top = new javax.swing.JPanel();
        jButton1_Previous = new javax.swing.JButton();
        jButton2_next = new javax.swing.JButton();
        jLabel1_currentDate = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        logoutBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1_PickDate = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jYearChooser = new com.toedter.calendar.JYearChooser();
        jbtnCurrentDay = new javax.swing.JButton();
        jMonthChooser = new com.toedter.calendar.JMonthChooser();
        jPanel5 = new javax.swing.JPanel();
        Add_Event = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEvent = new javax.swing.JTable();
        jbtnChange = new javax.swing.JButton();
        jbtnBack = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(230, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 230, 230), 4));

        jPanel2_Top.setBackground(new java.awt.Color(230, 255, 255));
        jPanel2_Top.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 230, 230), 4));

        jButton1_Previous.setBackground(new java.awt.Color(255, 255, 255));
        jButton1_Previous.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arrows-Left-Round-icon.png"))); // NOI18N
        jButton1_Previous.setText("Previous");
        jButton1_Previous.setBorderPainted(false);
        jButton1_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_Previous_PreviousActionPerformed(evt);
            }
        });

        jButton2_next.setBackground(new java.awt.Color(255, 255, 255));
        jButton2_next.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arrows-Right-Round-icon.png"))); // NOI18N
        jButton2_next.setText("Next");
        jButton2_next.setBorderPainted(false);

        jLabel1_currentDate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1_currentDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1_currentDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2_TopLayout = new javax.swing.GroupLayout(jPanel2_Top);
        jPanel2_Top.setLayout(jPanel2_TopLayout);
        jPanel2_TopLayout.setHorizontalGroup(
            jPanel2_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2_TopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1_Previous)
                .addGap(211, 211, 211)
                .addComponent(jLabel1_currentDate, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2_next, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2_TopLayout.setVerticalGroup(
            jPanel2_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2_TopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2_TopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1_Previous, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton2_next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1_currentDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(230, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 230, 230), 4));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setRowHeight(85);
        jTable1.setSelectionBackground(new java.awt.Color(0, 195, 215));
        jTable1.setSelectionForeground(new java.awt.Color(204, 0, 102));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        logoutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logoutBtn.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.setBorderPainted(false);
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 230, 230));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel1_PickDate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1_PickDate.setText("No day was selected");

        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jTextPane1.setText("Your selected day:");
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1_PickDate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1_PickDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));

        jYearChooser.setBackground(new java.awt.Color(255, 255, 255));
        jYearChooser.setAutoscrolls(true);

        jbtnCurrentDay.setBackground(new java.awt.Color(255, 255, 255));
        jbtnCurrentDay.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jbtnCurrentDay.setText("Jump in the current day");
        jbtnCurrentDay.setBorderPainted(false);

        jMonthChooser.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnCurrentDay)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMonthChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCurrentDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 230, 230));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        Add_Event.setBackground(new java.awt.Color(255, 255, 255));
        Add_Event.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        Add_Event.setText("+");
        Add_Event.setBorderPainted(false);
        Add_Event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_EventActionPerformed(evt);
            }
        });

        jTableEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Date", "Priority"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableEvent);

        jbtnChange.setBackground(new java.awt.Color(255, 255, 255));
        jbtnChange.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnChange.setText("Events");
        jbtnChange.setBorderPainted(false);
        jbtnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jbtnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Add_Event, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Add_Event, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jbtnBack.setBackground(new java.awt.Color(255, 255, 255));
        jbtnBack.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnBack.setText("Back");
        jbtnBack.setBorderPainted(false);
        jbtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)
                        .addComponent(jbtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2_Top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel2_Top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        
    }//GEN-LAST:event_formWindowOpened

    private void jButton1_Previous_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_Previous_PreviousActionPerformed
        
    }//GEN-LAST:event_jButton1_Previous_PreviousActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked
/**
 *  if user press the logout button, ICalenderFrame is opened
 * @param evt 
 */
    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        this.dispose();
        ICalendarFrame cal = new ICalendarFrame();
        cal.setVisible(true);
        
    }//GEN-LAST:event_logoutBtnActionPerformed
/**
 *  if user press the back button, UserProfile is opened
 * @param evt 
 */
    private void jbtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        UserProfile cal = new UserProfile();
        cal.setVisible(true);
    }//GEN-LAST:event_jbtnBackActionPerformed
/**
 *  if user want to add a new event, he should press the Add_Event "+" button.
 * @param evt 
 */
    private void Add_EventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_EventActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Event cal = new Event();
        cal.setVisible(true);
    }//GEN-LAST:event_Add_EventActionPerformed
/**
 *  if user want to edit/update event's data, user should press change button
 * @param evt 
 */
    private void jbtnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnChangeActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        EventChange cal = new EventChange();
        cal.setVisible(true);
    }//GEN-LAST:event_jbtnChangeActionPerformed

    private void jMonthChooser1PropertyChange(java.beans.PropertyChangeEvent evt){
        
          int i;
          i = CalendarPage.change_month - jMonthChooser.getMonth();
          cal.add(Calendar.MONTH, -i);
          updateMonth();
        
    }    
    
    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt){
        
            int i;
            i = CalendarPage.change_year - jYearChooser.getYear();
            cal.add(Calendar.YEAR, -i);
            updateMonth();
  
    }
    
    
   private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {                                     
   
        CalendarPage.select_cell_row = jTable1.getSelectedRow();
        CalendarPage.select_cell_column = jTable1.getSelectedColumn();
        CalendarPage.selected_month=cal.get(Calendar.MONTH);
        CalendarPage.selected_year=cal.get(Calendar.YEAR);
        updateMonth();
    }
    private void jLabel1_PickDateMouseClicked(java.awt.event.MouseEvent evt) {                                     
        int i;
        i = CalendarPage.change_month - CalendarPage.selected_month;
        cal.add(Calendar.MONTH, -i);
        int j;        
        j = CalendarPage.change_year - CalendarPage.selected_year;
            cal.add(Calendar.YEAR, -j);
            updateMonth();
    }
    
    private void jButton1_currentDayActionPerformed(java.awt.event.ActionEvent evt) {                                         
       int i;
        i = CalendarPage.change_month - calendar.get(Calendar.MONTH);
        cal.add(Calendar.MONTH, -i);
        updateMonth();
        int j;        
        j = CalendarPage.change_year - calendar.get(Calendar.YEAR);;
            cal.add(Calendar.YEAR, -j);
            updateMonth();
            System.out.println(CalendarPage.change_year);
    } 
    
    private void setCurrentAndFirstDayRowandColumn() {
        for (int k = 0; k < jTable1.getRowCount(); k++) {
                for (int j = 0; j < jTable1.getColumnCount(); j++) {
                  if(jTable1.getValueAt(k,j) != null){  
                        int int_atCell = Integer.parseInt(jTable1.getValueAt(k, j).toString());
                        if(int_atCell == current_day){
                            CalendarPage.currentDayCell_row = k;
                            CalendarPage.currentDayCell_column = j;
                        }
                        if(int_atCell == 1){
                           CalendarPage.firstDayCell_row = k;
                           CalendarPage.firstDayCell_column = j; 
                        }
                    }
                }
        }
        
    }
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalendarPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        //</editor-fold>
        CalendarPage cal = new CalendarPage();
        cal.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Event;
    private javax.swing.JButton jButton1_Previous;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton2_next;
    private javax.swing.JLabel jLabel1_PickDate;
    private javax.swing.JLabel jLabel1_currentDate;
    private com.toedter.calendar.JMonthChooser jMonthChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2_Top;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableEvent;
    private javax.swing.JTextPane jTextPane1;
    private com.toedter.calendar.JYearChooser jYearChooser;
    private javax.swing.JButton jbtnBack;
    private javax.swing.JButton jbtnChange;
    private javax.swing.JButton jbtnCurrentDay;
    private javax.swing.JButton logoutBtn;
    // End of variables declaration//GEN-END:variables

    
}
