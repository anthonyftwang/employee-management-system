
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import java.nio.charset.Charset;
import java.nio.file.*;
import org.openide.util.Exceptions;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 489516
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    
    private javax.swing.JPopupMenu systemMenu;
    private javax.swing.JPopupMenu fileMenu;
    
    MyHashTable theHash;
    int k = 10; // tweak number of buckets in hash table here
    boolean unsavedChanges = false;
    
    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null); // positions window in center of screen
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // closes window manually after checking for unsaved changes
        getContentPane().requestFocusInWindow(); // removes default focus from any one component
        theHash = new MyHashTable(k);
        systemTable.getTableHeader().setReorderingAllowed(false); // disable drag and drop of table columns
        systemTable.setRowHeight(23); // width = 61 matches command button dimensions
        
        ImageIcon rawDropdownIcon = new ImageIcon(getClass().getResource("/dropdown.png")); // arrow icon for dropdown buttons
        ImageIcon dropdownIcon = (ImageIcon)resizeIcon(rawDropdownIcon,8,8);
        systemButton.setIcon(dropdownIcon);
        systemButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        fileButton.setIcon(dropdownIcon);
        fileButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        systemMenu = new javax.swing.JPopupMenu("System");
        
        // handles add action under system menu
        systemMenu.add(new JMenuItem(new AbstractAction("Add...    ") {
            public void actionPerformed(ActionEvent e) {
                AddDialog theAddDialog = new AddDialog(MainFrame.this, true);
                theAddDialog.setSharedHash(theHash);
                theAddDialog.setVisible(true);
                if (theAddDialog.getAddResult() != null) {
                    theHash.addToTable(theAddDialog.getAddResult());
                    populateSystemTable();
                    unsavedChanges = true;
                } 
            }
        }));
        
        // handles remove action under system menu
        systemMenu.add(new JMenuItem(new AbstractAction("Remove...    ") {
            public void actionPerformed(ActionEvent e) {
                RemoveDialog theRemoveDialog = new RemoveDialog(MainFrame.this, true);
                theRemoveDialog.setSharedHash(theHash);
                theRemoveDialog.setVisible(true);
                if (theRemoveDialog.getRemoveResult() != -1) {
                    theHash.removeFromTable(theRemoveDialog.getRemoveResult());
                    populateSystemTable();
                    unsavedChanges = true;
                }
            }
        }));
        
        // handles edit action under system menu
        systemMenu.add(new JMenuItem(new AbstractAction("Edit...    ") {
            public void actionPerformed(ActionEvent e) {
                EditDialog theEditDialog = new EditDialog(MainFrame.this, true);
                theEditDialog.setSharedHash(theHash);
                theEditDialog.setVisible(true);
                if (theEditDialog.getEditResult()[0] != null && theEditDialog.getEditResult()[1] != null) {
                    theHash.removeFromTable(theEditDialog.getEditResult()[0].getEmpNum());
                    theHash.addToTable(theEditDialog.getEditResult()[1]);
                    populateSystemTable();
                    unsavedChanges = true;
                }
            }
        }));
        
        // handles search action under system menu
        systemMenu.add(new JMenuItem(new AbstractAction("Search...    ") {
            public void actionPerformed(ActionEvent e) {
                InfoDialog theInfoDialog = new InfoDialog(MainFrame.this, true);
                theInfoDialog.setSharedHash(theHash);
                theInfoDialog.setVisible(true);
            }
        }));
        
        fileMenu = new javax.swing.JPopupMenu("File");
        
        // handles open action under file menu
        fileMenu.add(new JMenuItem(new AbstractAction("Open...    ") {
            public void actionPerformed(ActionEvent e) {
                
                boolean doOpen = false; // will only open if user confirms override of unsaved changes or changes do not exist
                boolean isCorrupted = false; // will abort load completely if file corrupted
                if (unsavedChanges == true) {
                    int closingConfirmResult = JOptionPane.showConfirmDialog(null, "You have unsaved changes. Are you sure you want to load a new file?", "Confirm Load", JOptionPane.YES_NO_OPTION);
                    if (closingConfirmResult == JOptionPane.YES_OPTION) {
                        doOpen = true;
                    }
                }
                else {
                    doOpen = true;
                }
                if (doOpen == true) {
                    theHash = new MyHashTable(k); // overwrites old data structure
                    ArrayList<EmployeeInfo> listEmpToLoad = new ArrayList<>(); // buffers archive entries into holder so that corrupted files don't load partially on abort
                    JFileChooser openChooser = new JFileChooser();
                    openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
                    openChooser.setDialogTitle("Open File");
                    FileNameExtensionFilter emsFilter = new FileNameExtensionFilter("EMS Files (*.ems)","ems"); // only show *.ems files and directories
                    openChooser.addChoosableFileFilter(emsFilter);
                    openChooser.setFileFilter(emsFilter);
                    openChooser.setAcceptAllFileFilterUsed(false); // only one file type allowed
                    int result = openChooser.showOpenDialog(MainFrame.this);
                    if (result == openChooser.APPROVE_OPTION) {
                        String filename = openChooser.getSelectedFile().toString();
                        Path file = Paths.get(filename);
                        try {
                            //read file, add to ArrayList which THEN gets added to hash IF file is NOT corrupted
                            List<String> lines = Files.readAllLines(file);
                            for (int i = 0; i < lines.size(); i++) {
                                String[] parsedLine = lines.get(i).split("#"); // parses info at delimiter
                                EmployeeInfo empToLoad;
                                int parsedEmpNum = Integer.valueOf(parsedLine[1]);
                                String parsedFirstName = parsedLine[2];
                                String parsedLastName = parsedLine[3];
                                int parsedGender = Integer.valueOf(parsedLine[4]);
                                int parsedLocation = Integer.valueOf(parsedLine[5]);
                                double parsedDeduction = Double.valueOf(parsedLine[6]);
                                if (parsedLine.length == 10) { // PartTimeEmployee has 10 saved attributes, FullTimeEmployee has 8
                                    double parsedWage = Double.valueOf(parsedLine[7]);
                                    double parsedHPW = Double.valueOf(parsedLine[8]);
                                    double parsedWPY = Double.valueOf(parsedLine[9]);
                                    empToLoad = new PartTimeEmployee(parsedEmpNum, parsedFirstName, parsedLastName, parsedGender, parsedLocation, parsedDeduction, parsedWage, parsedHPW, parsedWPY);
                                }
                                else {
                                    double parsedSalary = Double.valueOf(parsedLine[7]);
                                    empToLoad = new FullTimeEmployee(parsedEmpNum, parsedFirstName, parsedLastName, parsedGender, parsedLocation, parsedDeduction, parsedSalary);
                                }
                                listEmpToLoad.add(empToLoad);
                                //theHash.addToTable(empToLoad);
                            }
                            //populateSystemTable();
                            //unsavedChanges = false;

                        }
                        catch (IOException ex) {
                            isCorrupted = true;
                            JOptionPane.showMessageDialog(MainFrame.this, "Couldn't open corrupted file", "Employee Management System", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (NumberFormatException ex) {
                            isCorrupted = true;
                            JOptionPane.showMessageDialog(MainFrame.this, "Couldn't open corrupted file", "Employee Management System", JOptionPane.ERROR_MESSAGE);
                        }
                        if (isCorrupted == false) { // if all entries buffer correctly, then add to main hash table
                            for (int i = 0; i < listEmpToLoad.size(); i++) {
                                theHash.addToTable(listEmpToLoad.get(i));
                            }
                            populateSystemTable();
                            unsavedChanges = false;
                        }
                    }
                }
            }
        }));
        
        // handles save action under file menu
        fileMenu.add(new JMenuItem(new AbstractAction("Save as...    ") {
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser saveChooser = new JFileChooser();
                saveChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                saveChooser.setSelectedFile(new File("archive.ems")); // sets default/suggested filename
                saveChooser.setDialogTitle("Save File");

                FileNameExtensionFilter emsFilter = new FileNameExtensionFilter("EMS Files (*.ems)","ems"); // only allow saving to *.ems filetype
                saveChooser.addChoosableFileFilter(emsFilter);
                saveChooser.setFileFilter(emsFilter);
                saveChooser.setAcceptAllFileFilterUsed(false);

                int result = saveChooser.showSaveDialog(MainFrame.this);

                if (result == saveChooser.APPROVE_OPTION) {
                    
                    if (saveChooser.getSelectedFile().exists()) { // disallow overwriting of existing files
                        JOptionPane.showMessageDialog(MainFrame.this, "A file with that name already exists in this location", "Employee Management System", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String filename = saveChooser.getSelectedFile().toString();
                        if (!filename.endsWith(".ems"))
                        filename += ".ems"; // appends file extension if not already added

                        // iterate thru hash table, build a string from each entry, and add it to list to be buffered to archive
                        ArrayList<String> lines = new ArrayList<String>();
                        for (int i = 0; i < theHash.getBuckets().length; i++) {
                            for (int j = 0; j < (theHash.getBuckets()[i].size()); j++) {
                                String employeeLine;
                                if (theHash.getBuckets()[i].get(j) instanceof PartTimeEmployee) {
                                    employeeLine = "P" + "#" + (theHash.getBuckets()[i].get(j)).getEmpNum() + "#" + (theHash.getBuckets()[i].get(j)).firstName
                                            + "#" + (theHash.getBuckets()[i].get(j)).lastName + "#" + (theHash.getBuckets()[i].get(j)).gender + "#" + (theHash.getBuckets()[i].get(j)).workLocation
                                            + "#" + (theHash.getBuckets()[i].get(j)).deductionsRate + "#" + ((PartTimeEmployee)(theHash.getBuckets()[i].get(j))).hourlyWage
                                            + "#" + ((PartTimeEmployee)(theHash.getBuckets()[i].get(j))).hoursPerWeek + "#" + ((PartTimeEmployee)(theHash.getBuckets()[i].get(j))).weeksPerYear;
                                }
                                else {
                                    employeeLine = "F" + "#" + (theHash.getBuckets()[i].get(j)).getEmpNum() + "#" + (theHash.getBuckets()[i].get(j)).firstName
                                            + "#" + (theHash.getBuckets()[i].get(j)).lastName + "#" + (theHash.getBuckets()[i].get(j)).gender + "#" + (theHash.getBuckets()[i].get(j)).workLocation
                                            + "#" + (theHash.getBuckets()[i].get(j)).deductionsRate + "#" + ((FullTimeEmployee)(theHash.getBuckets()[i].get(j))).salary;
                                }
                                lines.add(employeeLine);
                            }
                        }
                        Path file = Paths.get(filename);
                        try {
                            Files.write(file, lines, Charset.forName("UTF-8")); // write buffered lines to new file
                        }
                        catch (IOException ex) {
                            //Exceptions.printStackTrace(ex);
                            JOptionPane.showMessageDialog(MainFrame.this, "Couldn't save file in that directory", "Employee Management System", JOptionPane.ERROR_MESSAGE);
                        }
                        unsavedChanges = false;
                    }
                }
            }
        }));
        
        populateSystemTable();
        
    }
    
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) { // helper method to resize all icons
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }
    
    // load quick-action button icons
    
    ImageIcon rawInfoIcon = new ImageIcon(getClass().getResource("/info.png"));
    ImageIcon rawEditIcon = new ImageIcon(getClass().getResource("/edit.png"));
    ImageIcon rawRemoveIcon = new ImageIcon(getClass().getResource("/remove.png"));
    
    ImageIcon infoIcon = (ImageIcon)resizeIcon(rawInfoIcon,16,16);
    ImageIcon editIcon = (ImageIcon)resizeIcon(rawEditIcon,16,16);
    ImageIcon removeIcon = (ImageIcon)resizeIcon(rawRemoveIcon,16,16);
    
    // popular method that rebuilds and refreshes the table; called on any changes to the hash table   
    private void populateSystemTable(){
        getContentPane().requestFocusInWindow();
        CardLayout card = (CardLayout)mainPanel.getLayout();
        if (theHash.getNumInHash() == 0) {
            card.show(mainPanel, "emptyPanel"); // disaply text hint if no employees in system
        }
        else {
            // map all applicable attributes for each item in hash table to systemTable
            ArrayList<Object[]> dataList = new ArrayList<Object[]>();
            for (int i = 0; i < theHash.getBuckets().length; i++) {
                for (int j = 0; j < theHash.getBuckets()[i].size(); j++) {
                    Object[] rowToAdd = {" "+Integer.toString((theHash.getBuckets()[i].get(j)).getEmpNum()), " "+(theHash.getBuckets()[i].get(j)).firstName, " "+(theHash.getBuckets()[i].get(j)).lastName, (theHash.getBuckets()[i].get(j)).gender, (theHash.getBuckets()[i].get(j)).workLocation, " "+String.format("%.0f",(theHash.getBuckets()[i].get(j)).deductionsRate*100)+"%", -1, infoIcon, editIcon, removeIcon};
                    // format attributes for user-facing display
                    if (theHash.getBuckets()[i].get(j) instanceof PartTimeEmployee) {
                        rowToAdd[0] = " P " + rowToAdd[0];
                        rowToAdd[6] = " $" + String.format("%,.2f",((PartTimeEmployee)(theHash.getBuckets()[i].get(j))).calcAnnualGrossIncome());
                    }
                    else {
                        rowToAdd[0] = " F " + rowToAdd[0];
                        rowToAdd[6] = " $" + String.format("%,.2f",((FullTimeEmployee)(theHash.getBuckets()[i].get(j))).calcAnnualGrossIncome());
                    }
                    switch (Integer.valueOf(rowToAdd[3].toString())) {
                        case 0:
                            rowToAdd[3] = " Male";
                            break;
                        case 1:
                            rowToAdd[3] = " Female";
                            break;
                        default:
                            rowToAdd[3] = " Other";
                            break;
                    }
                    switch (Integer.valueOf(rowToAdd[4].toString())) {
                        case 0:
                            rowToAdd[4] = " Mississauga";
                            break;
                        case 1:
                            rowToAdd[4] = " Ottawa";
                            break;
                        default:
                            rowToAdd[4] = " Chicago";
                            break;
                    }
                    dataList.add(rowToAdd);
                }
            }
            // define table
            String[] columns = {"Employee Number","First Name","Last Name","Gender","Location","Deduction Rate","Gross Annual Income","Info","Edit","Remove"};
            Object data[][] = new Object[0][0];
            data = dataList.toArray(data);

            DefaultTableModel model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) { // allows quick-action buttons to be selected, but forbids editing of other cells
                    if (column > 6) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            };
            systemTable.setModel(model);

            // handles info quick-action button
            Action contentInfo = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    String displayedEmpNum = String.valueOf(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));
                    int empNumArg = Integer.valueOf(displayedEmpNum.substring(4));
                    InfoDialog theInfoDialog = new InfoDialog(MainFrame.this, true);
                    theInfoDialog.setSharedHash(theHash);
                    theInfoDialog.autoSearch(empNumArg);
                    theInfoDialog.setVisible(true);
                }
            };

            // handles edit quick-action button
            Action contentEdit = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    String displayedEmpNum = String.valueOf(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));
                    int empNumArg = Integer.valueOf(displayedEmpNum.substring(4));
                    EditDialog theEditDialog = new EditDialog(MainFrame.this, true);
                    theEditDialog.setSharedHash(theHash);
                    theEditDialog.autoSearch(empNumArg);
                    theEditDialog.setVisible(true);
                    if (theEditDialog.getEditResult()[0] != null && theEditDialog.getEditResult()[1] != null) {
                        theHash.removeFromTable(theEditDialog.getEditResult()[0].getEmpNum());
                        theHash.addToTable(theEditDialog.getEditResult()[1]);
                        populateSystemTable();
                        unsavedChanges = true;
                    }
                }
            };

            // handles remove quick-action button
            Action contentRemove = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable)e.getSource();
                    int modelRow = Integer.valueOf(e.getActionCommand() );
                    String displayedEmpNum = String.valueOf(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));
                    int empNumToRemove = Integer.valueOf(displayedEmpNum.substring(4));

                    int removeConfirmResult = JOptionPane.showConfirmDialog(null,"Do you want to remove ["+theHash.readFromTable(empNumToRemove).firstName+" "+theHash.readFromTable(empNumToRemove).lastName+"] from the system?","Employee Management System",JOptionPane.YES_NO_OPTION);
                    if (removeConfirmResult == JOptionPane.YES_OPTION) {
                        theHash.removeFromTable(empNumToRemove);
                        populateSystemTable();
                        unsavedChanges = true;
                    }
                }
            };

            // resize cells containing buttons
            ButtonColumn buttonColumn7 = new ButtonColumn(systemTable, contentInfo, 7);
            buttonColumn7.setMnemonic(KeyEvent.VK_D);

            ButtonColumn buttonColumn8 = new ButtonColumn(systemTable, contentEdit, 8);
            buttonColumn8.setMnemonic(KeyEvent.VK_D);
            
            ButtonColumn buttonColumn9 = new ButtonColumn(systemTable, contentRemove, 9);
            buttonColumn9.setMnemonic(KeyEvent.VK_D);

            systemTable.getColumnModel().getColumn(0).setMinWidth(100);
            systemTable.getColumnModel().getColumn(1).setMinWidth(100);
            systemTable.getColumnModel().getColumn(2).setMinWidth(100);
            systemTable.getColumnModel().getColumn(3).setMinWidth(100);
            systemTable.getColumnModel().getColumn(4).setMinWidth(100);
            systemTable.getColumnModel().getColumn(5).setMinWidth(100);
            systemTable.getColumnModel().getColumn(6).setMinWidth(150);

            TableColumn targetColumn7 = systemTable.getColumnModel().getColumn(7);
            targetColumn7.setPreferredWidth(75);
            targetColumn7.setMaxWidth(75);
            targetColumn7.setMinWidth(75);

            TableColumn targetColumn8 = systemTable.getColumnModel().getColumn(8);
            targetColumn8.setPreferredWidth(75);
            targetColumn8.setMaxWidth(75);
            targetColumn8.setMinWidth(75);
            
            TableColumn targetColumn9 = systemTable.getColumnModel().getColumn(9);
            targetColumn9.setPreferredWidth(75);
            targetColumn9.setMaxWidth(75);
            targetColumn9.setMinWidth(75);
            
            card.show(mainPanel, "tablePanel");
            
            // sort table alphabetically, uses employee number column by default
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(systemTable.getModel());
            systemTable.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
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

        mainPanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        systemTable = new javax.swing.JTable();
        emptyPanel = new javax.swing.JPanel();
        emptyLabel = new javax.swing.JLabel();
        systemButton = new javax.swing.JButton();
        fileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Management System");
        setPreferredSize(new java.awt.Dimension(1050, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onWindowClosing(evt);
            }
        });

        mainPanel.setLayout(new java.awt.CardLayout());

        tablePanel.setName(""); // NOI18N

        systemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        systemTable.setRowSelectionAllowed(false);
        scrollPane.setViewportView(systemTable);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        mainPanel.add(tablePanel, "tablePanel");

        emptyPanel.setName(""); // NOI18N
        emptyPanel.setLayout(new java.awt.GridBagLayout());

        emptyLabel.setText("No employees in the system.");
        emptyPanel.add(emptyLabel, new java.awt.GridBagConstraints());

        mainPanel.add(emptyPanel, "emptyPanel");

        systemButton.setText("System");
        systemButton.setMaximumSize(new java.awt.Dimension(80, 23));
        systemButton.setMinimumSize(new java.awt.Dimension(80, 23));
        systemButton.setPreferredSize(new java.awt.Dimension(80, 23));
        systemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSystemPressed(evt);
            }
        });

        fileButton.setText("File");
        fileButton.setMaximumSize(new java.awt.Dimension(75, 23));
        fileButton.setMinimumSize(new java.awt.Dimension(75, 23));
        fileButton.setPreferredSize(new java.awt.Dimension(75, 23));
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFilePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(systemButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(systemButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSystemPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSystemPressed
        // TODO add your handling code here:
        systemMenu.show(systemButton, 0, systemButton.getHeight()); // expand system dropdown
    }//GEN-LAST:event_onSystemPressed

    private void onFilePressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFilePressed
        // TODO add your handling code here:
        fileMenu.show(fileButton, 0, fileButton.getHeight()); // expand file dropdown
    }//GEN-LAST:event_onFilePressed

    private void onWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowClosing
        // TODO add your handling code here:
        if (unsavedChanges == true) { // ask for confirmation before closing with unsaved changes
            int closingConfirmResult = JOptionPane.showConfirmDialog(null, "You have unsaved changes. Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (closingConfirmResult == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        else {
            System.exit(0);
        }
    }//GEN-LAST:event_onWindowClosing

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
            // sets UI theme to system default (i.e. Windows instead of Netbeans/Java theme)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emptyLabel;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JButton fileButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton systemButton;
    private javax.swing.JTable systemTable;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}
