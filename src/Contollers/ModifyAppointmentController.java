package Contollers;

import Models.AppointmentModel;
import Models.ContactModel;
import Models.CustomerModel;
import Models.UserModel;
import Queries.AppointmentQuery;
import Queries.ContactQuery;
import Queries.CustomerQuery;
import Queries.UserQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** ----------------------------------------------------------------------------------------------------------------- */

/**
 * This class is the controller for the modify appointment screen.
 * Allows user to modify a existing appointment then save it to
 * the appointment table
 *
 * */
public class ModifyAppointmentController implements Initializable {

    /** Buttons, Text Fields, Combo Boxes and Date Pickers */
    @FXML
    public ComboBox<CustomerModel>modifyAppointmentCustomerIDComboBox;
    @FXML
    public ComboBox<UserModel>modifyAppointmentUserIDComboBox;
    @FXML
    public DatePicker modifyAppointmentDatePicker_End;
    @FXML
    public TextField modifyAppointmentIDTextField;
    @FXML
    public TextField modifyAppointmentTitleTextField;
    @FXML
    public TextField ModifyAppointmentTypeTextField;
    @FXML
    public TextField ModifyLocationTextField;
    @FXML
    public ComboBox<LocalTime>ModifyAppointmentStartTimeComboBox;
    @FXML
    public ComboBox<LocalTime>ModifyAppointmentEndTimeComboBox;
    @FXML
    public ComboBox<ContactModel>modifyAppointmentContactComboBox;
    @FXML
    public TextField modifyAppointmentDescriptionTextField;
    @FXML
    public DatePicker modifyAppointmentDatePicker_Start;
    @FXML
    public Button cancelBtn;
    @FXML
    public Button saveBtn;

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'cancel' button is clicked the user will be taken
     *                    back to the main appointment screen where they can view appointments
     *                    by month, week or all.
     * */
    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,868.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the save button is clicked it will check and ensure
     *                    the new appointment is inside business operation hours
     *                    and days.
     *                    It will also check to make sure that their are no empty value
     *                    fields before saving.
     * */
    public void saveAddAppointment(ActionEvent actionEvent) throws SQLException, IOException{

        /**
         * Variables 'checkStartAppDay' and 'checkEndAppDay' store the values from the
         * start and end date pickers, and gets the day of the week, to check if the new
         * appointment being scheduled is inside the business operation days.
         * */
        DayOfWeek checkStartAppDay = modifyAppointmentDatePicker_Start.getValue().getDayOfWeek();
        DayOfWeek checkEndAppDay = modifyAppointmentDatePicker_End.getValue().getDayOfWeek();
        Integer checkStartAppInt = checkStartAppDay.getValue();
        Integer checkEndAppInt = checkEndAppDay.getValue();
        /**
         * Variables 'weekStart' and 'weekEnd' store the week start (Monday) and the week end (Friday)
         * */
        Integer weekStart = DayOfWeek.MONDAY.getValue(); //Week Start
        Integer weekEnd = DayOfWeek.FRIDAY.getValue(); //Week End

        /**
         * The following variable gets information from the specified
         * text fields entered by the user.
         * */
        int appId = Integer.parseInt(modifyAppointmentIDTextField.getText());
        String appTitle = modifyAppointmentTitleTextField.getText();
        String appDescription = modifyAppointmentDescriptionTextField.getText();
        String appType = ModifyAppointmentTypeTextField.getText();
        String appLocation = ModifyLocationTextField.getText();

        ContactModel contactModel = modifyAppointmentContactComboBox.getValue();
        /** --------------------------------------------------------------------------- */

        /**
         * Check that the new appointment falls between the week start (Monday) and the week end (Friday)
         * if the new appointment is scheduled on saturday or sunday, the user will receive an alert
         * informing the user.
         * */
        if (checkStartAppInt < weekStart || checkStartAppInt > weekEnd){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Appointment outside business operation days");
            alert.showAndWait();
            return;
        }
        else if (checkEndAppInt < weekStart || checkEndAppInt > weekEnd){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Appointment outside business operation days");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /** If contact combo box selection is null, user receives an alert */
        if (contactModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Contact");
            alert.setHeaderText("Empty Contact Combo Box");
            alert.showAndWait();
            return;
        }
        int appContact = modifyAppointmentContactComboBox.getValue().getContactId();

        /** --------------------------------------------------------------------------- */
        /** If Start Date Picker selection is null, user receives an alert */
        LocalDate appStartDate = modifyAppointmentDatePicker_Start.getValue();
        if (appStartDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Start Date");
            alert.setHeaderText("Empty Start Date Picker");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /** If Start Time combo box selection is null, user receives an alert */
        LocalTime appStart = ModifyAppointmentStartTimeComboBox.getValue();
        if (appStart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Start Time");
            alert.setHeaderText("Empty Start Time Combo Box");
            alert.showAndWait();
            return;
        }
        /**
         * Variable 'appStartDateTime' stores the start date picker and start time in one variable for later use
         * */
        LocalDateTime appStartDateTime = LocalDateTime.of(modifyAppointmentDatePicker_Start.getValue(), ModifyAppointmentStartTimeComboBox.getValue());

        /** --------------------------------------------------------------------------- */
        /** If End Date Picker selection is null, user receives an alert */
        LocalDate appEndDate = modifyAppointmentDatePicker_End.getValue();
        if (appEndDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty End Date");
            alert.setHeaderText("Empty End Date Picker");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /** If End Time combo box selection is null, user receives an alert */
        LocalTime appEnd = ModifyAppointmentEndTimeComboBox.getValue();
        if (appEnd == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty End Time");
            alert.setHeaderText("Empty End Time Combo Box");
            alert.showAndWait();
            return;
        }
        /**
         * Variable 'appEnd' stores the end date picker and end time in one variable for later use
         * */
        LocalDateTime appEndDateTime = LocalDateTime.of(modifyAppointmentDatePicker_End.getValue(), ModifyAppointmentEndTimeComboBox.getValue());


        /** --------------------------------------------------------------------------- */
        /**
         * Alert for if the appointment start time and is after the end time
         * */
        if (appStart.isAfter(appEnd)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Appointment start time is after appointment end time");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * Alert for if the appointment start time is equal to the end time
         * */
        if (appStart.equals(appEnd)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Appointment start time can not be the same as appointment end time");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * If the customer Id combo box is null, user will receive an error
         * */
        CustomerModel customerModel = modifyAppointmentCustomerIDComboBox.getValue();
        if (customerModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Customer");
            alert.setHeaderText("Empty Customer Combo Box");
            alert.showAndWait();
            return;
        }
        int customerId = modifyAppointmentCustomerIDComboBox.getValue().getCustomerId();

        /** --------------------------------------------------------------------------- */
        /**
         * Checks to make sure that the appointment date start and end are on the same
         * day.
         * If not the user will receive an alert informing them
         * */
        if (appStartDate.getDayOfWeek() != appEndDate.getDayOfWeek()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Appointment must start and end on the same day");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * If the user Id combo box is null, user will receive an error
         * */
        UserModel userModel = modifyAppointmentUserIDComboBox.getValue();
        if (userModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Customer");
            alert.setHeaderText("Empty Customer Combo Box");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * if appointment title text field is empty, user receives an error
         * */
        int userId = modifyAppointmentUserIDComboBox.getValue().getUserId();
        if (appTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty User Id");
            alert.setHeaderText("Empty User Id Combo Box");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * if appointment description text field is empty, user receives an error
         * */
        else if (appDescription.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Description");
            alert.setHeaderText("Empty Description Box");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * if appointment type text field is empty, user receives an error
         * */
        else if (appType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Type");
            alert.setHeaderText("Empty Appointment Type");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * if appointment location text field is empty, user receives an error
         * */
        else if (appLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Location");
            alert.setHeaderText("Empty Appointment Location");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * Checks that the new appointment is scheduled between (Operation Hours 8AM - 10PM EST (22 Military Time))
         * Uses the 'TimeConversion' Class in the 'Helper' package
         * */
        else if (Helper.TimeConversion.operationCompanyTime(appStartDateTime, appEndDateTime)){
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * Checks that the new appointment does not class with any already existing appointment.
         *
         * (NEED TO FIX: Only receives alert when clashing appointment is schedule with the same customer Id, as the already existing appointment) ----
         * */
        else if (AppointmentQuery.clashing(modifyAppointmentCustomerIDComboBox.getSelectionModel().getSelectedItem().getCustomerId(), appStartDateTime, appEndDateTime)){
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * Checks that appointment does not clash with any already existing appointments (Checks appointment ID)
         * */
        else if (AppointmentQuery.clashingCheckWithAppId(modifyAppointmentCustomerIDComboBox.getSelectionModel().getSelectedItem().getCustomerId(), appStartDateTime, appEndDateTime, appId)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Clashing Appointments");
            alert.setContentText("Check appointment time");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * If the new appointment contains no empty values and is within business operation
         * days and hours, then the modified appointment will be updated
         * into the database and then loaded into the table.
         *
         * Returns user back to main appointment after appointment is modified
         * */
        else {
            AppointmentQuery.modifyExistingAppointment(appId, appTitle, appDescription, appContact, appType, appStartDateTime, appEndDateTime, customerId, userId,appLocation);
            Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            Scene scene = new Scene(root,868.0,720.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    public void sendAppData(AppointmentModel appointmentModel) throws SQLException {
        /**
         * Populates Appointment Start Time Combo Box
         * */
        ModifyAppointmentStartTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        /**
         * Populates Appointment End Time Combo Box
         * */
        ModifyAppointmentEndTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());

        /**
         * Get value fields
         * */
        modifyAppointmentIDTextField.setText(Integer.toString(appointmentModel.getAppId()));
        modifyAppointmentTitleTextField.setText(appointmentModel.getAppTitle());
        modifyAppointmentDescriptionTextField.setText(appointmentModel.getAppDescription());
        ModifyLocationTextField.setText(appointmentModel.getAppLocation());
        ModifyAppointmentTypeTextField.setText(appointmentModel.getAppType());
        modifyAppointmentDatePicker_Start.setValue(appointmentModel.getAppStart().toLocalDate());
        modifyAppointmentDatePicker_End.setValue(appointmentModel.getAppEnd().toLocalDate());
        ModifyAppointmentStartTimeComboBox.setValue(appointmentModel.getAppStart().toLocalTime());
        ModifyAppointmentEndTimeComboBox.setValue(appointmentModel.getAppEnd().toLocalTime());

        /**
         * Get Appointment Contact
         * */
        ContactModel contactModel = ContactQuery.contactById(appointmentModel.getAppContact());
        modifyAppointmentContactComboBox.setValue(contactModel);

        /**
         * Get Appointment Customer Id
         * */
        CustomerModel customerModel = CustomerQuery.customerById(appointmentModel.getAppCustomerId());
        modifyAppointmentCustomerIDComboBox.setValue(customerModel);

        /**
         * Get Appointment user Id
         * */
        UserModel userModel = UserQuery.obtainUsernameById(appointmentModel.getAppUserId());
        modifyAppointmentUserIDComboBox.setValue(userModel);
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Populates Contact Combo Box
         * */
        ObservableList<ContactModel>allContacts = ContactQuery.obtainAllContacts();
        modifyAppointmentContactComboBox.setItems(allContacts);
        /**
         * Populates Customer Combo Box
         * */
        ObservableList<CustomerModel> allCustomers = CustomerQuery.obtainAllCustomers();
        modifyAppointmentCustomerIDComboBox.setItems(allCustomers);
        /**
         * Populates User Combo Box
         * */
        ObservableList<UserModel> allUsers = UserQuery.obtainAllUsers();
        modifyAppointmentUserIDComboBox.setItems(allUsers);

    }
}
