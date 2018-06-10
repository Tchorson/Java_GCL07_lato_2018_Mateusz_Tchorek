package com.guiapptchorek;

/*
postulaty codda
informacyjny dane reprezentowane przez
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TreeMap;

public class GuiAppTchorek {

    private JPanel mainPanelBorderLayout;
    private JPanel GridLayout;
    private JTextField inputUsername;
    private JPasswordField inputPassword;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JPanel ButtonPanel;
    private JLabel usernameStatus;
    private JLabel passwordStatus;
    private JLabel appearIncorrectPass;
    private JPanel userInputArea;

    private String usernameInApp;
    private String passwordInApp;

    public GuiAppTchorek() {
        usernameStatus.setVisible(false);
        passwordStatus.setVisible(false);
        appearIncorrectPass.setVisible(false);

        userInputArea.setMaximumSize(userInputArea.getPreferredSize());


        usernameInApp = "";
        passwordInApp = "";

        TreeMap<String,String >userList = new TreeMap<String,String>();


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPassword.setText("");
                inputUsername.setText("");
                if(usernameStatus.isVisible() == true)
                    usernameStatus.setVisible(false);
                if(appearIncorrectPass.isVisible() == true)
                    passwordStatus.setVisible(false);
                if(appearIncorrectPass.isVisible() == true)
                    appearIncorrectPass.setVisible(false);
            }
        });

        inputUsername.addActionListener(new ActionListener() {
            @Override




            public void actionPerformed(ActionEvent e) {
                String textFieldValue = inputUsername.getText();

                if(textFieldValue.equals("")) {
                    inputUsername.setText("");
                    usernameStatus.setVisible(false);
                    inputUsername.requestFocus();
                    appearTopLabel();
                    return;
                }


                if(textFieldValue.length()<3)
                {
                    usernameStatus.setText("Username is too short!");
                    usernameStatus.setVisible(true);
                    inputUsername.requestFocus();
                }
                else if(textFieldValue.length()>20) {
                    usernameStatus.setText("username too long!");
                    usernameStatus.setVisible(true);
                    inputUsername.requestFocus();
                }
                else
                {


                    usernameInApp = textFieldValue;
                    usernameStatus.setText("");
                    usernameStatus.setVisible(false);

                }
                appearTopLabel();
            }
        });

        inputPassword.addActionListener(new ActionListener() {

            boolean hasSpecialSign, hasUpperCase;
            @Override
            public void actionPerformed(ActionEvent e) {
                hasSpecialSign = hasUpperCase = false;
                passwordStatus.setVisible(false);
                String passwrod = String.valueOf(inputPassword.getPassword());

                if(passwrod.equals("")) {
                    inputPassword.setText("");
                    passwordStatus.setVisible(false);
                    inputPassword.requestFocus();
                    appearTopLabel();
                    return;
                }


                char [] passwordArray = inputPassword.getPassword();

                if(passwordArray.length < 3){
                    passwordStatus.setText("Password is too short!");
                    passwordStatus.setVisible(true);
                    inputPassword.requestFocus();
                    appearTopLabel();
                    return;
                }
                if(passwordArray.length > 20){
                    passwordStatus.setText("Password is too long!");
                    passwordStatus.setVisible(true);
                    inputPassword.requestFocus();
                    appearTopLabel();
                    return;
                }

                for(int indexPassword = 0;indexPassword<passwordArray.length;indexPassword++){

                    int cint =(int) passwordArray[indexPassword];
                    if(cint <48 || (cint > 57 && cint < 65) || (cint > 90 && cint < 97) || cint > 122)hasSpecialSign= true;

                    if(Character.isUpperCase(passwordArray[indexPassword])) hasUpperCase = true;
                }//
                if(hasSpecialSign == true && hasUpperCase == true){
                    passwordInApp = passwrod;
                    passwordStatus.setText("");
                    passwordStatus.setVisible(false);

                }
                else if (hasSpecialSign == false) {
                    passwordStatus.setText("Password doesnt have special sign!");
                    passwordStatus.setVisible(true);
                    inputPassword.requestFocus();
                }
                else {
                    passwordStatus.setText("Doenst have upper letters!");
                    passwordStatus.setVisible(true);
                    inputPassword.requestFocus();
                }
                appearTopLabel();
            }
        });




        inputPassword.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                if(e.getKeyCode() == 38){
                    inputPassword.setText(passwordInApp);
                }
            }
        });

        inputUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == 38){
                    inputUsername.setText(usernameInApp);
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameInApp.equals("Admin") && passwordInApp.equals("Adm!n")){
                    JOptionPane.showMessageDialog(null,"You have sucessfully logined");

                    try {
                        SecondApp secondWindow = new SecondApp();

                    }catch(Exception secondWindowException){
                        secondWindowException.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong Username or Password");
            }
        });
    }

    private void appearTopLabel()
    {
        if(usernameStatus.isVisible()==true && passwordStatus.isVisible() == true ){
            appearIncorrectPass.setVisible(true);
            appearIncorrectPass.setText("Incorrect input: "+usernameLabel.getText()+" and "+passwordLabel.getText());
        }
        else if(usernameStatus.isVisible()==true){
            appearIncorrectPass.setVisible(true);
            appearIncorrectPass.setText("Incorrect input: "+usernameLabel.getText());
        }
         else if(passwordStatus.isVisible() == true){
            appearIncorrectPass.setVisible(true);
            appearIncorrectPass.setText("Incorrect input: "+passwordLabel.getText());
        }
        else appearIncorrectPass.setVisible(false);
    }


    private static void runProgram(){

        GuiAppTchorek mainApp = new GuiAppTchorek();
        JFrame frame = new JFrame("GuiAppTchorek");
        frame.setResizable(false);
        Container contentPane = mainApp.mainPanelBorderLayout;
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                runProgram();
            }
        });
    }

}
