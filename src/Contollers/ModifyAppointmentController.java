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

public class ModifyAppointmentController implements Initializable {
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


    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,868.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void saveAddAppointment(ActionEvent actionEvent) throws IOException{

        int appId = Integer.parseInt(modifyAppointmentIDTextField.getText());
        String appTitle = modifyAppointmentTitleTextField.getText();
        String appDescription = modifyAppointmentDescriptionTextField.getText();
        String appType = ModifyAppointmentTypeTextField.getText();
        String appLocation = ModifyLocationTextField.getText();

        ContactModel contactModel = modifyAppointmentContactComboBox.getValue();
        if (contactModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Contact");
            alert.setHeaderText("Empty Contact Combo Box");
            alert.showAndWait();
            return;
        }
        int appContact = modifyAppointmentContactComboBox.getValue().getContactId();

        LocalDate appStartDate = modifyAppointmentDatePicker_Start.getValue();
        if (appStartDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Start Date");
            alert.setHeaderText("Empty Start Date Picker");
            alert.showAndWait();
            return;
        }
        LocalTime appStart = ModifyAppointmentStartTimeComboBox.getValue();
        if (appStart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Start Time");
            alert.setHeaderText("Empty Start Time Combo Box");
            alert.showAndWait();
            return;
        }
        LocalDateTime appStartDateTime = LocalDateTime.of(modifyAppointmentDatePicker_Start.getValue(), ModifyAppointmentStartTimeComboBox.getValue());

        LocalDate appEndDate = modifyAppointmentDatePicker_End.getValue();
        if (appEndDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty End Date");
            alert.setHeaderText("Empty End Date Picker");
            alert.showAndWait();
            return;
        }

        LocalTime appEnd = ModifyAppointmentEndTimeComboBox.getValue();
        if (appEnd == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty End Time");
            alert.setHeaderText("Empty End Time Combo Box");
            alert.showAndWait();
            return;
        }
        LocalDateTime appEndDateTime = LocalDateTime.of(modifyAppointmentDatePicker_End.getValue(), ModifyAppointmentEndTimeComboBox.getValue());
        CustomerModel customerModel = modifyAppointmentCustomerIDComboBox.getValue();
        if (customerModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Customer");
            alert.setHeaderText("Empty Customer Combo Box");
            alert.showAndWait();
            return;
        }
        int customerId = modifyAppointmentCustomerIDComboBox.getValue().getCustomerId();

        UserModel userModel = modifyAppointmentUserIDComboBox.getValue();
        if (userModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Customer");
            alert.setHeaderText("Empty Customer Combo Box");
            alert.showAndWait();
            return;
        }
        int userId = modifyAppointmentUserIDComboBox.getValue().getUserId();
        if (appTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty User Id");
            alert.setHeaderText("Empty User Id Combo Box");
            alert.showAndWait();
            return;
        }
        else if (appDescription.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Description");
            alert.setHeaderText("Empty Description Box");
            alert.showAndWait();
            return;
        }
        else if (appType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Type");
            alert.setHeaderText("Empty Appointment Type");
            alert.showAndWait();
            return;
        }
        else if (appLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Empty Location");
            alert.setHeaderText("Empty Appointment Location");
            alert.showAndWait();
            return;
        }
        else if (Helper.TimeConversion.operationCompanyTime(appStartDateTime, appEndDateTime)){
            return;
        }
        else if (AppointmentQuery.clashing(modifyAppointmentCustomerIDComboBox.getSelectionModel().getSelectedItem().getCustomerId(), appStartDateTime, appEndDateTime)){
            return;
        }
        else if (AppointmentQuery.clashingCheckWithAppId(modifyAppointmentCustomerIDComboBox.getSelectionModel().getSelectedItem().getCustomerId(), appStartDateTime, appEndDateTime, appId)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Clashing Appointments");
            alert.setContentText("Check appointment time");
        }
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

    public void sendAppData(AppointmentModel appointmentModel) throws SQLException {
        ModifyAppointmentStartTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        ModifyAppointmentEndTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());

        modifyAppointmentIDTextField.setText(Integer.toString(appointmentModel.getAppId()));
        modifyAppointmentTitleTextField.setText(appointmentModel.getAppTitle());
        modifyAppointmentDescriptionTextField.setText(appointmentModel.getAppDescription());
        ModifyLocationTextField.setText(appointmentModel.getAppLocation());
        ModifyAppointmentTypeTextField.setText(appointmentModel.getAppType());
        modifyAppointmentDatePicker_Start.setValue(appointmentModel.getAppStart().toLocalDate());
        modifyAppointmentDatePicker_End.setValue(appointmentModel.getAppEnd().toLocalDate());
        ModifyAppointmentStartTimeComboBox.setValue(appointmentModel.getAppStart().toLocalTime());
        ModifyAppointmentEndTimeComboBox.setValue(appointmentModel.getAppEnd().toLocalTime());

        ContactModel contactModel = ContactQuery.contactById(appointmentModel.getAppContact());
        modifyAppointmentContactComboBox.setValue(contactModel);

        CustomerModel customerModel = CustomerQuery.customerById(appointmentModel.getAppCustomerId());
        modifyAppointmentCustomerIDComboBox.setValue(customerModel);

        UserModel userModel = UserQuery.obtainUsernameById(appointmentModel.getAppUserId());
        modifyAppointmentUserIDComboBox.setValue(userModel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ContactModel>allContacts = ContactQuery.obtainAllContacts();
        modifyAppointmentContactComboBox.setItems(allContacts);

        ObservableList<CustomerModel> allCustomers = CustomerQuery.obtainAllCustomers();
        modifyAppointmentCustomerIDComboBox.setItems(allCustomers);

        ObservableList<UserModel> allUsers = UserQuery.obtainAllUsers();
        modifyAppointmentUserIDComboBox.setItems(allUsers);

    }
}
