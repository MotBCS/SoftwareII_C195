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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {
    public ComboBox<CustomerModel>modifyAppointmentCustomerIDComboBox;
    public ComboBox<UserModel>modifyAppointmentUserIDComboBox;
    public DatePicker modifyAppointmentDatePicker_End;
    public TextField modifyAppointmentIDTextField;
    public TextField modifyAppointmentTitleTextField;
    public TextField ModifyAppointmentTypeTextField;
    public TextField ModifyLocationTextField;
    public ComboBox<LocalTime>ModifyAppointmentStartTimeComboBox;
    public ComboBox<LocalTime>ModifyAppointmentEndTimeComboBox;
    public ComboBox<ContactModel>modifyAppointmentContactComboBox;
    public TextField modifyAppointmentDescriptionTextField;
    public DatePicker modifyAppointmentDatePicker_Start;
    public Button cancelBtn;
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
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Contact Combo Box");
            alert.showAndWait();
            return;
        }
        int appContact = modifyAppointmentContactComboBox.getValue().getContactId();

        LocalDate appStartDatePicker = modifyAppointmentDatePicker_Start.getValue();
        if (appStartDatePicker == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Start Date Picker");
            alert.showAndWait();
            return;
        }
        LocalDate appEndDatePicker = modifyAppointmentDatePicker_End.getValue();
        if (appEndDatePicker == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty End Date Picker");
            alert.showAndWait();
            return;
        }

        LocalTime appST = ModifyAppointmentStartTimeComboBox.getValue();
        if (appST == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Start Time");
            alert.showAndWait();
            return;
        }
        LocalTime appED = ModifyAppointmentEndTimeComboBox.getValue();
        if (appED == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty End Time");
            alert.showAndWait();
            return;
        }

        CustomerModel customerModel = modifyAppointmentCustomerIDComboBox.getValue();
        if (customerModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Customer Combo Box");
            alert.showAndWait();
            return;
        }

        UserModel userModel = modifyAppointmentUserIDComboBox.getValue();
        if (userModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty User Combo Box");
            alert.showAndWait();
            return;
        }

        LocalDateTime appStart = LocalDateTime.of(modifyAppointmentDatePicker_Start.getValue(), ModifyAppointmentStartTimeComboBox.getValue());
        LocalDateTime appEnd = LocalDateTime.of(modifyAppointmentDatePicker_End.getValue(), ModifyAppointmentEndTimeComboBox.getValue());
        int customerId = modifyAppointmentCustomerIDComboBox.getValue().getCustomerId();
        int appByUserId = modifyAppointmentUserIDComboBox.getValue().getUserId();

        if (appTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Title");
            alert.showAndWait();
        }
        else if (appDescription.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Description");
            alert.showAndWait();
        }
        else if (appType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Type");
            alert.showAndWait();
        }
        else if (appLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to save modified appointment");
            alert.setContentText("Empty Location");
            alert.showAndWait();
        }
        else if (Helper.TimeConversion.operationCompanyTime(appStart, appEnd)){
            return;
        }
        else if (AppointmentQuery.clashingAppointments(customerId, appStart, appEnd)){
            return;
        }
        else {
            AppointmentQuery.modifyExistingAppointment(appId, appTitle, appDescription, appContact, appType, appStart, appEnd, customerId, appByUserId, appLocation);
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
