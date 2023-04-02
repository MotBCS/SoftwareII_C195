package Contollers;

import Models.AppointmentModel;
import Models.ContactModel;
import Models.REPORT_MonthType_TotalAppointment;
import Models.REPORT_StateProvince_TotalCustomer;
import Queries.AppointmentQuery;
import Queries.ContactQuery;
import Queries.ReportQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public ComboBox<ContactModel>reportContactComboBox;
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
        ContactModel contactModel = reportContactComboBox.getSelectionModel().getSelectedItem();
        if (contactModel.getContactId() == 1){
            appointmentTable.setItems(ContactQuery.contactReportByContactID1());
        }else if (contactModel.getContactId() == 2){
            appointmentTable.setItems(ContactQuery.contactReportByContactID2());
        }else if (contactModel.getContactId() == 3){
            appointmentTable.setItems(ContactQuery.contactReportByContactID3());
        }else {
            ObservableList<AppointmentModel> viewAll = ContactQuery.contactReport();
            appointmentTable.setItems(viewAll);
        }
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
        reportContactComboBox.setPromptText("Select Contact");

        ObservableList<ContactModel>populateContactComboBox = FXCollections.observableArrayList();
        populateContactComboBox.clear();
        populateContactComboBox = ContactQuery.obtainAllContacts();
        reportContactComboBox.setItems(populateContactComboBox);


        /** Contact Report Table */
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

        /**
         * Combine Month and Type Tables into one
         * */
        ObservableList<REPORT_MonthType_TotalAppointment> viewAllMonthType = ReportQuery.appByMonthType();
        /** Binding Month, type and total column to table */
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("appMonth"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("appType"));
        appointmentTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPerMonth"));
        /** Set values to table */
        monthTypeTable.setItems(viewAllMonthType);

        /**
         * State/Province Table
         * */
        ObservableList<REPORT_StateProvince_TotalCustomer> viewAllStateProvince = ReportQuery.customerTotalByStateProvince();
        /** Binding State/Province columns to table*/
        stateProvinceColumn.setCellValueFactory(new PropertyValueFactory<>("stateProvince"));
        customerTotalColumn.setCellValueFactory(new PropertyValueFactory<>("customerTotal"));
        /** Set values to table */
        stateProvinceTable.setItems(viewAllStateProvince);
    }
}
