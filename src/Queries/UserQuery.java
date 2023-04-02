package Queries;

import Helper.JavaDatabaseConnection;
import Models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {

    public static boolean checkUserLogin(String username, String password){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT User_Name, Password FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return resultSet.next();
        } catch (SQLException exception) {
            System.out.print("Unable to get user login information");
            return false;
        }
        finally {
            JavaDatabaseConnection.closeConnection();
        }
    }

    public static ObservableList<UserModel>obtainAllUsers(){
        ObservableList<UserModel> allUsersList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT * FROM users";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt("User_ID");
                String username = resultSet.getString("User_Name");
                UserModel userModel = new UserModel(userId, username);
                allUsersList.add(userModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain all users (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return allUsersList;
    }

    public static int obtainUserById(String username) throws SQLException{
        int userId = 0;
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT User_ID, User_Name FROM users WHERE User_Name = '" + username +"'";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userId = resultSet.getInt("User_ID");
                username = resultSet.getString("User_Name");
            }
        } catch (SQLException exception) {
            System.out.println("Unable to get user by user Id (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return userId;
    }

    public static UserModel obtainUsernameById(int userId){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT User_ID, User_Name FROM users WHERE User_ID = ?";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                int user_Id = resultSet.getInt("User_ID");
                String username = resultSet.getString("User_Name");
                UserModel userModel = new UserModel(
                        user_Id,
                        username
                );
                return userModel;
            }
        } catch (SQLException exception) {
            System.out.println("Unable to get username from user Id (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return null;
    }
}
