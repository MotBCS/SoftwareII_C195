package Contollers;

import Models.AppointmentModel;
import Models.UserModel;
import Queries.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

/** ----------------------------------------------------------------------------------------------------------------- */

public class MainAppointmentController implements Initializable {
    @FXML
    public Button deleteAppointmentBtn;
    @FXML
    public Button modifyAppointmentBtn;
    @FXML
    public Button addNewAppointmentBtn;
    @FXML
    public Button backBtn;
    @FXML
    public TableView<AppointmentModel>appointmentTable;
    @FXML
    public TableColumn<AppointmentModel, Integer>appIDColumn;
    @FXML
    public TableColumn<AppointmentModel, String>titleColumn;
    @FXML
    public TableColumn<AppointmentModel, String>typeColumn;
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
    @FXML
    public RadioButton viewAllAppointmentsRadioBtn;
    @FXML
    public RadioButton viewAppointmentByWeekRadioBtn;
    @FXML
    public RadioButton viewAppointmentByMonthRadioBtn;

    ObservableList<AppointmentModel> allAppList = FXCollections.observableArrayList();

    /** ----------------------------------------------------------------------------------------------------------------- */

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    public void onActionAddNewAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AddNewAppointment.fxml"));
        Stage stage = (Stage) addNewAppointmentBtn.getScene().getWindow();
        Scene scene = new Scene(root,600.0,505.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    public void toModifyAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if (appointmentTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/ModifyExistingAppointmentScreen.fxml"));
            fxmlLoader.load();
            ModifyAppointmentController modifyAppointmentController = fxmlLoader.getController();
            modifyAppointmentController.sendAppData(appointmentTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) modifyAppointmentBtn.getScene().getWindow();
            Parent scene = fxmlLoader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Select an existing appointment from the table to modify");
            alert.showAndWait();
            return;
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent The deleteAppointment method allows the user to delete
     *                    a selected appointment from the table.
     * */
    public void deleteAppointment(ActionEvent actionEvent) {
        /**
         * The variable 'obtainApp' is used to store the selected appointment
         * */
        AppointmentModel obtainApp = appointmentTable.getSelectionModel().getSelectedItem();
        /**
         * If there is no appointment selected and the user clicks the delete button
         * they will receive an error to inform them that the selection is empty.
         * */
        if (obtainApp == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Select an existing appointment from the table to delete");
            alert.showAndWait();
            return;
        }
        else if (obtainApp != null){
            /**
             * If the selection is not null, the user will receive an
             * warning that will allow them to confirm the deletion of the
             * selected appointment
             * */
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Selected Appointment?");
            alert.setContentText("Delete selected appointment " + obtainApp.getAppId() + ", from appointment table?");

            /**
             * If the user confirms the deletion of the selected appointment,
             * they will receive an alert confirming the deletion of the
             * selected appointment.
             * */
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.OK){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText("Appointment has been successfully deleted");

                /**
                 *          Alert Will Displays:
                 *          - Appointment ID
                 *          - Appointment Type
                 * */
                alert2.setContentText("Appointment ID: " + appointmentTable.getSelectionModel().getSelectedItem().getAppId() + "\nAppointment Type: " + appointmentTable.getSelectionModel().getSelectedItem().getAppType() + " has been successfully deleted");
                AppointmentQuery.deleteExistingAppointment(appointmentTable.getSelectionModel().getSelectedItem().getAppId());
                /**
                 * The 'allAppList' variable stores all appointment
                 * retrieved from the database
                 * */
                allAppList = AppointmentQuery.obtainAllAppointments();
                /**
                 * Set retrieved Items to appointment table and refresh
                 * */
                appointmentTable.setItems(allAppList);
                appointmentTable.refresh();
                alert2.showAndWait();
            }
            /**
             * If the user decides not to delete the selected appointment
             * and clicks the 'cancel' button the alert will close
             * and the selected appointment will still existing in the
             * table.
             * */
            else if (choice.get() == ButtonType.CANCEL){
                alert.close();
            }
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent The 'View All' radio  button is selected by default,
     *                    allows user to view all appointments currently scheduled.
     * */
    public void onActionViewAllRadioBtn(ActionEvent actionEvent) {
        /** Set Items to Appointment Table */
        appointmentTable.setItems(AppointmentQuery.obtainAllAppointments());
        System.out.println("All Radio Btn pressed");
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'View by Week' radio button is selected
     *                    the table will be populated with appointments
     *                    from the current week
     * */
    public void onActionViewByWeekRadioBtn(ActionEvent actionEvent) {
        /** Set Items to Appointment Table */
        appointmentTable.setItems(AppointmentQuery.appByCurrentWeek());

        /**
         * Alert informs the user that the  week radio button
         * has been selected
         * (This was mainly used to help make sure the
         * radio button where working when there are no
         * appointments scheduled in the table)
         * */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("View By Current Week");
        alert.setContentText("Viewing appointments scheduled this week");
        alert.showAndWait();
        System.out.println("Week Radio Btn pressed");
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'View By Month' radio button is selected
     *                    the table will be populated with appointments
     *                    from the current month.
     * */
    public void onActionViewByMonthRadioBtn(ActionEvent actionEvent) {
        /** Set Items to Appointment Table */
        appointmentTable.setItems(AppointmentQuery.appByCurrentMonth());

        /**
         * Alert informs the user that the  month radio button
         * has been selected
         * (This was mainly used to help make sure the
         * radio button where working when there are no
         * appointments scheduled in the table)
         * */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("View By Current Month");
        alert.setContentText("Viewing appointments scheduled this month");
        alert.showAndWait();
        System.out.println("Month Radio Btn pressed");
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * When the application is loaded the 'View All' radio
         * button will be selected by default
         * displaying all appointments in the database
         * */
        viewAllAppointmentsRadioBtn.isSelected();

        /**
         * Binding Columns to Appointment Table
         * */
        appIDColumn.setCellValueFactory(new PropertyValueFactory<>("appId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("appTitle"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("appType"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("appLocation"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appStart"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appEnd"));
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<>("appContact"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("appCustomerId"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appDescription"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("appUserId"));

        /**
         * Set Items to Appointment Table
         * Gets appointment data from 'AppointmentQuery' using the 'obtainAllAppointments' method
         * */
        appointmentTable.setItems(AppointmentQuery.obtainAllAppointments());

    }
}
