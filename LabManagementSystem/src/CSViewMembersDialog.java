/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.*;               // For Swing components like JFrame, JTable, JComboBox, etc.
import javax.swing.table.DefaultTableModel;  // For the table model
import java.awt.event.*;            // For ActionListener and event handling
import java.sql.*;
/**
 *
 * @author Pc Occas
 */
public class CSViewMembersDialog extends JDialog {
     private Integer groupId;
    private String groupName;
     // Variable to store the group name

    // Constructor accepting parent, modal, and groupName
    public CSViewMembersDialog(JFrame parent, boolean modal, Integer groupId, String groupName) {
        super(parent, modal);
        initComponents();
        this.groupId = groupId;  // Initialize groupId
        this.groupName = groupName;  // Initialize groupName
        setTitle("View Members - " + groupName);  // Set the title with groupName
        loadGroupMembers();  // Load members for this group
    }
    


    // Your other methods, like loadMembers(), etc.




     // Load members of the selected group into the table
private void loadGroupMembers() {
    DefaultTableModel model = (DefaultTableModel) membersTable.getModel();
    model.setRowCount(0); // Clear the table

    try (Connection con = DBconnexion.ConnectDB()) {
        // Updated query to select all necessary columns
        String sql = "SELECT p.id, p.name, p.email, p.phone, p.category "
                   + "FROM csperson p "
                   + "JOIN csgroup_members gm ON p.id = gm.person_id "
                   + "WHERE gm.group_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, this.groupId); // Use the passed groupId
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int personId = rs.getInt("id");
            String personName = rs.getString("name");
            String personEmail = rs.getString("email");
            String personPhone = rs.getString("phone");
            String personCategory = rs.getString("category");
            
            // Add all columns to the table model
            model.addRow(new Object[]{personId, personName, personEmail, personPhone, personCategory});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading group members.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        membersTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        membersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Email", "Phone", "Category"
            }
        ));
        jScrollPane1.setViewportView(membersTable);

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("Group's member");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(41, 41, 41)))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(137, 137, 137))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 // Get selected row in the members table (person to remove from the group)
int selectedRow = membersTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a person to remove.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Get the personId from the first column of the selected row
    // Assuming the table has "personId" in the first column (index 0)
    int personId = (int) membersTable.getValueAt(selectedRow, 0); 

    // Now remove the relation from the group_members table using the person's ID
    try (Connection con = DBconnexion.ConnectDB()) {
        String deleteRelationSQL = "DELETE FROM csgroup_members WHERE group_id = ? AND person_id = ?";
        PreparedStatement ps = con.prepareStatement(deleteRelationSQL);
        ps.setInt(1, this.groupId); // Use the groupId of the current group
        ps.setInt(2, personId); // Use the selected person's ID

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Member removed from the group successfully.");
            loadGroupMembers(); // Refresh the members table to reflect changes
        } else {
            JOptionPane.showMessageDialog(this, "No matching relation found in group_members.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error removing member from the group.", "Error", JOptionPane.ERROR_MESSAGE);
    }



            // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CSSelectPersonDialog selectPersonDialog = new CSSelectPersonDialog(this, true, groupId);
        selectPersonDialog.setVisible(true);
         loadGroupMembers();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(CSViewMembersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(CSViewMembersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(CSViewMembersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(CSViewMembersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the dialog */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Sample groupId and groupName for testing
            Integer groupId = 1; // Replace with actual groupId as required
            String groupName = "Example Group"; // Replace with actual groupName as required

            CSViewMembersDialog dialog = new CSViewMembersDialog(new javax.swing.JFrame(), true, groupId, groupName);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        }
    });
}

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable membersTable;
    // End of variables declaration//GEN-END:variables
}
