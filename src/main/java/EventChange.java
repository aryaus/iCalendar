
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import com.lowagie.text.pdf.*;
import java.io.FileNotFoundException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Every user’s event is stored as a row in a table and could be edited & removed by the user.
 * The buttons back, export PDF, edit and delete have their own functionalities. 
 */
public class EventChange extends javax.swing.JFrame {

  
    /**
     * Creates new form Event
     */
      public EventChange() {
        initComponents();
        show_Table();
    }
      
          
/**
 * This method gets all user´s events from database and displays them as a jTable1
 *  
 */
    public void show_Table(){
        
        int c;
        Connection connection = DBconnection.connectToDatabase();
        DefaultTableModel RecordTable = (DefaultTableModel)jTableEvent.getModel();
        //int SelectedRows = jTable1.getSelectedRow();
        
        if(connection != null){
            try {
                
                int id = ICalendarFrame.user_id;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from events where UserID= '" +id+ "' ");
                ResultSetMetaData Rsmd = rs.getMetaData();
                c = Rsmd.getColumnCount();
                RecordTable.setRowCount(0);
                
                while(rs.next()){
                    //Data will be added until the end
                    Vector v = new Vector();
                    for(int j= 1; j<= c; j++){
                        
                        v.add(rs.getString("eventName"));
                        v.add(rs.getString("eventDate"));
                        v.add(rs.getString("eventTime"));
                        v.add(rs.getString("duration"));
                        v.add(rs.getString("location"));
                        v.add(rs.getString("participants"));
                        v.add(rs.getString("priority"));
                        v.add(rs.getString("reminder"));
                        v.add(rs.getString("ID"));
                        
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
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jbtnBack = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();
        jbtnEdit1 = new javax.swing.JButton();
        exportPdfBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jtxtEventName = new javax.swing.JTextField();
        jlEventName = new javax.swing.JLabel();
        jlDate = new javax.swing.JLabel();
        jlDuration = new javax.swing.JLabel();
        jtxtDuration = new javax.swing.JTextField();
        jlLocation = new javax.swing.JLabel();
        jtxtLocation = new javax.swing.JTextField();
        jlParticipants = new javax.swing.JLabel();
        jtxtParticipant = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        priorityComboBox = new javax.swing.JComboBox<>();
        reminderComboBox = new javax.swing.JComboBox<>();
        jlEventID = new javax.swing.JLabel();
        jtxtEventID = new javax.swing.JTextField();
        jtxtDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtxtTime = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEvent = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Event");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));

        jbtnBack.setBackground(new java.awt.Color(255, 255, 255));
        jbtnBack.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnBack.setText("Back");
        jbtnBack.setBorderPainted(false);
        jbtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBackActionPerformed(evt);
            }
        });

        jbtnDelete.setBackground(new java.awt.Color(255, 255, 255));
        jbtnDelete.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnDelete.setText("Delete");
        jbtnDelete.setBorderPainted(false);
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        jbtnEdit1.setBackground(new java.awt.Color(255, 255, 255));
        jbtnEdit1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnEdit1.setText("Edit");
        jbtnEdit1.setBorderPainted(false);
        jbtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEdit1ActionPerformed(evt);
            }
        });

        exportPdfBtn.setText("Export PDF");
        exportPdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPdfBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jbtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportPdfBtn)
                .addGap(54, 54, 54)
                .addComponent(jbtnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jbtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnDelete)
                    .addComponent(jbtnEdit1)
                    .addComponent(jbtnBack)
                    .addComponent(exportPdfBtn))
                .addGap(33, 33, 33))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));

        jtxtEventName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtEventName.setDoubleBuffered(true);
        jtxtEventName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtEventNameActionPerformed(evt);
            }
        });

        jlEventName.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlEventName.setText("Event Name");

        jlDate.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlDate.setText("Date");

        jlDuration.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlDuration.setText("Duration");

        jtxtDuration.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtDuration.setDisabledTextColor(new java.awt.Color(21, 21, 96));

        jlLocation.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlLocation.setText("Location");

        jtxtLocation.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jlParticipants.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlParticipants.setText("Participants");

        jtxtParticipant.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("Reminder");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setText("Priority");

        priorityComboBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        priorityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "high", "medium", "low" }));
        priorityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priorityComboBoxActionPerformed(evt);
            }
        });

        reminderComboBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        reminderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 week", "3 days", "1 hour", "10 minutes" }));
        reminderComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reminderComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reminderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priorityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priorityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reminderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jlEventID.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlEventID.setText("Event ID");

        jtxtEventID.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Time");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jlEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtEventID))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jtxtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtxtDuration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtEventName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtParticipant, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTableEvent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Date", "Time", "Duration", "Location", "Participants", "Priority", "Remeinder", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEvent.getTableHeader().setResizingAllowed(false);
        jTableEvent.getTableHeader().setReorderingAllowed(false);
        jTableEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEventMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableEvent);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void priorityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priorityComboBoxActionPerformed

    private void jtxtEventNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtEventNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtEventNameActionPerformed
    
/**
 *If you want to delete an event,you should choose the event from jTable and then press the delete button
 * 
 */
    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
 
        // TODO add your handling code here:
        
         DefaultTableModel RecordTable = (DefaultTableModel)jTableEvent.getModel();
        int selectedIndex = jTableEvent.getSelectedRow();
        

        Connection connection = DBconnection.connectToDatabase();

        if(connection != null){
            try {

                String id = jtxtEventID.getText();
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do want to Delete the Event", "Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                
                    PreparedStatement delete = connection.prepareStatement("delete from events where id = ?");
                    delete.setInt(1,Integer.parseInt(id));

                    int i= delete.executeUpdate();
                    if(i >=1){
                    
                        JOptionPane.showMessageDialog(null,"Information has been deleted!");

                        show_Table(); // show the table after updating.
 
                    }else{
                    JOptionPane.showMessageDialog(null,"Delete failed!");
                    }
                }

            } catch (SQLException ex){
                Logger.getLogger(ICalendarFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO DATABASE CONNECTION!");
        }
    }//GEN-LAST:event_jbtnDeleteActionPerformed

    private void reminderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reminderComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reminderComboBoxActionPerformed
/**
 *  If you want to display the calendar page, you should press the back button
 * 
 */
    private void jbtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBackActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        CalendarPage cal = new CalendarPage();
        cal.setVisible(true);

    }//GEN-LAST:event_jbtnBackActionPerformed
/**
 * 
 * If you want to edit an event, you should select the event from jTable then you can edit your data and press the update button.
 * The given data are saved and displayed in jTable1
 * @param evt 
 */
    private void jbtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEdit1ActionPerformed
        // TODO add your handling code here:
        
        Connection connection = DBconnection.connectToDatabase();

        if(connection != null){
            try {

                String eventName = jtxtEventName.getText();
                String date = jtxtDate.getText();
                String time = jtxtTime.getText();
                String duration = jtxtDuration.getText();
                String location = jtxtLocation.getText();
                String participant = jtxtParticipant.getText();
                String priority = priorityComboBox.getSelectedItem().toString();
                String reminder = reminderComboBox.getSelectedItem().toString();
                String id = jtxtEventID.getText();

                PreparedStatement update = connection.prepareStatement("update events set eventName = ?,eventDate = ?,eventTime = ?,duration = ?,location = ?, participants = ?, priority = ?, reminder = ? where id = ?");

                update.setString(1,eventName);
                update.setString(2,date);
                update.setString(3,time);
                update.setString(4,duration);
                update.setString(5,location);
                update.setString(6,participant);
                update.setString(7,priority);
                update.setString(8,reminder);
                update.setInt(9,Integer.parseInt(id));

                int i= update.executeUpdate();

                if(i >=1){
                    
                    JOptionPane.showMessageDialog(null,"Information has been Updated!");

                    show_Table(); // show the table after updating.
                    

                }else{
                    JOptionPane.showMessageDialog(null,"Update failed!");
                }

            } catch (SQLException ex){
                Logger.getLogger(ICalendarFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO DATABASE CONNECTION!");
        }
    }//GEN-LAST:event_jbtnEdit1ActionPerformed
/**
 * The data of the selected event are displayed in related jTextFields
 * 
 */
    private void jTableEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEventMouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel RecordTable = (DefaultTableModel)jTableEvent.getModel();
        int selectedIndex = jTableEvent.getSelectedRow();

        
        jtxtEventName.setText(RecordTable.getValueAt(selectedIndex, 0).toString());
        jtxtDate.setText(RecordTable.getValueAt(selectedIndex, 1).toString());
        
        jtxtTime.setText(RecordTable.getValueAt(selectedIndex, 2).toString());
        jtxtDuration.setText(RecordTable.getValueAt(selectedIndex, 3).toString());
        jtxtLocation.setText(RecordTable.getValueAt(selectedIndex, 4).toString());
        jtxtParticipant.setText(RecordTable.getValueAt(selectedIndex, 5).toString());
        priorityComboBox.setSelectedItem(RecordTable.getValueAt(selectedIndex, 6));
        reminderComboBox.setSelectedItem(RecordTable.getValueAt(selectedIndex, 7));
        jtxtEventID.setText(RecordTable.getValueAt(selectedIndex, 8).toString());

    }//GEN-LAST:event_jTableEventMouseClicked
/**
 * this method converts all data from jTable to pdf file
 * @param evt 
 */
    private void exportPdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPdfBtnActionPerformed
        
              
        String path="";
        JFileChooser file= new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x=file.showSaveDialog(this);
        
        if(x == JFileChooser.APPROVE_OPTION){
            path = file.getSelectedFile().getPath();
        }
        
        Document doc= new Document();
        
          try {
              PdfWriter.getInstance(doc, new FileOutputStream(path+"events.pdf"));
              
              doc.open();
              
              PdfPTable tbl = new PdfPTable(8);
              
              // adding header
              tbl.addCell("Name");
              tbl.addCell("Date");
              tbl.addCell("Time");
              tbl.addCell("Duration");
              tbl.addCell("Location");
              tbl.addCell("Participants");
              tbl.addCell("Priority");
              tbl.addCell("Reminder");
              tbl.addCell("Event ID");
              
              for(int i= 0; i < jTableEvent.getRowCount(); i++){
                  
        String name = jTableEvent.getValueAt(i, 0).toString();
        String date = jTableEvent.getValueAt(i, 1).toString();
        String time = jTableEvent.getValueAt(i, 2).toString();
        String duration = jTableEvent.getValueAt(i, 3).toString();
        String location = jTableEvent.getValueAt(i, 4).toString();
        String participant = jTableEvent.getValueAt(i, 5).toString();
        String priority = jTableEvent.getValueAt(i, 6).toString();
        String reminder = jTableEvent.getValueAt(i, 7).toString();
        String id = jTableEvent.getValueAt(i, 8).toString();
        
        
        tbl.addCell(name);
        tbl.addCell(date);
        tbl.addCell(duration);
        tbl.addCell(location);
        tbl.addCell(participant);
        tbl.addCell(priority);
        tbl.addCell(reminder);
        tbl.addCell(id);
        
                  
              }
              
              doc.add(tbl);           
              
              
              
          } catch (FileNotFoundException ex) {
              Logger.getLogger(EventChange.class.getName()).log(Level.SEVERE, null, ex);
          } catch (DocumentException ex) {
              Logger.getLogger(EventChange.class.getName()).log(Level.SEVERE, null, ex);
          }
        
          doc.close();
        
              
        
        // TODO add your handling code here:
    }//GEN-LAST:event_exportPdfBtnActionPerformed

    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Event.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Event.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Event().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportPdfBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableEvent;
    private javax.swing.JButton jbtnBack;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnEdit1;
    private javax.swing.JLabel jlDate;
    private javax.swing.JLabel jlDuration;
    private javax.swing.JLabel jlEventID;
    private javax.swing.JLabel jlEventName;
    private javax.swing.JLabel jlLocation;
    private javax.swing.JLabel jlParticipants;
    private javax.swing.JTextField jtxtDate;
    private javax.swing.JTextField jtxtDuration;
    private javax.swing.JTextField jtxtEventID;
    private javax.swing.JTextField jtxtEventName;
    private javax.swing.JTextField jtxtLocation;
    private javax.swing.JTextField jtxtParticipant;
    private javax.swing.JTextField jtxtTime;
    private javax.swing.JComboBox<String> priorityComboBox;
    private javax.swing.JComboBox<String> reminderComboBox;
    // End of variables declaration//GEN-END:variables
}
