package Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyAppointmentController {
    public ComboBox modifyAppointmentCustomerIDComboBox;
    public ComboBox modifyAppointmentUserIDComboBox;
    public DatePicker modifyAppointmentDatePicker_End;
    public TextField modifyAppointmentIDTextField;
    public TextField modifyAppointmentTitleTextField;
    public TextField ModifyAppointmentTypeTextField;
    public TextField ModifyLocationTextField;
    public ComboBox ModifyAppointmentStartTimeComboBox;
    public ComboBox ModifyAppointmentEndTimeComboBox;
    public ComboBox modifyAppointmentContactComboBox;
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
}
