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
import java.util.Locale;
import java.util.ResourceBundle;

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

    boolean validUser = false;

    public void quitApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) quitBtn.getScene().getWindow();
        stage.close();
    }

    LocalDateTime loginUserTime = LocalDateTime.now();
    ZonedDateTime localDateTimeToUser = loginUserTime.atZone(ZoneId.systemDefault());
    LocalDateTime loginUserTimeAdd = LocalDateTime.now().plusMinutes(15);
    public void loginUser(ActionEvent actionEvent) throws IOException {
        String username = loginUsernameTextField.getText();
        String password = loginPasswordTextField.getText();



    }

    public static void UserLog_SuccessfulLogIN(String UserUsername){
        try {
            String userLog = "login_activity.txt";
            File file = new File(userLog);
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();

            printWriter.println("*** User: " + UserUsername + " Login Time: " + Timestamp.valueOf(localDateTime) + " Attempt: Successful ***\n");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Unable to obtain login details");
        }
    }

    public static void UserLog_FailedLogIN(String UserUsername){
        try {
            String userLog = "login_activity.txt";
            File file = new File(userLog);
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();

            printWriter.println("*** User: " + UserUsername + " Login Time: " + Timestamp.valueOf(localDateTime) + " Attempt: Failed ***\n");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Unable to obtain login details");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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


        /** Get users systems time zone and replace label text with location */
        ZoneId usersLocalTimeZone = ZoneId.systemDefault();
        locationLabel.setText(String.valueOf(usersLocalTimeZone));

    }
}
