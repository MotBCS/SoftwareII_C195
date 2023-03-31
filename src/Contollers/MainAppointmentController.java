package Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppointmentController {
    public Button deleteAppointmentBtn;
    public Button modifyAppointmentBtn;
    public Button addNewAppointmentBtn;
    public Button backBtn;
    public TableView appointmentTable;
    public TableColumn appIDColumn;
    public TableColumn titleColumn;
    public TableColumn typeColumn;
    public TableColumn locationColumn;
    public TableColumn startDateTimeColumn;
    public TableColumn endDateTimeColumn;
    public TableColumn contactIDColumn;
    public TableColumn customerIDColumn;
    public TableColumn descriptionColumn;
    public TableColumn userIDColumn;
    public RadioButton viewAllAppointmentsRadioBtn;
    public RadioButton viewAppointmentByWeekRadioBtn;
    public RadioButton viewAppointmentByMonthRadioBtn;

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void onActionAddNewAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AddNewAppointment.fxml"));
        Stage stage = (Stage) addNewAppointmentBtn.getScene().getWindow();
        Scene scene = new Scene(root,600.0,505.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void toModifyAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ModifyExistingAppointmentScreen.fxml"));
        Stage stage = (Stage) modifyAppointmentBtn.getScene().getWindow();
        Scene scene = new Scene(root,600.0,505.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void deleteAppointment(ActionEvent actionEvent) {
    }

    public void onActionViewAllRadioBtn(ActionEvent actionEvent) {
    }

    public void onActionViewByWeekRadioBtn(ActionEvent actionEvent) {
    }

    public void onActionViewByMonthRadioBtn(ActionEvent actionEvent) {
    }
}
