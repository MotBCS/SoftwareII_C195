package Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
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

    public void loginUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
