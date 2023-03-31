package Queries;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentQuery {


    /** - GET ALL APPOINTMENTS -------------------------------------------------------------------------------------*/

    public static ObservableList<AppointmentModel> obtainAllAppointments(){
        ObservableList<AppointmentModel> allAppList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM appointments";
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
        }
        return allAppList;
    }

    /** - CREATE APPOINTMENT -------------------------------------------------------------------------------------*/

    public static void createNewAppointment(String appTitle, String appDescription, int appContact, String appType, LocalDateTime appStart, LocalDateTime appEnd, int appCustomerId, int appUserId, String appLocation, ){}
}
