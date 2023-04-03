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
        }
    }

    public void deleteAppointment(ActionEvent actionEvent) {
        AppointmentModel obtainApp = appointmentTable.getSelectionModel().getSelectedItem();
        if (obtainApp == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Select an existing appointment from the table to delete");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Delete Selected Appointment?");
            alert.setContentText("Delete selected appointment " + obtainApp.getAppId() + ", from appointment table?");
            alert.showAndWait();
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.OK){
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setHeaderText("Appointment has been successfully deleted");
                alert2.setContentText("Appointment" + appointmentTable.getSelectionModel().getSelectedItem().getAppId() + " has been deleted");
                AppointmentQuery.deleteExistingAppointment(appointmentTable.getSelectionModel().getSelectedItem().getAppId());
                allAppList = AppointmentQuery.obtainAllAppointments();
                appointmentTable.setItems(allAppList);
                appointmentTable.refresh();
            }
            else if (choice.get() == ButtonType.CANCEL){
                alert.close();
            }
        }
    }

    public void onActionViewAllRadioBtn(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentQuery.obtainAllAppointments());
        System.out.println("All Radio Btn pressed");
    }

    public void onActionViewByWeekRadioBtn(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentQuery.appByCurrentWeek());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("View By Current Week");
        alert.setContentText("Viewing appointments scheduled this week");
        alert.showAndWait();
        System.out.println("Week Radio Btn pressed");
    }

    public void onActionViewByMonthRadioBtn(ActionEvent actionEvent) {
        appointmentTable.setItems(AppointmentQuery.appByCurrentMonth());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("View By Current Month");
        alert.setContentText("Viewing appointments scheduled this month");
        alert.showAndWait();
        System.out.println("Month Radio Btn pressed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewAllAppointmentsRadioBtn.isSelected();

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

        appointmentTable.setItems(AppointmentQuery.obtainAllAppointments());

    }
}
