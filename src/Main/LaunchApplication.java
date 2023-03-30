package Main;

import Helper.JavaDatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Mya Thomas
 *
 * */

public class LaunchApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource("/Views/loginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 452.0, 400.0);
        stage.initStyle(StageStyle.UNDECORATED);// Remove window header
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args){
        Locale france = new Locale("fr", "FR");

        //Check language of user system, if true go into properties file and get values
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(resourceBundle.getString("Login"));
        }


        /** Opens database before application is launched */
        JavaDatabaseConnection.openConnection();

        /** This method launches the application and loads up GUIs */
        launch(args);

        /** Closes database after application is closed */
        JavaDatabaseConnection.closeConnection();
    }
}
