package Contollers;

import Models.AppointmentModel;
import Models.ContactModel;
import Models.CustomerModel;
import Models.UserModel;
import Queries.ContactQuery;
import Queries.CustomerQuery;
import Queries.UserQuery;
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
import java.sql.SQLException;
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

    public void saveAddAppointment(ActionEvent actionEvent) {
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

    }
}
