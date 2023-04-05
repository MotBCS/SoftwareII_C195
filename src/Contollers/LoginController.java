package Contollers;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import Models.UserModel;
import Queries.AppointmentQuery;
import Queries.UserQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/** ----------------------------------------------------------------------------------------------------------------- */

/**
 * The 'LoginController' class, is a controller for the loginScreen.fxml view. Allows the user to login to the application,
 * For the project the user must use either 'test' or 'admin' to login otherwise the user will receive an error
 * saying 'No user found'.
 *
 * At the bottom of the login screen the user can also view their system time zone or click the 'quit' button to close
 * the application.
 * */

/**
 *
 * LAMBDA EXPRESSION #1
 * Location : (LoginController)
 * Lines : (290 - 293)
 *
 * */

public class LoginController implements Initializable {

    /**
     * LoginController variables
     * */
    @FXML
    public Button quitBtn; //Quit Button, close application
    @FXML
    public Label locationLabel; //Use for user time zone
    @FXML
    public Label passwordLabel; //Password label
    @FXML
    public Label usernameLabel; //Username label
    @FXML
    public Label errorMessageLabel; //Error message label above username and password text fields
    @FXML
    public TextField loginUsernameTextField; //Username text field
    @FXML
    public PasswordField loginPasswordTextField; //Password text field
    @FXML
    public Text LoginLabel; //Main login label
    @FXML
    public Button loginBtn; //Login button, allows user to access application after username and password validation

    public static int userId; //User Id, will be used to get userId of logged in user to check future appointments

    LocalDateTime timeNow = LocalDateTime.now(); //Used for checking for future appointments
    LocalDateTime timeNowAdd15 = LocalDateTime.now().plusMinutes(15); //Used for checking for future appointments

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent The quitBtn, allows user to quit the application
     * */
    public void quitApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) quitBtn.getScene().getWindow();
        stage.close();
    }
    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the login button is clicked the username and password
     *                    that the user enters is checked to see if their is a matching
     *                    user in the database.
     * */
    public void loginUser(ActionEvent actionEvent) throws IOException, SQLException{
        /**
         * Gets username from 'loginUsernameTextField' and stores it in the username variable
         * Gets password from 'loginPasswordTextField' and stores it in the password variable
         * If the username or password is enter in uppercase it will be converted to lowercase,
         * to ensure it matches the values in the database.
         * */
        String username = loginUsernameTextField.getText().toLowerCase(Locale.ROOT);
        String password = loginPasswordTextField.getText().toLowerCase(Locale.ROOT);
        /**
         * Uses the 'checkUserLogin' method to get the userId from the database,
         * based on whether the user enters 'test' which has a user Id of 1,
         * or 'admin' which has a user Id of 2.
         * */
        userId = UserQuery.checkUserLogin(username, password);
        /** If a user is found it will return a result that is greater than or equal to one
         * if no user is found it will return a 0 and the user will receive a 'No user found' alert*/
        if (userId >=1){
            System.out.println("In-Session User: " + loginUsernameTextField.getText()); // Prints to console the current in session user (Used for reference)
            Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            Scene scene = new Scene(root,452.0,400.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            /**
             *  If the users login is successful their login attempt will be record in a external
             * text file. That contains the username, the login time and date and whether the attempt
             * was successful or not
             * */
            UserLog_SuccessfulLogIN(username);
            /**
             * Checks if the logged in user has any upcoming appointments scheduled
             * */
            futureAppointments();
        }
        /**
         * If the username or password text fields are left empty or blank and the user attempts to login
         * they will receive an alert informing them of the empty or blank fields
         * */
        else {
            if (username.isEmpty() || username.isBlank()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("EnterUsername"));
                } else {
                    //Error message in English
                    errorMessageLabel.setText("Please Enter Username");
                }
            }
            if (password.isEmpty() || password.isBlank()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("EnterPassword"));
                } else {
                    //Error message in English
                    errorMessageLabel.setText("Please Enter Password");
                }
            }
            if (username.isEmpty() && password.isEmpty()) {
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("BothFieldsEmpty"));
                } else {
                    //Error message in English
                    errorMessageLabel.setText("Please Enter Username and Password");
                }
                /**
                 * If no user is found in the database a 0 will be returned and the user will
                 * receive an alert informing them that no user was found.
                 * */
            } else if (userId < 1) {
                UserLog_FailedLogIN(username);
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    //Error message in French
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
                    errorMessageLabel.setText(resourceBundle.getString("InvalidUser"));
                } else {
                    //Error message in English
                    errorMessageLabel.setText("No User Found");
                }
            }
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * Used to check if the logged in user has any upcoming appointments,
     * if there are any associated appointments the user will
     * received an alert to inform them
     * */
    private void futureAppointments() throws SQLException {
        boolean upcomingApps = false;
        ObservableList<AppointmentModel> getAppointmentByUserId = AppointmentQuery.appByUserID(userId);
        for (AppointmentModel appointmentModel: getAppointmentByUserId){
            /** Using a variable called 'appST' to store the getAppStart time */
            LocalDateTime appST = appointmentModel.getAppStart();
            if ((appST.isAfter(timeNow) || appST.isEqual(timeNowAdd15)) && (appST.isBefore(timeNowAdd15) || appST.isEqual(timeNow))){
                /**
                          * If the user has an upcoming appointment in the next 15 minutes
                          * they will receive an alert informing them. The alert contains:
                          * - Appointment ID
                          * - Appointment Date
                          * - Appointment Time
                 * */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Upcoming Appointment");
                alert.setContentText("Hello " + loginUsernameTextField.getText() + ", you have an upcoming appointment in 15 minutes.\nAppointment ID: " + appointmentModel.getAppId() + "\nAppointment Date and Time: " + appointmentModel.getAppStart());
                alert.showAndWait();
                System.out.println("Upcoming appointment found!");
                upcomingApps = true;
            }
        }
        if (!upcomingApps){
            /**
             * If the login user does not have any upcoming appointments in the
             * next 15 minutes, they will receive an alert informing them.
             * */
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No Upcoming Appointments");
            alert.setContentText("You have no upcoming appointments at the moment");
            alert.showAndWait();
            System.out.println("No upcoming appointments");
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */
    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param username Logs the username of the user in an external text file and
     *                 records the time of the successful login attempt
     * */
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

    /**
     * @param username Logs the username of the user in an external text file and
     *                 records the time of the failed login attempt
     * */
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

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * Sets the language based on the users zone. If the users system is set to 'FR' the application login screen
     * will be translated to french along will the errors.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * LAMBDA EXPRESSION #1
         * Location : (LoginController)
         * Lines : (290 - 293)
         *
         * Get users systems time zone and replace label text with location
         * */
        UserLocation userTimeZoneLocation = () -> {
            locationLabel.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
        };
        userTimeZoneLocation.userLocation();

        /**
         * When users system language is set to 'FR' french, the labels and prompt text on the login screen will be
         * converted to french.
         * */
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
