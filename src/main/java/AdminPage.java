
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.*;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pegah
 */
public class AdminPage extends javax.swing.JFrame {

    /**
     * Creates new form AdminPage
     */

    public AdminPage() {
        initComponents();
        show_Table(); // call this method to show the updated data in table
    }
    
    public void show_Table(){
        
        //Show Table
        int c;
        Connection connection = DBconnection.connectToDatabase();
        DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
        //int SelectedRows = jTable1.getSelectedRow();
        
        if(connection != null){
            try {
                
                 
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from users");
                ResultSetMetaData Rsmd = rs.getMetaData();
                c = Rsmd.getColumnCount();
                RecordTable.setRowCount(0);
                
                while(rs.next()){
                    //Data will be added until finish
                    Vector v = new Vector();
                    for(int j= 1; j<= c; j++){
                        
                        v.add(rs.getString("fname"));
                        v.add(rs.getString("lname"));
                        v.add(rs.getString("email"));
                        v.add(rs.getString("uname"));
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

        jpAdminPage = new javax.swing.JPanel();
        jpButton = new javax.swing.JPanel();
        jbtnUpdate = new javax.swing.JButton();
        jbtnReset = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();
        jbtnLogout = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jlID = new javax.swing.JLabel();
        jtxtID = new javax.swing.JTextField();
        jlFirstName = new javax.swing.JLabel();
        jlLastName = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        jlUserName = new javax.swing.JLabel();
        jtxtFirstName = new javax.swing.JTextField();
        jtxtLastName = new javax.swing.JTextField();
        jtxtEmail = new javax.swing.JTextField();
        jtxtUserName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin page");
        setBackground(new java.awt.Color(51, 0, 255));

        jpAdminPage.setBackground(new java.awt.Color(204, 255, 255));
        jpAdminPage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        jpButton.setBackground(new java.awt.Color(204, 255, 255));
        jpButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        jbtnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jbtnUpdate.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnUpdate.setText("Update");
        jbtnUpdate.setBorderPainted(false);
        jbtnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnUpdateMouseClicked(evt);
            }
        });
        jbtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateActionPerformed(evt);
            }
        });

        jbtnReset.setBackground(new java.awt.Color(255, 255, 255));
        jbtnReset.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnReset.setText("Reset");
        jbtnReset.setBorderPainted(false);
        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetActionPerformed(evt);
            }
        });

        jbtnExit.setBackground(new java.awt.Color(255, 255, 255));
        jbtnExit.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnExit.setText("Exit");
        jbtnExit.setBorderPainted(false);
        jbtnExit.setMaximumSize(new java.awt.Dimension(61, 35));
        jbtnExit.setMinimumSize(new java.awt.Dimension(61, 35));
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });

        jbtnLogout.setBackground(new java.awt.Color(255, 255, 255));
        jbtnLogout.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnLogout.setText("Logout");
        jbtnLogout.setBorderPainted(false);
        jbtnLogout.setMaximumSize(new java.awt.Dimension(83, 35));
        jbtnLogout.setMinimumSize(new java.awt.Dimension(83, 35));
        jbtnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLogoutActionPerformed(evt);
            }
        });

        jbtnDelete.setBackground(new java.awt.Color(255, 255, 255));
        jbtnDelete.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jbtnDelete.setText("Delete");
        jbtnDelete.setBorderPainted(false);
        jbtnDelete.setMaximumSize(new java.awt.Dimension(83, 35));
        jbtnDelete.setMinimumSize(new java.awt.Dimension(83, 35));
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpButtonLayout = new javax.swing.GroupLayout(jpButton);
        jpButton.setLayout(jpButtonLayout);
        jpButtonLayout.setHorizontalGroup(
            jpButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jpButtonLayout.setVerticalGroup(
            jpButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpButtonLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbtnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        jTable1.setBackground(new java.awt.Color(204, 255, 255));
        jTable1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Firstname", "Lastname", "Email", "Username", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        jlID.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlID.setText("ID");

        jtxtID.setEditable(false);
        jtxtID.setBackground(new java.awt.Color(255, 255, 255));
        jtxtID.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtIDActionPerformed(evt);
            }
        });
        jtxtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtIDKeyReleased(evt);
            }
        });

        jlFirstName.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlFirstName.setText("Firstname");

        jlLastName.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlLastName.setText("Lastname");

        jlEmail.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlEmail.setText("Email");

        jlUserName.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jlUserName.setText("Username");

        jtxtFirstName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtFirstNameActionPerformed(evt);
            }
        });
        jtxtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtFirstNameKeyReleased(evt);
            }
        });

        jtxtLastName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtLastNameActionPerformed(evt);
            }
        });
        jtxtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtLastNameKeyReleased(evt);
            }
        });

        jtxtEmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtEmailActionPerformed(evt);
            }
        });
        jtxtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtEmailKeyReleased(evt);
            }
        });

        jtxtUserName.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtxtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtUserNameActionPerformed(evt);
            }
        });
        jtxtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtUserNameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlID)
                        .addGap(76, 76, 76)
                        .addComponent(jtxtID))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtLastName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlFirstName)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtEmail))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlUserName)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtUserName)))
                .addContainerGap(411, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jpAdminPageLayout = new javax.swing.GroupLayout(jpAdminPage);
        jpAdminPage.setLayout(jpAdminPageLayout);
        jpAdminPageLayout.setHorizontalGroup(
            jpAdminPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAdminPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAdminPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpAdminPageLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpAdminPageLayout.setVerticalGroup(
            jpAdminPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAdminPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAdminPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpAdminPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpAdminPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        // TODO : Exit
        frame = new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "MYSQL Connector", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }

    }//GEN-LAST:event_jbtnExitActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        

        Connection connection = DBconnection.connectToDatabase();

        if(connection != null){
            try {

                String id = jtxtID.getText();
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do want to Delete the User", "Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                
                    PreparedStatement delete = connection.prepareStatement("delete from users where id = ?");
                    delete.setInt(1,Integer.parseInt(id));

                    int i= delete.executeUpdate();
                    if(i >=1){
                    
                        JOptionPane.showMessageDialog(null,"Information has been deleted!");

                        show_Table(); // show the table after update.
                        jtxtID.setText("");
                        jtxtFirstName.setText("");
                        jtxtLastName.setText("");
                        jtxtEmail.setText("");
                        jtxtUserName.setText("");
                        jtxtFirstName.requestFocus();

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

    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
        // TODO: Reset

        jtxtID.setText("");
        jtxtFirstName.setText("");
        jtxtLastName.setText("");
        jtxtEmail.setText("");
        jtxtUserName.setText("");

    }//GEN-LAST:event_jbtnResetActionPerformed

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed

       // DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
       // int selectedIndex = jTable1.getSelectedRow();

        Connection connection = DBconnection.connectToDatabase();

        if(connection != null){
            try {

                String Firstname = jtxtFirstName.getText();
                String Lastname = jtxtLastName.getText();
                String Email = jtxtEmail.getText();
                String Username = jtxtUserName.getText();
                String id = jtxtID.getText();

                PreparedStatement update = connection.prepareStatement("update users set fname = ?,lname = ?,email = ?,uname = ? where id = ?");

                update.setString(1,Firstname);
                update.setString(2,Lastname);
                update.setString(3,Email);
                update.setString(4,Username);
                update.setInt(5,Integer.parseInt(id));

                int i= update.executeUpdate();

                if(i >=1){
                    
                    JOptionPane.showMessageDialog(null,"Information has been Updated!");

                    show_Table(); // show the table after update.
                    jtxtID.setText("");
                    jtxtFirstName.setText("");
                    jtxtLastName.setText("");
                    jtxtEmail.setText("");
                    jtxtUserName.setText("");
                    jtxtFirstName.requestFocus();

                }else{
                    JOptionPane.showMessageDialog(null,"Update failed!");
                }

            } catch (SQLException ex){
                Logger.getLogger(ICalendarFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO DATABASE CONNECTION!");
        }
    }//GEN-LAST:event_jbtnUpdateActionPerformed

    private void jbtnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnUpdateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnUpdateMouseClicked

    private void jtxtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIDKeyReleased

    private void jtxtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIDActionPerformed

    private void jtxtLastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtLastNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtLastNameKeyReleased

    private void jtxtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtLastNameActionPerformed

    private void jtxtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtEmailKeyReleased

    private void jtxtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtEmailActionPerformed

    private void jtxtFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtFirstNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtFirstNameKeyReleased

    private void jtxtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtFirstNameActionPerformed

    private void jtxtUserNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtUserNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtUserNameKeyReleased

    private void jtxtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtUserNameActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();

        jtxtFirstName.setText(RecordTable.getValueAt(selectedIndex, 0).toString());
        jtxtLastName.setText(RecordTable.getValueAt(selectedIndex, 1).toString());
        jtxtEmail.setText(RecordTable.getValueAt(selectedIndex, 2).toString());
        jtxtUserName.setText(RecordTable.getValueAt(selectedIndex, 3).toString());
        jtxtID.setText(RecordTable.getValueAt(selectedIndex, 4).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ICalendarFrame cal = new ICalendarFrame();
        cal.setVisible(true);
    }//GEN-LAST:event_jbtnLogoutActionPerformed

    private JFrame frame;
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
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnLogout;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlFirstName;
    private javax.swing.JLabel jlID;
    private javax.swing.JLabel jlLastName;
    private javax.swing.JLabel jlUserName;
    private javax.swing.JPanel jpAdminPage;
    private javax.swing.JPanel jpButton;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtFirstName;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtLastName;
    private javax.swing.JTextField jtxtUserName;
    // End of variables declaration//GEN-END:variables


}
