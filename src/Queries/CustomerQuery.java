package Queries;

import Helper.JavaDatabaseConnection;
import Models.CustomerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery {
    public static ObservableList<CustomerModel> obtainAllCustomers(){
        ObservableList<CustomerModel> allCustomerList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int customerId = resultSet.getInt("Customer_ID");
                String customer_Name = resultSet.getString("Customer_Name");
                String customer_Address = resultSet.getString("Address");
                String customer_PostalCode = resultSet.getString("Postal_Code");
                String customer_PhoneNumber = resultSet.getString("Phone");
                int customer_StateProvinceId = resultSet.getInt("Division_ID");
                int customer_Country_Id = resultSet.getInt("Country_ID");
                String customer_StateProvinceName = resultSet.getString("Division");
                String customer_CountryName = resultSet.getString("Country");

                CustomerModel customerModel = new CustomerModel(
                        customerId,
                        customer_Name,
                        customer_Address,
                        customer_PostalCode,
                        customer_PhoneNumber,
                        customer_StateProvinceId,
                        customer_Country_Id,
                        customer_StateProvinceName,
                        customer_CountryName
                );
                allCustomerList.add(customerModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain all customers (SQL Error)");
        }
        return allCustomerList;
    }

    public static void createNewCustomer(String customer_Name, String customer_Address, String customer_PostalCode, String customer_PhoneNumber, int customer_StateProvinceId) throws SQLException{
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, customer_Name);
            preparedStatement.setString(2, customer_Address);
            preparedStatement.setString(3, customer_PostalCode);
            preparedStatement.setString(4, customer_PhoneNumber);
            preparedStatement.setInt(5, customer_StateProvinceId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Unable to create new customer (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
    }

    public static void modifyExistingCustomer(int customerId, String customer_Name, String customer_Address, String customer_PostalCode, String customer_PhoneNumber, int customer_StateProvinceId) throws SQLException{
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, customer_Name);
            preparedStatement.setString(2, customer_Address);
            preparedStatement.setString(3, customer_PostalCode);
            preparedStatement.setString(4, customer_PhoneNumber);
            preparedStatement.setInt(5, customer_StateProvinceId);
            preparedStatement.setInt(6, customerId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Unable to modify existing customer (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
    }
}
