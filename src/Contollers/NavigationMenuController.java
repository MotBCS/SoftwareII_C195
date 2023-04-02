package Contollers;

import Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NavigationMenuController implements Initializable {
    public Button mainAppointmentsBtn;
    public Button mainLogoutBtn;
    public Button mainCustomerBtn;
    public Button mainReportBtn;
    public Text mainMenuLabel;

    public static UserModel inSessionUser;
    public Label inSessionUserLabel;

    public static void sendUserInformation(UserModel obtainUserByID) {
        UserModel userModel = new UserModel(obtainUserByID.getUserId(), obtainUserByID.getUsername());
        inSessionUser = userModel;
        System.out.println("In session user: " + userModel);
    }

    public void appointmentBtnHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
        Stage stage = (Stage) mainAppointmentsBtn.getScene().getWindow();
        Scene scene = new Scene(root,868.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void logoutBtnHandler(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Logout?");
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> choice = alert.showAndWait();
        if (choice.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/loginScreen.fxml"));
            Stage stage = (Stage) mainLogoutBtn.getScene().getWindow();
            Scene scene = new Scene(root, 452.0, 400.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            System.out.println(" ** User Logged out successfully ** ");
        }
    }

    public void customerBtnHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
        Stage stage = (Stage) mainCustomerBtn.getScene().getWindow();
        Scene scene = new Scene(root,1019.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void reportBtnHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ReportsScreen.fxml"));
        Stage stage = (Stage) mainReportBtn.getScene().getWindow();
        Scene scene = new Scene(root,865.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
