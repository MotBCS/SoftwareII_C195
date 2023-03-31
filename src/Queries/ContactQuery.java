package Queries;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import Models.ContactModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContactQuery {

    /** - GET ALL CONTACTS  -------------------------------------------------------------------------------------*/

    public static ObservableList<ContactModel> obtainAllContacts(){
        ObservableList<ContactModel> allContactList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int contactId = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");
                ContactModel contactModel = new ContactModel(
                        contactId,
                        contactName
                );
                allContactList.add(contactModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain all contacts from database (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return allContactList;
    }

    /** - CONTACT REPORT  -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel> contactReport(){
        ObservableList<AppointmentModel> contactReport = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Appointment_ID, Customer_ID, Title, Description, Location, Type, User_ID, Contact_ID, Start, End FROM appointments ORDER BY Appointment_ID ASC";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int appId = resultSet.getInt("Appointment_ID");
                String appTitle = resultSet.getString("Title");
                String appDescription = resultSet.getString("Description");
                int appContact = resultSet.getInt("Contact_ID");
                String appType = resultSet.getString("Type");
                LocalDateTime appStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int appCustomerId = resultSet.getInt("Customer_ID");
                int appUserId = resultSet.getInt("User_ID");
                String appLocation = resultSet.getString("Location");
                String appointmentContactName = resultSet.getString("Contact_Name");

                AppointmentModel appointmentModel = new AppointmentModel(
                        appId,
                        appTitle,
                        appDescription,
                        appContact,
                        appType,
                        appStart,
                        appEnd,
                        appCustomerId,
                        appUserId,
                        appLocation,
                        appointmentContactName
                );
                contactReport.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain contact report (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return contactReport;
    }

    /** - GET CONTACTS BY ID -------------------------------------------------------------------------------------*/

    public static ContactModel contactById(int contactId){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, contactId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                int contact_Id = resultSet.getInt("Contact_ID"); //Search contact
                String contactName = resultSet.getString("Contact_Name");
                ContactModel contactModel = new ContactModel(
                        contact_Id,
                        contactName
                );
                return contactModel;
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain contact by contact Id (SQL Error)");
        }
        return null;
    }

    /** - FILTER APPOINTMENT BY CONTACT ID  -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel> contactReportByContactID1(){
        ObservableList<AppointmentModel> contact_1_ReportList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Appointment_ID, Customer_ID, Title, Description, Location, Type, User_ID, Contact_ID, Start, End FROM appointments WHERE Contact_ID = 1 ORDER BY Appointment_ID ASC;";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int appId = resultSet.getInt("Appointment_ID");
                String appTitle = resultSet.getString("Title");
                String appDescription = resultSet.getString("Description");
                int appContact = resultSet.getInt("Contact_ID");
                String appType = resultSet.getString("Type");
                LocalDateTime appStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int appCustomerId = resultSet.getInt("Customer_ID");
                int appUserId = resultSet.getInt("User_ID");
                String appLocation = resultSet.getString("Location");
                String appointmentContactName = resultSet.getString("Contact_Name");

                AppointmentModel appointmentModel = new AppointmentModel(
                        appId,
                        appTitle,
                        appDescription,
                        appContact,
                        appType,
                        appStart,
                        appEnd,
                        appCustomerId,
                        appUserId,
                        appLocation,
                        appointmentContactName
                );
                contact_1_ReportList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain filter appointments by contact Id 1 (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return contact_1_ReportList;
    }

    /** - FILTER APPOINTMENT BY CONTACT ID  -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel> contactReportByContactID2(){
        ObservableList<AppointmentModel> contact_2_ReportList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Appointment_ID, Customer_ID, Title, Description, Location, Type, User_ID, Contact_ID, Start, End FROM appointments WHERE Contact_ID = 2 ORDER BY Appointment_ID ASC;";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int appId = resultSet.getInt("Appointment_ID");
                String appTitle = resultSet.getString("Title");
                String appDescription = resultSet.getString("Description");
                int appContact = resultSet.getInt("Contact_ID");
                String appType = resultSet.getString("Type");
                LocalDateTime appStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int appCustomerId = resultSet.getInt("Customer_ID");
                int appUserId = resultSet.getInt("User_ID");
                String appLocation = resultSet.getString("Location");
                String appointmentContactName = resultSet.getString("Contact_Name");

                AppointmentModel appointmentModel = new AppointmentModel(
                        appId,
                        appTitle,
                        appDescription,
                        appContact,
                        appType,
                        appStart,
                        appEnd,
                        appCustomerId,
                        appUserId,
                        appLocation,
                        appointmentContactName
                );
                contact_2_ReportList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain filter appointments by contact Id 2 (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return contact_2_ReportList;
    }

    /** - FILTER APPOINTMENT BY CONTACT ID  -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel> contactReportByContactID3(){
        ObservableList<AppointmentModel> contact_3_ReportList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Appointment_ID, Customer_ID, Title, Description, Location, Type, User_ID, Contact_ID, Start, End FROM appointments WHERE Contact_ID = 3 ORDER BY Appointment_ID ASC;";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int appId = resultSet.getInt("Appointment_ID");
                String appTitle = resultSet.getString("Title");
                String appDescription = resultSet.getString("Description");
                int appContact = resultSet.getInt("Contact_ID");
                String appType = resultSet.getString("Type");
                LocalDateTime appStart = resultSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEnd = resultSet.getTimestamp("End").toLocalDateTime();
                int appCustomerId = resultSet.getInt("Customer_ID");
                int appUserId = resultSet.getInt("User_ID");
                String appLocation = resultSet.getString("Location");
                String appointmentContactName = resultSet.getString("Contact_Name");

                AppointmentModel appointmentModel = new AppointmentModel(
                        appId,
                        appTitle,
                        appDescription,
                        appContact,
                        appType,
                        appStart,
                        appEnd,
                        appCustomerId,
                        appUserId,
                        appLocation,
                        appointmentContactName
                );
                contact_3_ReportList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain filter appointments by contact Id 3 (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return contact_3_ReportList;
    }

}
