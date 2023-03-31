package Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportController {
    public Button backBtn;
    public TableView monthTypeTable;
    public TableColumn monthColumn;
    public TableColumn typeColumn;
    public TableColumn appointmentTotalColumn;
    public TableView stateProvinceTable;
    public TableColumn stateProvinceColumn;
    public TableColumn customerTotalColumn;
    public ComboBox reportContactComboBox;
    public TableView appointmentTable;
    public TableColumn appIDColumn;
    public TableColumn titleColumn;
    public TableColumn AppointmentTypeColumn;
    public TableColumn locationColumn;
    public TableColumn startDateTimeColumn;
    public TableColumn endDateTimeColumn;
    public TableColumn contactIDColumn;
    public TableColumn customerIDColumn;
    public TableColumn descriptionColumn;
    public TableColumn userIDColumn;

    public void filterContactReportTable(ActionEvent actionEvent) {
    }

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
