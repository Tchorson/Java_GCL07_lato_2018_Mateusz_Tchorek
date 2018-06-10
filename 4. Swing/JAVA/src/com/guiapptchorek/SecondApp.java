package com.guiapptchorek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondApp {

    String[]comboBoxArray = {"5.0","4.5","4.0","3.5","3.0","2.0"};
    private JPanel secondAppMainPanel;
    private JButton removeSelectionButton;
    private JList nameList;
    private JList ageList;
    private JList markList;
    private JTextField inputAge;
    private JTextField inputName;
    private JComboBox gradeBox;
    private JButton addButton;
    private JPanel addingPanel;
    private JPanel jListPanel;
    private JPanel boxPanel;
    String tmpMark, tmpName, tmpAge;
    String [] tablicaName = new String[15];
    String [] tablicaAge = new String[15];
    String [] tablicaMark = new String[15];
    int counter  =1 ;



    SecondApp(){

        runProgram();

        gradeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox names = (JComboBox)e.getSource();
                tmpMark = (String)names.getSelectedItem();
                System.out.println(tmpMark);
            }
        });

        inputName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tmpName = inputName.getText();
                System.out.println(tmpName);
            }
        });
        inputAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tmpAge = inputAge.getText();
                System.out.println(tmpAge);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tablicaName[counter]= tmpName;
                tablicaAge[counter]=tmpAge;
                tablicaMark[counter]=tmpMark;
                counter++;
                //nameList.setVisible(true);
                nameList.setListData(tablicaName);
                ageList.setListData(tablicaAge);
                markList.setListData(tablicaMark);
            }
        });

        removeSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nameIndex = nameList.getSelectedIndex();
                int ageIndex = ageList.getSelectedIndex();
                int markIndex = markList.getSelectedIndex();

                tablicaMark[nameIndex]= null;
                tablicaAge[ageIndex]= null;
                tablicaName[markIndex]= null;

                nameList.setListData(tablicaName);
                ageList.setListData(tablicaAge);
                markList.setListData(tablicaMark);

            }
        });
    }
    private void runProgram() {

        tablicaAge[0]="Age";
        tablicaMark[0]="Mark";
        tablicaName[0]="Name";

        nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        markList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        nameList.setLayoutOrientation(JList.VERTICAL);
        ageList.setLayoutOrientation(JList.VERTICAL);
        markList.setLayoutOrientation(JList.VERTICAL);



        JFrame frameSecondApp = new JFrame("DataBase");
        frameSecondApp.setResizable(false);
        Container contentPane = this.secondAppMainPanel;
        frameSecondApp.setContentPane(contentPane);
        frameSecondApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSecondApp.setPreferredSize(new Dimension(600, 400));


        frameSecondApp.pack();
        frameSecondApp.setLocationRelativeTo(null);
        frameSecondApp.setVisible(true);
    }
}
