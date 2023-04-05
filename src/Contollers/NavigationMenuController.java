package Contollers;

import Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/** ----------------------------------------------------------------------------------------------------------------- */

/**
 * The 'NavigationMenuController' is a controller for the main
 * menu. When the user logs in the will be directed to this screen
 * where they can navigate to the appointment, customer or report
 * screen.
 * */
public class NavigationMenuController implements Initializable {

    /** Buttons */
    @FXML
    public Button mainAppointmentsBtn;
    @FXML
    public Button mainLogoutBtn;
    @FXML
    public Button mainCustomerBtn;
    @FXML
    public Button mainReportBtn;
    @FXML
    public Text mainMenuLabel;

    public static UserModel inSessionUser;
    public Label inSessionUserLabel;

//    public static void sendInSessionUser(String username) {
//        UserModel userModel = new UserModel(inSessionUser.getUsername());
//        inSessionUser = userModel;
//        System.out.println("In session user: " + userModel);
//    }

    /** ----------------------------------------------------------------------------------------------------------------- */

//    public static void sendUserInformation(UserModel obtainUserByID) {
//        UserModel userModel = new UserModel(obtainUserByID.getUserId(), obtainUserByID.getUsername());
//        inSessionUser = userModel;
//        System.out.println("In session user: " + userModel);
//    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user clicks the appointment navigation
     *                    button they will be brought to the main
     *                    appointment screen, where they can create,
     *                    modify or delete appointments.
     * */
    public void appointmentBtnHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentMenuScreen.fxml"));
        Stage stage = (Stage) mainAppointmentsBtn.getScene().getWindow();
        Scene scene = new Scene(root,868.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user clicks the logout button,
     *                    they will be asked if they are sure they want
     *                    to logout, then if the user clicks 'OK'
     *                    they will be brought back to the login screen
     *                    where they can log back into the application
     *                    or close it.
     * */
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

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent If the user clicks the customer navigation button
     *                    they will be taken to the main customer screen
     *                    where they can add, modify or delete customers.
     * */
    public void customerBtnHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
        Stage stage = (Stage) mainCustomerBtn.getScene().getWindow();
        Scene scene = new Scene(root,1019.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent If the user clicks the report navigation button,
     *                    they will be brought to the report screen, where
     *                    the user can view appointments by selected contact
     *                    Id, or view appointment total by month and type. Also,
     *                    the user can view total customers per state/province.
     * */
    public void reportBtnHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ReportsScreen.fxml"));
        Stage stage = (Stage) mainReportBtn.getScene().getWindow();
        Scene scene = new Scene(root,865.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
