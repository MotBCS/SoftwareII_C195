package Contollers;

import Models.AppointmentModel;
import Models.REPORT_MonthType_TotalAppointment;
import Models.REPORT_StateProvince_TotalCustomer;
import Queries.AppointmentQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;


public class ReportController implements Initializable {
    @FXML
    public Button backBtn;
    @FXML
    public TableView<REPORT_MonthType_TotalAppointment>monthTypeTable;
    @FXML
    public TableColumn<REPORT_MonthType_TotalAppointment, String>monthColumn;
    @FXML
    public TableColumn<REPORT_MonthType_TotalAppointment, String>typeColumn;
    @FXML
    public TableColumn<REPORT_MonthType_TotalAppointment, Integer>appointmentTotalColumn;
    @FXML
    public TableView<REPORT_StateProvince_TotalCustomer>stateProvinceTable;
    @FXML
    public TableColumn<REPORT_StateProvince_TotalCustomer, String>stateProvinceColumn;
    @FXML
    public TableColumn<REPORT_StateProvince_TotalCustomer, Integer>customerTotalColumn;
    @FXML
    public ComboBox<String>reportContactComboBox;
    @FXML
    public TableView<AppointmentModel>appointmentTable;
    @FXML
    public TableColumn<AppointmentModel, Integer>appIDColumn;
    @FXML
    public TableColumn<AppointmentModel, String>titleColumn;
    @FXML
    public TableColumn<AppointmentModel, String>AppointmentTypeColumn;
    @FXML
    public TableColumn<AppointmentModel, String>locationColumn;
    @FXML
    public TableColumn<AppointmentModel, Timestamp>startDateTimeColumn;
    @FXML
    public TableColumn<AppointmentModel, Timestamp>endDateTimeColumn;
    @FXML
    public TableColumn<AppointmentModel, Integer>contactIDColumn;
    @FXML
    public TableColumn<AppointmentModel, Integer>customerIDColumn;
    @FXML
    public TableColumn<AppointmentModel, String>descriptionColumn;
    @FXML
    public TableColumn<AppointmentModel, Integer>userIDColumn;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appIDColumn.setCellValueFactory(new PropertyValueFactory<>("appId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        AppointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appType"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appStart"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appEnd"));
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("appCustomerId"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("appUserId"));

        appointmentTable.setItems(AppointmentQuery.obtainAllAppointments());

    }
}
