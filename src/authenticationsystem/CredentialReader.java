package authenticationsystem;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CredentialReader {

    private String userName;
    private String passWord;
    private String scanLine;
    private boolean credCheck;
    private String userRole;
    private int scanLineCase;
        
    // constuctor method
    public CredentialReader() {  
        userName = "";
        passWord = "";
        scanLine = null;
        credCheck = false;
        userRole = "";
        scanLineCase = 0;
    }

    
    /* this method checks entered username and hashed password against
       a credential text file. returns true/false login validation and
       assigns a case number for associated user role */
    
    public boolean getLoginInfo(String inputName, String inputPass) 
            throws IOException, FileNotFoundException {

        userName = inputName;
        passWord = inputPass;
        
        // object creation for filereading functions
        FileReader readFile = null; 
        BufferedReader buffReader = null; 
        PassRemover removePass = new PassRemover();
        try {
            
            readFile = new FileReader("credentials.txt"); 
            buffReader = new BufferedReader(readFile);
            
            // reads each line until either login match or end of file
            while ((scanLine = buffReader.readLine()) != null) {
                scanLine = removePass.getNewScanLine(scanLine);
                if ((scanLine.contains(userName)) && 
                        (scanLine.contains(passWord))) {
                    credCheck = true;
                    break;
                }             }
        } catch (FileNotFoundException fileNotFound) { 
            System.out.println(fileNotFound);
        } catch (IOException iOException) {
            System.out.println(iOException);
        }

        /* if statements determine which role was 
           read with user login and assigns case number */
        if (scanLine == null) {
            scanLine = "";
        }
        if ((scanLine.contains("zookeeper"))) {
            scanLineCase = 1;
        }
        if ((scanLine.contains("admin"))) {
            scanLineCase = 2;
        }
        if ((scanLine.contains("veterinarian"))) {
            scanLineCase = 3;
        }
        
        return credCheck; // returns true/false to main
    }

    /* this method uses provided case number and opens appropriate role
       file, builds text into a string and then passes back to main to be
       displayed to the user */
    
    public String getRoleInfo() {
        switch (scanLineCase) {
            case 1: {
                // create file reading objects 
                FileReader readFile = null; 
                BufferedReader buffReader = null; 
                StringBuilder roleString = new StringBuilder();
                
                // try/catch to print error if file is not able to be opened
                try {
                    readFile = new FileReader("zookeeper.txt");
                    buffReader = new BufferedReader(readFile);
                    userRole = buffReader.readLine();
                    // loop to continue reading lines filling buffer
                    while (userRole != null) {
                        roleString.append(userRole + "\r\n");
                        userRole = buffReader.readLine();
                    }
                } catch (FileNotFoundException fileNotFound) {  
                    System.out.println(fileNotFound);
                } catch (IOException iOException) {
                    System.out.println(iOException);
                }
                // sending buffer to string for return
                userRole = roleString.toString();
                break;
            }

            case 2: {
                FileReader readFile = null; 
                BufferedReader buffReader = null; 
                StringBuilder roleString = new StringBuilder();
                try {
                    readFile = new FileReader("admin.txt");
                    buffReader = new BufferedReader(readFile);
                    userRole = buffReader.readLine();
                    while (userRole != null) {
                        roleString.append(userRole + "\r\n");
                        userRole = buffReader.readLine();
                    }
                } catch (FileNotFoundException fileNotFound) {  
                    System.out.println(fileNotFound);
                } catch (IOException iOException) {
                    System.out.println(iOException);
                }
                userRole = roleString.toString();
                break;
            }

            case 3: {
                FileReader readFile = null; 
                BufferedReader buffReader = null; 
                StringBuilder roleString = new StringBuilder();
                try {
                    readFile = new FileReader("veterinarian.txt");
                    buffReader = new BufferedReader(readFile);
                    userRole = buffReader.readLine();
                    // loop to continue reading lines filling buffer
                    while (userRole != null) {
                        roleString.append(userRole + "\r\n");
                        userRole = buffReader.readLine();
                    }
                } catch (FileNotFoundException fileNotFound) {  
                    System.out.println(fileNotFound);
                } catch (IOException iOException) {
                    System.out.println(iOException);
                }
                userRole = roleString.toString();
                break;
            }
            
        }
        return userRole;
    }
}
