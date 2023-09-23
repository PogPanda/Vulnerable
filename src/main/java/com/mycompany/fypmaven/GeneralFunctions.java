package com.mycompany.fypmaven;

import static com.mycompany.fypmaven.DB_Connection.getConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GeneralFunctions {

    public static boolean checkDuplicate(Object[] params, String query, Object[] excludedValues) {

//        Edit the query so that it excludes the current description that is being edited
        if (excludedValues != null && excludedValues.length > 0) {
            query += " AND Description NOT IN (";
            for (int i = 0; i < excludedValues.length; i++) {
                if (i > 0) {
                    query += ",";
                }
                query += "?";
            }
            query += ")";
        }
        
        ResultSet resultSet;
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                int paramIndex = 1; // Start setting parameters at index 1

                // Set the parameters from params[]
                for (Object param : params) {
                    preparedStatement.setObject(paramIndex++, param);
                }

                if (excludedValues != null && excludedValues.length > 0) {
                    // Set the parameters from excludedValues[]
                    for (Object excludedValue : excludedValues) {
                        preparedStatement.setObject(paramIndex++, excludedValue);
                    }
                }
                
                resultSet = preparedStatement.executeQuery();

                System.out.println(preparedStatement);
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkDuplicate(Object[] params, String query) {

        ResultSet resultSet;
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    return true;

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Account> checkPasswords(String userID) throws SQLException {

//        Create a list to store matched accounts
        List<Account> matchedAccounts = new ArrayList<>();

//        Define the directory where the executable will be copied 
        File tempDir = new File(System.getProperty("java.io.tmpdir"));

        try {

            DB_Connection connection = new DB_Connection();

            connection.getConnection();

            String query = "Select * From accounts where user_ID = ? ";
            Object[] param = {userID};
            List<Account> accountList = GeneralFunctions.GetAll(query, param);

            for (Account account : accountList) {
                String password = account.getPassword();

                // Define the name of the executable
                String exeFileName = "check_password.exe";

                // Get the URL of the executable file from resources
                InputStream inputStream = GeneralFunctions.class.getResourceAsStream("/Scripts/check_password.exe");
                File exeFile = new File(tempDir, exeFileName);

                // Copy the executable file from resources to a temporary file
                try (InputStream exeInputStream = inputStream; FileOutputStream fileOutputStream = new FileOutputStream(exeFile)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = exeInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                }

                // Create a process builder for the executable
                ProcessBuilder processBuilder = new ProcessBuilder(exeFile.getAbsolutePath());

                // Start the process
                Process process = processBuilder.start();

                // Get the output stream of the process
                InputStream processInputStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(processInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                // Send the password to the executable
                process.getOutputStream().write((password + "\n").getBytes());
                process.getOutputStream().flush();

                // Read the executable's output
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    output.append(line).append("\n");

                    // Check if the line contains progress information
                }

                // Wait for the executable to complete
                int exitCode = process.waitFor();

                // Check the exit code
                if (exitCode == 0) {
                    System.out.println("Executable executed successfully.");
                } else {
                    System.err.println("Error: Executable execution failed with exit code " + exitCode);
                }

                // Check the script's output
                int scriptOutput = Integer.parseInt(output.toString().trim());

                if (scriptOutput > 1) {

                    System.out.println(scriptOutput);
                    matchedAccounts.add(account);
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return matchedAccounts;
    }

    public static void showMatchingAccounts(List<Account> matchingAccounts) {
        if (!matchingAccounts.isEmpty()) {
            StringBuilder message = new StringBuilder("These accounts are at risk. Please change their passwords as soon as possible. :\n\n");
            message.append("Accounts: \n");
            for (Account account : matchingAccounts) {
                message.append("- ").append(account.getDescription()).append("\n");

            }

            JOptionPane.showMessageDialog(
                    null,
                    message.toString(),
                    "The account(s) shown are at risk of being breached, please change them as soon as possible",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public static List<String> PopulateList(String query, Object[] param) {

//      Setting of required variable
        List<String> descriptionsList = new ArrayList<String>();

//      Getting the results and putting it into a result set
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < param.length; i++) {
                    statement.setObject(i + 1, param[i]);
                }

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String description = resultSet.getString("Description");
                    descriptionsList.add(description);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return descriptionsList;
    }

    public static List<Account> GetAll(String query, Object[] param) {
        List<Account> accountList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < param.length; i++) {
                    statement.setObject(i + 1, param[i]);
                }
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    // Retrieve data from the result set for each row
                    String description = resultSet.getString("Description");
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    String url = resultSet.getString("URL");
                    String userid = resultSet.getString("user_id");
                    // Create an Account object and add it to the list
                    Account account = new Account(description, username, password, url, userid);
                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void showNotification(String message, String title) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
