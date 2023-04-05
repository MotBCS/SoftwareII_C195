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

/**
 * This class is used to launch the
 * application. When the application is launched
 * the user will be brought to the main login screen,
 * where they must enter a valid username and
 * password to access the rest of the application
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

        /**
         * Reference From CI Software Team Resource Repo
         * Referenced to help better understand UTC time conversion
         * Also, used to check the current time of the SQL database.
         * */
        Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime localDateTime = timeStamp.toLocalDateTime();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime UTC_ZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime localDateTime_IN = UTC_ZonedDateTime.toLocalDateTime();
        ZonedDateTime zonedDateTime_OUT = localDateTime_IN.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime_OUT_ToLocalTime = zonedDateTime_OUT.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime localDateTimeFinal = zonedDateTime_OUT_ToLocalTime.toLocalDateTime();

        /** Prints system time and UTC time conversion to console */
        System.out.println(timeStamp);
        System.out.println(localDateTime);
        System.out.println(zonedDateTime);
        System.out.println(UTC_ZonedDateTime);
        System.out.println(localDateTime_IN);
        System.out.println(zonedDateTime_OUT);
        System.out.println(zonedDateTime_OUT_ToLocalTime);
        System.out.println(localDateTimeFinal);


        /** Opens database before application is launched */
        JavaDatabaseConnection.openConnection();

        /** This method launches the application and loads up GUIs */
        launch(args);

        /** Closes database after application is closed */
        JavaDatabaseConnection.closeConnection();
    }
}
