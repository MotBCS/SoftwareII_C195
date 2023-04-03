package Contollers;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import Models.UserModel;
import Queries.AppointmentQuery;
import Queries.UserQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lamdaInterface.UserLocation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginController implements Initializable {
    public TextField locationField;
    public Button quitBtn;
    public Label locationLabel;
    public Label passwordLabel;
    public Label usernameLabel;
    public Label errorMessageLabel;
    public TextField loginUsernameTextField;
    public PasswordField loginPasswordTextField;
    public Text LoginLabel;
    public Button loginBtn;
    public static int userId;

    private LocalDateTime plus15 = LocalDateTime.now().plusMinutes(15);
    private LocalDateTime minus15 = LocalDateTime.now().minusMinutes(15);
    private LocalDateTime appST;
    private LocalDateTime appTime;
    private boolean checkAppTime;
    private int appId;

    public void quitApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) quitBtn.getScene().getWindow();
        stage.close();
    }

    public void loginUser(ActionEvent actionEvent) throws IOException, SQLException{
        String username = loginUsernameTextField.getText();
        String password = loginPasswordTextField.getText();
        userId = UserQuery.checkUserLogin(username, password);
        if (userId >=1){
            System.out.println("In-Session User: " + loginUsernameTextField.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            Scene scene = new Scene(root,452.0,400.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            UserLog_SuccessfulLogIN(username);
            futureAppointments();
        }
        else {
            if (username.isEmpty() || username.isBlank()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("EnterUsername"));
                } else {
                    errorMessageLabel.setText("Please Enter Username");
                }
            }
            if (password.isEmpty() || password.isBlank()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("EnterPassword"));
                } else {
                    errorMessageLabel.setText("Please Enter Password");
                }
            }
            if (username.isEmpty() && password.isEmpty()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("BothFieldsEmpty"));
                } else {
                    errorMessageLabel.setText("Please Enter Username and Password");
                }
            } else if (userId < 1) {
                UserLog_FailedLogIN(username);
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("InvalidUser"));
                } else {
                    errorMessageLabel.setText("No User Found");
                }
            }
        }
    }

    private void futureAppointments() throws SQLException {
        for (AppointmentModel appointmentModel : AppointmentQuery.obtainAllAppointments()){
            appST = (appointmentModel.getAppStart());
            if ((appST.isBefore(plus15)) && (appST.isAfter(plus15))){
                appId = appointmentModel.getAppId();
                appTime = appST;
                checkAppTime = true;
            }
        }
        if (checkAppTime != false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Upcoming Appointment");
            alert.setContentText("You have a appointment in 15 minutes,\n Appointment ID: " + appId + ", starts at: " + appTime.format(DateTimeFormatter.ofPattern("yyyy-dd-MM hh:mm")));
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("No Upcoming Appointments");
                alert.setContentText("You have no upcoming appointments at the moment");
                alert.showAndWait();
        }
    }


    public static void UserLog_SuccessfulLogIN(String username){
        try {
            String userLog = "login_activity.txt";
            File file = new File(userLog);
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();

            printWriter.println("*** User: " + username + " Login Time: " + Timestamp.valueOf(localDateTime) + " Attempt: Successful ***\n");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Unable to obtain login details");
        }
    }

    public static void UserLog_FailedLogIN(String username){
        try {
            String userLog = "login_activity.txt";
            File file = new File(userLog);
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();

            printWriter.println("*** User: " + username + " Login Time: " + Timestamp.valueOf(localDateTime) + " Attempt: Failed ***\n");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Unable to obtain login details");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         *  Lambda #1
         * Get users systems time zone and replace label text with location
         * */
        UserLocation userTimeZoneLocation = () -> {
            locationLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
        };
        userTimeZoneLocation.userLocation();

        ResourceBundle rb = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")){
            /** Pane Header */
            LoginLabel.setText(rb.getString("Login")); //Login Header

            /** Button Translation */
            quitBtn.setText(rb.getString("Quit")); //Quit Button
            loginBtn.setText(rb.getString("Login")); //Login Button

            /** Text Field Translation */
            usernameLabel.setText(rb.getString("Username")); //Username label
            loginUsernameTextField.setPromptText(rb.getString("Username")); //Username prompt text
            passwordLabel.setText(rb.getString("Password")); //Password label
            loginPasswordTextField.setPromptText(rb.getString("Password")); //Password prompt text
        }
    }
}
