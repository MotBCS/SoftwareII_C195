package Queries;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentQuery {


    /** - GET ALL APPOINTMENTS -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel> obtainAllAppointments(){
        ObservableList<AppointmentModel> allAppList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY appointments.Appointment_ID;";
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
                allAppList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain all appointments from database (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return allAppList;
    }

    /** - CREATE APPOINTMENT -------------------------------------------------------------------------------------*/

    public static void createNewAppointment(String appTitle, String appDescription, int appContact, String appType, LocalDateTime appStart, LocalDateTime appEnd, int appCustomerId, int appUserId, String appLocation) throws SQLException{
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "INSERT INTO appointments (Title, Description, Contact_ID, Type, Start, End, Customer_ID, User_ID, Location) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, appTitle);
            preparedStatement.setString(2, appDescription);
            preparedStatement.setInt(3, appContact);
            preparedStatement.setString(4, appType);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(appStart));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(appEnd));
            preparedStatement.setInt(7, appCustomerId);
            preparedStatement.setInt(8, appUserId);
            preparedStatement.setString(9, appLocation);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Unable to create appointment (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
    }
    /** - MODIFY APPOINTMENT -------------------------------------------------------------------------------------*/

    public static void modifyExistingAppointment(int appId, String appTitle, String appDescription, int appContact, String appType, LocalDateTime appStart, LocalDateTime appEnd, int appCustomerId, int appUserId, String appLocation ){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "UPDATE appointments SET Title = ?," +
                    "Description = ?," +
                    "Contact_ID = ?," +
                    "Type = ?," +
                    "Start = ?," +
                    "End = ?," +
                    "Customer_ID = ?," +
                    "User_ID = ?," +
                    "Location = ?" +
                    "WHERE Appointment_ID = ?";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, appTitle);
            preparedStatement.setString(2, appDescription);
            preparedStatement.setInt(3, appContact);
            preparedStatement.setString(4, appType);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(appStart));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(appEnd));
            preparedStatement.setInt(7, appCustomerId);
            preparedStatement.setInt(8, appUserId);
            preparedStatement.setString(9, appLocation);
            preparedStatement.setInt(10, appId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Unable to modify existing appointment (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
    }

    /** - DELETE APPOINTMENT -------------------------------------------------------------------------------------*/

    public static void deleteExistingAppointment(int appId){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "DELETE FROM appointments WHERE Appointment_ID=?;";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, appId);
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println("Unable to delete existing appointment (SQL Error)");
        }
    }
    /** - GET APPOINTMENT BY MONTH -------------------------------------------------------------------------------------*/
    public static ObservableList<AppointmentModel>appByCurrentMonth(){
        ObservableList<AppointmentModel> appMonthList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Appointment_ID, Customer_ID, Title, Description, Location, Type, User_ID, Contact_ID, Start, End FROM appointments WHERE MONTH(Start)=MONTH(NOW())";
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
                //String appointmentContactName = resultSet.getString("Contact_Name");
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
                        appLocation
                        //appointmentContactName
                );
                appMonthList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain appointment by current month");
        }
        return appMonthList;
    }

    /** - GET APPOINTMENT BY WEEK -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel>appByCurrentWeek(){
        ObservableList<AppointmentModel> appWeekList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Appointment_ID, Customer_ID, Title, Description, Location, Type, User_ID, Contact_ID, Start, End FROM appointments WHERE Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 7 DAY);";
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
                //String appointmentContactName = resultSet.getString("Contact_Name");
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
                        appLocation
                        //appointmentContactName
                );
                appWeekList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain appointment by current week");
        }
        return appWeekList;
    }

    /** - GET USER APPOINTMENT (BY -> userId )-------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel>appByUserID(int userId){
        ObservableList<AppointmentModel> appByUserIdList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT * FROM appointments WHERE User_ID = '" + userId + "'";
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
                        appLocation
                );
                appByUserIdList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain appointment by current month");
        }
        return appByUserIdList;
    }
    /** - GET CONTACT APPOINTMENT (BY -> contactId )-------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel>appByContactId(int contactId){
        ObservableList<AppointmentModel> appByContactIdList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT * FROM appointments" +
                    "JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE" +
                    "appointments.Contact_ID = '" + contactId+ "'";
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
                appByContactIdList.add(appointmentModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain appointment by current month");
        }
        return appByContactIdList;
    }

    /** - Clashing Appointments -------------------------------------------------------------------------------------*/

    public static boolean clashingAppointments(int customerId, LocalDateTime appStart, LocalDateTime appEnd){
        ObservableList<AppointmentModel> clashingAppsList = AppointmentQuery.obtainAllAppointments();
        LocalDateTime ldtAppStart; //Check app start
        LocalDateTime ldtAppEnd; //Check app end
        for (AppointmentModel appointmentModel : clashingAppsList){
            ldtAppStart = appointmentModel.getAppStart();
            ldtAppEnd = appointmentModel.getAppEnd();
            if (customerId != appointmentModel.getAppCustomerId()){
                continue;
            }
            else if (ldtAppStart.isEqual(appStart) || ldtAppEnd.isEqual(appEnd)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Clashing Time Conflict");
                alert.setContentText("Appointment has same start and/or end as an already existing appointment");
                alert.showAndWait();
                return true;
            }
            else if (appStart.isAfter(ldtAppStart) && appStart.isBefore(appEnd)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Clashing Time Conflict");
                alert.setContentText("Appointment starts during already existing appointment");
                alert.showAndWait();
                return true;
            }
            else if (appEnd.isAfter(ldtAppStart) && appEnd.isBefore(ldtAppEnd)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Clashing Time Conflict");
                alert.setContentText("Appointment ends during already existing appointment");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }
}
