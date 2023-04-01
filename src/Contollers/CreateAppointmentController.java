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

public class CreateAppointmentController implements Initializable {
//    public ComboBox<CustomerModel>modifyAppointmentCustomerIDComboBox;
//    public ComboBox<UserModel>modifyAppointmentUserIDComboBox;
//    public DatePicker modifyAppointmentDatePicker_End;
//    public TextField modifyAppointmentIDTextField;
//    public TextField modifyAppointmentTitleTextField;
//    public TextField ModifyAppointmentTypeTextField;
//    public TextField ModifyLocationTextField;
//    public ComboBox<LocalTime>ModifyAppointmentStartTimeComboBox;
//    public ComboBox<LocalTime>ModifyAppointmentEndTimeComboBox;
//    public ComboBox<ContactModel>modifyAppointmentContactComboBox;
//    public TextField modifyAppointmentDescriptionTextField;
//    public DatePicker modifyAppointmentDatePicker_Start;
    public Button cancelBtn;
    public Button saveBtn;
    public ComboBox<CustomerModel>createAppointmentCustomerIDComboBox;
    public ComboBox<UserModel>createAppointmentUserIDComboBox;
    public DatePicker createAppointmentDatePicker_End;
    public TextField createAppointmentIDTextField;
    public TextField createAppointmentTitleTextField;
    public TextField createAppointmentTypeTextField;
    public TextField createLocationTextField;
    public ComboBox<LocalTime>createAppointmentStartTimeComboBox;
    public ComboBox<LocalTime>createAppointmentEndTimeComboBox;
    public ComboBox<ContactModel>createAppointmentContactComboBox;
    public TextField createAppointmentDescriptionTextField;
    public DatePicker createAppointmentDatePicker_Start;

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,868.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void saveAddAppointment(ActionEvent actionEvent) throws SQLException, IOException {
        String appTitle = createAppointmentTitleTextField.getText();
        String appDes = createAppointmentDescriptionTextField.getText();
        String appType = createAppointmentTypeTextField.getText();
        String appLocation = createLocationTextField.getText();

        ContactModel contactModel = createAppointmentContactComboBox.getValue();
        if (contactModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty contact combo box");
        }
        int appContact = contactModel.getContactId();

        LocalDate startDate = createAppointmentDatePicker_Start.getValue();
        if (startDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty start date");
            return;
        }
        LocalTime startTime = createAppointmentStartTimeComboBox.getValue();
        if (startTime == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty start time");
            return;
        }
        LocalDateTime appStart = LocalDateTime.of(createAppointmentDatePicker_Start.getValue(), createAppointmentStartTimeComboBox.getValue());

        LocalDate endDate = createAppointmentDatePicker_End.getValue();
        if (endDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty end date");
            return;
        }

        LocalTime endTime = createAppointmentEndTimeComboBox.getValue();
        if (endTime == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty end time");
            return;
        }
        LocalDateTime appEnd = LocalDateTime.of(createAppointmentDatePicker_End.getValue(), createAppointmentEndTimeComboBox.getValue());

        CustomerModel customerModel = createAppointmentCustomerIDComboBox.getValue();
        if (customerModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty customer combo box");
            return;
        }
        int appByCustomerId = createAppointmentCustomerIDComboBox.getValue().getCustomerId();

        UserModel userModel = createAppointmentUserIDComboBox.getValue();
        if (userModel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty user combo box");
            return;
        }
        int appByUserId = createAppointmentUserIDComboBox.getValue().getUserId();

        if (appTitle.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment title");
        }
        else if(appDes.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment Description");
        }
        else if (appType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment type");
        }
        else if (appLocation.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unable to create new appointment");
            alert.setContentText("Empty appointment location");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAppointmentIDTextField.setId(createAppointmentIDTextField.getId());
        createAppointmentStartTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        createAppointmentEndTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());

//        createAppointmentDatePicker_Start
//        createAppointmentDatePicker_End

        ObservableList<ContactModel>allContacts = ContactQuery.obtainAllContacts();
        createAppointmentContactComboBox.setItems(allContacts);

        ObservableList<UserModel>allUsers = UserQuery.obtainAllUsers();
        createAppointmentUserIDComboBox.setItems(allUsers);

        ObservableList<CustomerModel>allCustomers = CustomerQuery.obtainAllCustomers();
        createAppointmentCustomerIDComboBox.setItems(allCustomers);


    }
}
