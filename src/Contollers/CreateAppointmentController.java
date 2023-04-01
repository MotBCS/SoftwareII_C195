package Contollers;

import Models.AppointmentModel;
import Models.ContactModel;
import Models.CustomerModel;
import Models.UserModel;
import Queries.ContactQuery;
import Queries.CustomerQuery;
import Queries.UserQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    public void saveAddAppointment(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAppointmentIDTextField.setId(createAppointmentIDTextField.getId());
        createAppointmentStartTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());
        createAppointmentEndTimeComboBox.setItems(Helper.TimeConversion.appTimeComboBoxPopulation());

        createAppointmentDatePicker_Start
        createAppointmentDatePicker_End

        ObservableList<ContactModel>allContacts = ContactQuery.obtainAllContacts();
        createAppointmentContactComboBox.setItems(allContacts);

        ObservableList<UserModel>allUsers = UserQuery.obtainAllUsers();
        createAppointmentUserIDComboBox.setItems(allUsers);

        ObservableList<CustomerModel>allCustomers = CustomerQuery.obtainAllCustomers();
        createAppointmentCustomerIDComboBox.setItems(allCustomers);


    }
}
