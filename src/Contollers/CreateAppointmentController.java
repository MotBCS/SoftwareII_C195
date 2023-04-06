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
 * This class is the controller for the create appointment screen.
 * Allows user to create a new appointment then save it to
 * the appointment table.
 *
 * @author Mya Thomas
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

    private final int AddToDatePicker = 0;
    private int customerId;
    private LocalDateTime localDateTimeStart;
    private LocalDateTime localDateTimeEnd;


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
         * If the user attempts to schedule an appointment on a past date, they will
         * receive an error, informing them.
         * */
        else if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Appointment can not be scheduled in the past");
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
        /** --------------------------------------------------------------------------- */
        /**
         * if appointment title text field is empty, user receives an error
         * */
        if (appTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment title");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * if appointment description text field is empty, user receives an error
         * */
        else if(appDes.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment Description");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * if appointment type text field is empty, user receives an error
         * */
        else if (appType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment type");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * if appointment location text field is empty, user receives an error
         * */
        else if (appLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment location");
            alert.showAndWait();
            return;
        }
        /** --------------------------------------------------------------------------- */
        /**
         * Checks that the new appointment is scheduled between (Operation Hours 8AM - 10PM EST (22 Military Time))
         * Uses the 'TimeConversion' Class in the 'Helper' package
         * */
        else if (Helper.TimeConversion.operationCompanyTime(appStart, appEnd)){
            return;
        }


        /** --------------------------------------------------------------------------- */
        /**
         * Checks that the new appointment does not class with any already existing appointment.
         *
         * (NEED TO FIX: Only receives alert when clashing appointment is schedule with the same customer Id, as the already existing appointment) ----
         * */
        else if (AppointmentQuery.clashingAppointmentsByCustomerId(createAppointmentCustomerIDComboBox.getSelectionModel().getSelectedItem().getCustomerId(), appStart, appEnd)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Clashing Appointment");
            alert.setContentText("This appointment, clashes with another appointment scheduled by " + createAppointmentCustomerIDComboBox.getSelectionModel().getSelectedItem().getCustomer_Name() + "\nPlease change appointment contact or customer or change appointment time.");
            alert.showAndWait();
            return;
        }

        /** --------------------------------------------------------------------------- */
        /**
         * If the new appointment contains no empty values and is within business operation
         * days and hours, then the new appointment will be created and the values will be
         * inserted into the database and then loaded into the table.
         *
         * Returns user back to main appointment after appointment is created
         * */
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

        /**
         *
         * LAMBDA EXPRESSION #2
         * Location : (CreateAppointmentController)
         * Lines : (384 - 385)
         *
         * Takes the value from appointment start date picker and sets it to the
         * appointment end date picker.
         *
         * */
        createAppointmentDatePicker_Start.valueProperty().addListener((DatePicker, DatePickerOriginal, DatePickerNewValue) ->
                createAppointmentDatePicker_End.setValue(DatePickerNewValue.plusDays(AddToDatePicker)));

        createAppointmentIDTextField.setId(createAppointmentIDTextField.getId());
        /**
         * Populates Appointment Start Time Combo Box
         * */
        createAppointmentStartTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        /**
         * Populates Appointment End Time Combo Box
         * */
        createAppointmentEndTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        /**
         * Populates Contact Combo Box
         * */
        ObservableList<ContactModel>allContacts = ContactQuery.obtainAllContacts();
        createAppointmentContactComboBox.setItems(allContacts); // Set Items to combo box
        /**
         * Populates User Combo Box
         * */
        ObservableList<UserModel>allUsers = UserQuery.obtainAllUsers();
        createAppointmentUserIDComboBox.setItems(allUsers); // Set Items to combo box
        /**
         * Populates Customer Combo Box
         * */
        ObservableList<CustomerModel>allCustomers = CustomerQuery.obtainAllCustomers();
        createAppointmentCustomerIDComboBox.setItems(allCustomers); // Set Items to combo box
    }
}
