package Contollers;

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
 * This class is the controller for the create appointment screen.
 * Allows user to create a new appointment then save it to
 * the appointment table.
 *
 * */
public class CreateAppointmentController implements Initializable {

    /** Buttons, Text Fields, Combo Boxes and Date Pickers */
    @FXML
    public Button cancelBtn;
    @FXML
    public Button saveBtn;
    @FXML
    public ComboBox<CustomerModel>createAppointmentCustomerIDComboBox;
    @FXML
    public ComboBox<UserModel>createAppointmentUserIDComboBox;
    @FXML
    public DatePicker createAppointmentDatePicker_End;
    @FXML
    public TextField createAppointmentIDTextField;
    @FXML
    public TextField createAppointmentTitleTextField;
    @FXML
    public TextField createAppointmentTypeTextField;
    @FXML
    public TextField createLocationTextField;
    @FXML
    public ComboBox<LocalTime>createAppointmentStartTimeComboBox;
    @FXML
    public ComboBox<LocalTime>createAppointmentEndTimeComboBox;
    @FXML
    public ComboBox<ContactModel>createAppointmentContactComboBox;
    @FXML
    public TextField createAppointmentDescriptionTextField;
    @FXML
    public DatePicker createAppointmentDatePicker_Start;
    private final int addDays_ToEndDatePicker = 0;

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
    public void saveAddAppointment(ActionEvent actionEvent) throws SQLException, IOException {

        /**
         * Variables 'checkStartAppDay' and 'checkEndAppDay' store the values from the
         * start and end date pickers, and gets the day of the week, to check if the new
         * appointment being scheduled is inside the business operation days.
         * */
        DayOfWeek checkStartAppDay = createAppointmentDatePicker_Start.getValue().getDayOfWeek();
        DayOfWeek checkEndAppDay = createAppointmentDatePicker_End.getValue().getDayOfWeek();
        Integer checkStartAppInt = checkStartAppDay.getValue();
        Integer checkEndAppInt = checkEndAppDay.getValue();
        /**
         * Variables 'weekStart' and 'weekEnd' store the week start (Monday) and the week end (Friday)
         * */
        Integer weekStart = DayOfWeek.MONDAY.getValue(); //Week Start
        Integer weekEnd = DayOfWeek.FRIDAY.getValue(); //Week End

        /**
         * The following variable store information from the specified
         * text fields entered by the user.
         * */
        String appTitle = createAppointmentTitleTextField.getText(); //Appointment Title
        String appDes = createAppointmentDescriptionTextField.getText(); //Appointment Description
        String appType = createAppointmentTypeTextField.getText(); //Appointment Type
        String appLocation = createLocationTextField.getText(); //Appointment Location

        ContactModel contactModel = createAppointmentContactComboBox.getValue(); //Appointment Contact

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
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty contact combo box");
            alert.showAndWait();
            return;
        }
        int appContact = contactModel.getContactId();

        /** --------------------------------------------------------------------------- */
        /** If Start Date Picker selection is null, user receives an alert */
        LocalDate startDate = createAppointmentDatePicker_Start.getValue();
        if (startDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty start date");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /** If Start Time combo box selection is null, user receives an alert */
        LocalTime startTime = createAppointmentStartTimeComboBox.getValue();
        if (startTime == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty start time");
            alert.showAndWait();
            return;
        }
        /**
         * Variable 'appStart' stores the start date picker and start time in one variable for later use
         * */
        LocalDateTime appStart = LocalDateTime.of(createAppointmentDatePicker_Start.getValue(), createAppointmentStartTimeComboBox.getValue());

        /** --------------------------------------------------------------------------- */
        /** If End Date Picker selection is null, user receives an alert */
        LocalDate endDate = createAppointmentDatePicker_End.getValue();
        if (endDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty end date");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /** If End Time combo box selection is null, user receives an alert */
        LocalTime endTime = createAppointmentEndTimeComboBox.getValue();
        if (endTime == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty end time");
            alert.showAndWait();
            return;
        }
        /**
         * Variable 'appEnd' stores the end date picker and end time in one variable for later use
         * */
        LocalDateTime appEnd = LocalDateTime.of(createAppointmentDatePicker_End.getValue(), createAppointmentEndTimeComboBox.getValue());

        /** --------------------------------------------------------------------------- */
        /**
         * Alert for if the appointment start time and is after the end time
         * */
        if (startTime.isAfter(endTime)){
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
        if (startTime.equals(endTime)){
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
        CustomerModel customerModel = createAppointmentCustomerIDComboBox.getValue();
        if (customerModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty customer combo box");
            alert.showAndWait();
            return;
        }
        int appByCustomerId = createAppointmentCustomerIDComboBox.getValue().getCustomerId();

        /** --------------------------------------------------------------------------- */
        /**
         * Checks to make sure that the appointment date start and end are on the same
         * day.
         * If not the user will receive an alert informing them
         * */
        if (startDate.getDayOfWeek() != endDate.getDayOfWeek()){
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
        UserModel userModel = createAppointmentUserIDComboBox.getValue();
        if (userModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty user combo box");
            alert.showAndWait();
            return;
        }
        int appByUserId = createAppointmentUserIDComboBox.getValue().getUserId();

        if (appTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment title");
            alert.showAndWait();
            return;
        }
        else if(appDes.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment Description");
            alert.showAndWait();
            return;
        }
        else if (appType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment type");
            alert.showAndWait();
            return;
        }
        else if (appLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment location");
            alert.showAndWait();
            return;
        }
        else if (Helper.TimeConversion.operationCompanyTime(appStart, appEnd)){
            return;
        }
        else if (AppointmentQuery.clashingAppointments(appByCustomerId, appStart, appEnd)){
            return;
        }
        else {
            AppointmentQuery.createNewAppointment(appTitle, appDes, appContact, appType, appStart, appEnd, appByCustomerId, appByUserId, appLocation);
            Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            Scene scene = new Scene(root,868.0,720.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAppointmentIDTextField.setId(createAppointmentIDTextField.getId());
        createAppointmentStartTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        createAppointmentEndTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());

        ObservableList<ContactModel>allContacts = ContactQuery.obtainAllContacts();
        createAppointmentContactComboBox.setItems(allContacts);

        ObservableList<UserModel>allUsers = UserQuery.obtainAllUsers();
        createAppointmentUserIDComboBox.setItems(allUsers);

        ObservableList<CustomerModel>allCustomers = CustomerQuery.obtainAllCustomers();
        createAppointmentCustomerIDComboBox.setItems(allCustomers);

        /**
         * Lambda Expression #2
         * */
        createAppointmentDatePicker_Start.valueProperty().addListener((DatePicker, DatePickerOriginal, DatePickerNewValue) -> createAppointmentDatePicker_End.setValue(DatePickerNewValue.plusDays(addDays_ToEndDatePicker)));
    }
}
