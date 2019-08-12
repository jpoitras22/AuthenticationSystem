package authenticationsystem;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AuthenticationSystem {

    public static void main(String[] args) throws IOException {

        String userName = "";  // username variable
        String userPass = "";  // password variable
        String hashedPass = ""; // hashed password variable
        boolean userCleared = false; // return credentials true/false
        int i = 0;
        Scanner scan = new Scanner(System.in);  // scan for input
        HashGenerator passHash = new HashGenerator(); // create MD5 hash object
        CredentialReader loginCheck = new CredentialReader();

        // loop to input login information up to 3 attempts
        for (i = 1; i < 4; ++i) {

            // creating frame and setting parameters
            JFrame frameMessage = new JFrame();
            frameMessage.setVisible(true);
            frameMessage.setLocation(900, 500);
            frameMessage.setAlwaysOnTop(true);
            

            // input dialog for username
            userName = JOptionPane.showInputDialog(frameMessage,
                    "Enter Username");
            frameMessage.dispose();
            if (userName == null) {
                System.exit(0);
            }
                        
            // input dialog for password
            userPass = JOptionPane.showInputDialog(frameMessage,
                    "Enter Password");
            frameMessage.dispose();
            if (userPass == null) {
                System.exit(0);
            }

            // hashed password return
            hashedPass = passHash.getMD5Hash(userPass);
            userCleared = loginCheck.getLoginInfo(userName, hashedPass);

            // login is successful, proceed to open role file
            if (userCleared) {
                String goodLogin = "Login Successful";
                JOptionPane.showMessageDialog(frameMessage, goodLogin);
                String[] button = {"Logout"};
                int logOut = JOptionPane.showOptionDialog(frameMessage,
                        loginCheck.getRoleInfo(), "Role Information",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, button, button[0]);

                /* loops to a logout confirmation window and back to role
                   information window if logout is NO*/ 
                if (logOut == 0) {
                    int areYouSure = JOptionPane.showOptionDialog(frameMessage,
                            "Are you sure you want to logout?", "Logout",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    while (areYouSure != JOptionPane.YES_OPTION) {
                        logOut = JOptionPane.showOptionDialog(frameMessage,
                                loginCheck.getRoleInfo(), "Role Information",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null, button, button[0]);
                        if (logOut == 0) {
                            areYouSure
                                    = JOptionPane.showOptionDialog(frameMessage,
                                            "Are you sure you want to logout?",
                                            "Logout", JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null, null, null);
                        }
                        else {
                            System.exit(0);
                        }
                    }
                    System.exit(0);
                }
                else {
                    System.exit(0);
                }

                break;
            } // message that login has been unsuccessful
            else {
                String badLogin = "Username or Password Incorrect";
                JOptionPane.showMessageDialog(frameMessage, badLogin,
                        "Username/Password Mismatch",
                        JOptionPane.ERROR_MESSAGE);
                frameMessage.dispose();
            }
        }

        // message that max login attempts have been reached
        if (!userCleared) {
            JFrame frameMessage = new JFrame();
            frameMessage.setVisible(true);
            frameMessage.setLocation(900, 500);
            frameMessage.setAlwaysOnTop(true);
            String warnMessage
                    = "Login Unsuccessful, Maxiumim Attempts Reached!!";
            JOptionPane.showMessageDialog(frameMessage, warnMessage,
                    "Login Error", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }

    }

}
