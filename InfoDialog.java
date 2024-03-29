
import java.awt.CardLayout;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 489516
 */
public class InfoDialog extends javax.swing.JDialog {
    
    private MyHashTable sharedHash;
    private int empNumSearch;

    /**
     * Creates new form InfoDialog
     */
    public InfoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        closeButton.requestFocusInWindow();
        searchField.setUI(new HintTextFieldUI("Search by employee number",true,Color.lightGray));
        CardLayout contextCard = (CardLayout)contextPanel.getLayout();
        contextCard.show(contextPanel, "hintErrorPanel");
    }
    
    public void setSharedHash(MyHashTable hashIn) {
        sharedHash = hashIn;
    }
    
    public void autoSearch(int empNumArg) {
        searchField.setText(String.valueOf(empNumArg));
        searchButton.doClick();
    }
    
    /*public void autoSearch(int empNumToSearch) {
        searchField.setText(String.valueOf(empNumToSearch));
        searchButton.doClick();
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        contextPanel = new javax.swing.JPanel();
        hintErrorPanel = new javax.swing.JPanel();
        hintErrorText = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        empNumLabel = new javax.swing.JLabel();
        empNumVal = new javax.swing.JLabel();
        empTypeTabel = new javax.swing.JLabel();
        empTypeVal = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameVal = new javax.swing.JLabel();
        dynamic1 = new javax.swing.JLabel();
        dynamic1Val = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        lastNameVal = new javax.swing.JLabel();
        dynamic2 = new javax.swing.JLabel();
        dynamic2Val = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        genderVal = new javax.swing.JLabel();
        dynamic3 = new javax.swing.JLabel();
        dynamic3Val = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        locationVal = new javax.swing.JLabel();
        dynamic4 = new javax.swing.JLabel();
        dynamic4Val = new javax.swing.JLabel();
        deductionRateLabel = new javax.swing.JLabel();
        deductionRateVal = new javax.swing.JLabel();
        dynamic5 = new javax.swing.JLabel();
        dynamic5Val = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.title")); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 350));

        searchField.setText(org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.searchField.text_1")); // NOI18N
        searchField.setMinimumSize(new java.awt.Dimension(295, 20));
        searchField.setPreferredSize(new java.awt.Dimension(295, 20));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldonTextFieldEnter(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(searchButton, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.searchButton.text_1")); // NOI18N
        searchButton.setMaximumSize(new java.awt.Dimension(75, 23));
        searchButton.setMinimumSize(new java.awt.Dimension(75, 23));
        searchButton.setPreferredSize(new java.awt.Dimension(75, 23));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtononSearchPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(closeButton, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.closeButton.text_1")); // NOI18N
        closeButton.setMaximumSize(new java.awt.Dimension(75, 23));
        closeButton.setMinimumSize(new java.awt.Dimension(75, 23));
        closeButton.setPreferredSize(new java.awt.Dimension(75, 23));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtononCancelPressed(evt);
            }
        });

        contextPanel.setPreferredSize(new java.awt.Dimension(100, 210));
        contextPanel.setLayout(new java.awt.CardLayout());

        hintErrorPanel.setName(""); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(hintErrorText, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.hintErrorText.text")); // NOI18N

        javax.swing.GroupLayout hintErrorPanelLayout = new javax.swing.GroupLayout(hintErrorPanel);
        hintErrorPanel.setLayout(hintErrorPanelLayout);
        hintErrorPanelLayout.setHorizontalGroup(
            hintErrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hintErrorPanelLayout.createSequentialGroup()
                .addComponent(hintErrorText)
                .addGap(0, 141, Short.MAX_VALUE))
        );
        hintErrorPanelLayout.setVerticalGroup(
            hintErrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hintErrorPanelLayout.createSequentialGroup()
                .addComponent(hintErrorText)
                .addGap(0, 196, Short.MAX_VALUE))
        );

        contextPanel.add(hintErrorPanel, "hintErrorPanel");

        infoPanel.setLayout(new java.awt.GridLayout(6, 4));

        org.openide.awt.Mnemonics.setLocalizedText(empNumLabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.empNumLabel.text")); // NOI18N
        infoPanel.add(empNumLabel);

        empNumVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(empNumVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.empNumVal.text")); // NOI18N
        infoPanel.add(empNumVal);

        org.openide.awt.Mnemonics.setLocalizedText(empTypeTabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.empTypeTabel.text")); // NOI18N
        infoPanel.add(empTypeTabel);

        empTypeVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(empTypeVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.empTypeVal.text")); // NOI18N
        infoPanel.add(empTypeVal);

        org.openide.awt.Mnemonics.setLocalizedText(firstNameLabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.firstNameLabel.text")); // NOI18N
        infoPanel.add(firstNameLabel);

        firstNameVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(firstNameVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.firstNameVal.text")); // NOI18N
        infoPanel.add(firstNameVal);

        org.openide.awt.Mnemonics.setLocalizedText(dynamic1, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic1.text")); // NOI18N
        infoPanel.add(dynamic1);

        dynamic1Val.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(dynamic1Val, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic1Val.text")); // NOI18N
        infoPanel.add(dynamic1Val);

        org.openide.awt.Mnemonics.setLocalizedText(lastNameLabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.lastNameLabel.text")); // NOI18N
        infoPanel.add(lastNameLabel);

        lastNameVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lastNameVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.lastNameVal.text")); // NOI18N
        infoPanel.add(lastNameVal);

        org.openide.awt.Mnemonics.setLocalizedText(dynamic2, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic2.text")); // NOI18N
        infoPanel.add(dynamic2);

        dynamic2Val.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(dynamic2Val, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic2Val.text")); // NOI18N
        infoPanel.add(dynamic2Val);

        org.openide.awt.Mnemonics.setLocalizedText(genderLabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.genderLabel.text")); // NOI18N
        infoPanel.add(genderLabel);

        genderVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(genderVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.genderVal.text")); // NOI18N
        infoPanel.add(genderVal);

        org.openide.awt.Mnemonics.setLocalizedText(dynamic3, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic3.text")); // NOI18N
        infoPanel.add(dynamic3);

        dynamic3Val.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(dynamic3Val, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic3Val.text")); // NOI18N
        infoPanel.add(dynamic3Val);

        org.openide.awt.Mnemonics.setLocalizedText(locationLabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.locationLabel.text")); // NOI18N
        infoPanel.add(locationLabel);

        locationVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(locationVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.locationVal.text")); // NOI18N
        infoPanel.add(locationVal);

        org.openide.awt.Mnemonics.setLocalizedText(dynamic4, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic4.text")); // NOI18N
        infoPanel.add(dynamic4);

        dynamic4Val.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(dynamic4Val, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic4Val.text")); // NOI18N
        infoPanel.add(dynamic4Val);

        org.openide.awt.Mnemonics.setLocalizedText(deductionRateLabel, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.deductionRateLabel.text")); // NOI18N
        infoPanel.add(deductionRateLabel);

        deductionRateVal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(deductionRateVal, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.deductionRateVal.text")); // NOI18N
        infoPanel.add(deductionRateVal);

        org.openide.awt.Mnemonics.setLocalizedText(dynamic5, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic5.text")); // NOI18N
        infoPanel.add(dynamic5);

        dynamic5Val.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(dynamic5Val, org.openide.util.NbBundle.getMessage(InfoDialog.class, "InfoDialog.dynamic5Val.text")); // NOI18N
        infoPanel.add(dynamic5Val);

        contextPanel.add(infoPanel, "infoPanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contextPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldonTextFieldEnter(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldonTextFieldEnter
        // TODO add your handling code here:
        searchButton.doClick();
    }//GEN-LAST:event_searchFieldonTextFieldEnter

    private void searchButtononSearchPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtononSearchPressed
        // TODO add your handling code here:
        // if num in hash, switch card layout to confirmation label
        CardLayout contextCard = (CardLayout)contextPanel.getLayout();
        try {
            empNumSearch = Integer.valueOf(searchField.getText());
        }
        catch (NumberFormatException ex) {
            empNumSearch = -1;
        }
        if (empNumSearch > 0) {
            if (sharedHash.isInTable(empNumSearch)) {
                contextCard.show(contextPanel, "infoPanel");
                EmployeeInfo queried = sharedHash.readFromTable(empNumSearch);
                
                empNumVal.setText(String.valueOf(queried.empNum));
                firstNameVal.setText(String.valueOf(queried.firstName));
                lastNameVal.setText(String.valueOf(queried.lastName));
                
                if (queried.gender == 0) {
                    genderVal.setText("Male");
                }
                else if (queried.gender == 1) {
                    genderVal.setText("Female");
                }
                else {
                    genderVal.setText("Other");
                }
                
                if (queried.workLocation == 0) {
                    locationVal.setText("Mississauga");
                }
                else if (queried.workLocation == 1) {
                    locationVal.setText("Ottawa");
                }
                else {
                    locationVal.setText("Chicago");
                }
                String dedRateToDisplay = String.format("%.0f",(queried.deductionsRate*100));
                deductionRateVal.setText(dedRateToDisplay+"%");
                
                if (queried instanceof PartTimeEmployee) {
                    empTypeVal.setText("Part Time");
                    dynamic1.setText("Hourly Wage:");
                    String wageToDisplay = String.format("%.2f",((PartTimeEmployee)queried).hourlyWage);
                    dynamic1Val.setText("$"+wageToDisplay);
                    dynamic2.setText("Hours Per Week:");
                    String hpwToDisplay = String.format("%.1f",((PartTimeEmployee)queried).hoursPerWeek);
                    dynamic2Val.setText(hpwToDisplay);
                    dynamic3.setText("Weeks Per Year:");
                    String wpyToDisplay = String.format("%.1f",((PartTimeEmployee)queried).weeksPerYear);
                    dynamic3Val.setText(wpyToDisplay);
                    dynamic4.setText("Gross Annual Income:");
                    String gaiToDisplay = String.format("%,.2f",((PartTimeEmployee)queried).calcAnnualGrossIncome());
                    dynamic4Val.setText("$"+gaiToDisplay);
                    dynamic5.setText("Net Annual Income:");
                    String naiToDisplay = String.format("%,.2f",((PartTimeEmployee)queried).calcAnnualNetIncome());
                    dynamic5Val.setText("$"+naiToDisplay);
                }
                else {
                    empTypeVal.setText("Full Time");
                    dynamic1.setText("Salary:");
                    String salToDisplay = String.format("%,.2f",((FullTimeEmployee)queried).salary);
                    dynamic1Val.setText("$"+salToDisplay);
                    dynamic2.setText("Gross Annual Income:");
                    String gaiToDisplay = String.format("%,.2f",((FullTimeEmployee)queried).calcAnnualGrossIncome());
                    dynamic2Val.setText("$"+gaiToDisplay);
                    dynamic3.setText("Net Annual Income:");
                    String naiToDisplay = String.format("%,.2f",((FullTimeEmployee)queried).calcAnnualNetIncome());
                    dynamic3Val.setText("$"+naiToDisplay);
                    dynamic4.setText(null);
                    dynamic4Val.setText(null);
                    dynamic5.setText(null);
                    dynamic5Val.setText(null);
                }
            }
            else {
                contextCard.show(contextPanel, "hintErrorPanel");
                hintErrorText.setText("No such employee found in the system.");
                
            }
        }
        else {
            contextCard.show(contextPanel, "hintErrorPanel");
            hintErrorText.setText("No such employee found in the system.");
        }  
    }//GEN-LAST:event_searchButtononSearchPressed

    private void closeButtononCancelPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtononCancelPressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtononCancelPressed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InfoDialog dialog = new InfoDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel contextPanel;
    private javax.swing.JLabel deductionRateLabel;
    private javax.swing.JLabel deductionRateVal;
    private javax.swing.JLabel dynamic1;
    private javax.swing.JLabel dynamic1Val;
    private javax.swing.JLabel dynamic2;
    private javax.swing.JLabel dynamic2Val;
    private javax.swing.JLabel dynamic3;
    private javax.swing.JLabel dynamic3Val;
    private javax.swing.JLabel dynamic4;
    private javax.swing.JLabel dynamic4Val;
    private javax.swing.JLabel dynamic5;
    private javax.swing.JLabel dynamic5Val;
    private javax.swing.JLabel empNumLabel;
    private javax.swing.JLabel empNumVal;
    private javax.swing.JLabel empTypeTabel;
    private javax.swing.JLabel empTypeVal;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel firstNameVal;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel genderVal;
    private javax.swing.JPanel hintErrorPanel;
    private javax.swing.JLabel hintErrorText;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lastNameVal;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel locationVal;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
