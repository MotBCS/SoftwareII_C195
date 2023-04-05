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

/** ----------------------------------------------------------------------------------------------------------------- */
/**
 *
 * The 'ReportController' class is used as a controller for the ReportScreen.fxml.
 * Allows users to view tables containing all appointments that can be filtered by
 * selecting a contact from the combo box at the top right of the screen. There also
 * two more additional tables that the user can use to view appointment total by
 * month and type as well as see how many customers per state/province.
 *
 * */
public class ReportController implements Initializable {

    /** Buttons */
    @FXML
    public Button backBtn;

    /**
     * View Appointment By Month and Type
     * Table and Columns
     * */
    @FXML
    public TableView<REPORT_MonthType_TotalAppointment>monthTypeTable;
    @FXML
    public TableColumn<REPORT_MonthType_TotalAppointment, String>monthColumn;
    @FXML
    public TableColumn<REPORT_MonthType_TotalAppointment, String>typeColumn;
    @FXML
    public TableColumn<REPORT_MonthType_TotalAppointment, Integer>appointmentTotalColumn;

    /**
     * View Customer Total By State/Provinces
     * Table and Columns
     * */
    @FXML
    public TableView<REPORT_StateProvince_TotalCustomer>stateProvinceTable;
    @FXML
    public TableColumn<REPORT_StateProvince_TotalCustomer, String>stateProvinceColumn;
    @FXML
    public TableColumn<REPORT_StateProvince_TotalCustomer, Integer>customerTotalColumn;

    /**
     * View Appointments By Selected Contact
     *Table and Columns
     * */
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

    /**
     * Contact Combo Box (Used to filter appointment table by selected appointment)
     * */
    @FXML
    public ComboBox<ContactModel>reportContactComboBox;

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent 'filterContactReportTable' allows the user to
     *                    filter the appointment table located in the report menu,
     *                    by selected contact
     * */
    public void filterContactReportTable(ActionEvent actionEvent) {
        ContactModel contactModel = reportContactComboBox.getSelectionModel().getSelectedItem();
        /**
         * Checks the contact Id, to determine who to filter the appointment table by
         * */
        if (contactModel.getContactId() == 1){
            appointmentTable.setItems(ContactQuery.contactReportByContactID1());
        }else if (contactModel.getContactId() == 2){
            appointmentTable.setItems(ContactQuery.contactReportByContactID2());
        }else if (contactModel.getContactId() == 3){
            appointmentTable.setItems(ContactQuery.contactReportByContactID3());
        }
        /**
         * By default, the appointment table will display all appointments if there
         * isn't an contact selected in the combo box
         * */
        else {
            ObservableList<AppointmentModel> viewAll = ContactQuery.contactReport();
            appointmentTable.setItems(viewAll);
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'back' button is clicked the user will be taken back
     *                    to the main appointment screen.
     * */
    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Sets combo box prompt text, while there isn't a
         * contact selected.
         * */
        reportContactComboBox.setPromptText("Select Contact");

        /**
         * Creates an empty observable list to store all the contacts
         * obtained from the contact query, used to populate the
         * report screen contact combo box
         * */
        ObservableList<ContactModel>populateContactComboBox = FXCollections.observableArrayList();
        populateContactComboBox.clear();
        populateContactComboBox = ContactQuery.obtainAllContacts();
        reportContactComboBox.setItems(populateContactComboBox);


        /** Contact Report Table */
        /**
         * Binding columns to tables
         * */
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

        /**
         * Setting items to appointment table
         * */
        appointmentTable.setItems(AppointmentQuery.obtainAllAppointments());

        /**
         * Combine Month and Type Tables into one
         * */
        ObservableList<REPORT_MonthType_TotalAppointment> viewAllMonthType = ReportQuery.appByMonthType();
        /** Binding Month, type and total column to table */
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("appMonth"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("appType"));
        appointmentTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPerMonth"));
        /** Set items to table */
        monthTypeTable.setItems(viewAllMonthType);

        /**
         * State/Province Table
         * */
        ObservableList<REPORT_StateProvince_TotalCustomer> viewAllStateProvince = ReportQuery.customerTotalByStateProvince();
        /** Binding State/Province columns to table*/
        stateProvinceColumn.setCellValueFactory(new PropertyValueFactory<>("stateProvince"));
        customerTotalColumn.setCellValueFactory(new PropertyValueFactory<>("customerTotal"));
        /** Set items to table */
        stateProvinceTable.setItems(viewAllStateProvince);
    }
}
