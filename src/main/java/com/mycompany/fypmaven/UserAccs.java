package com.mycompany.fypmaven;

import static com.mycompany.fypmaven.DB_Connection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserAccs {
    
    private String ID;
    private String Username;
    private String Password;
    private String RecoveryPhrase;
    
    public UserAccs(String Username, String Password){
        
    this.Username = Username;
    this.Password = Password; 
        
    }
    
   public String getUserID(){
       
       return ID;
   }
    public String getUsername() {

        return Username;
    }

    public String getPassword(){
        
        return Password;
    }
    
    public String setRecoveryPhrase (){
        
        return RecoveryPhrase;
    }
    
    public void setUsername(String Username){
        
        this.Username = Username;
    }
    
    public void setPassword (String Password){
        
        this.Password = Password; 
    }
    
    public void setRecoveryPhrase (String Recovery){
        
        this.RecoveryPhrase = Recovery;
    }
    
    
//    Function to login to the database
    public boolean Login() {
        
//        The login query 
        String query = "SELECT * FROM user_accounts WHERE Name = ? AND Password = ?";
        
//        Checking if there is a match in the database 
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                
                preparedStatement.setString(1, this.Username);
                preparedStatement.setString(2, this.Password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    
                    while(resultSet.next()){
                         this.ID = resultSet.getString("User_ID");
                          return true; // If a matching user is found
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
