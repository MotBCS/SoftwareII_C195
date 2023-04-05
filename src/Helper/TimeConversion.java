package Helper;

import Models.AppointmentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/** ----------------------------------------------------------------------------------------------------------------- */

/**
 * This class contains the methods to help with time conversion,
 * populating the start and end combo boxes and the business operation
 * methods to help ensure appointments are scheduled between operation
 * hours 8AM to 10PM
 * */
public class TimeConversion {

    /**
     * EST start time for company operation hours 8AM
     * */
    public static LocalTime Company_ltStart(){
        LocalTime operationHours_Open = LocalTime.of(8,0); //Company Start Time
        ZoneId EST = ZoneId.of("America/New_York");//Eastern Standard Time
        ZoneId userLZ = ZoneId.systemDefault();
        LocalDateTime companyEST = LocalDateTime.of(LocalDate.now(), operationHours_Open);
        LocalDateTime companyLocal = companyEST.atZone(EST).withZoneSameInstant(userLZ).toLocalDateTime();
        LocalTime companyStartLocal = companyLocal.toLocalTime();
        return companyStartLocal;
    }
    /** ----------------------------------------------------------------------------------------------------------------- */
    /**
     * EST end time for company operation hours 10PM (22:00 Military Time)
     * */
    public static LocalTime Company_ltEnd(){
        LocalTime operationHours_Close = LocalTime.of(22,0); //Company End Time
        ZoneId EST = ZoneId.of("America/New_York");//Eastern Standard Time
        ZoneId userLZ = ZoneId.systemDefault();
        LocalDateTime companyEndDateTime = LocalDateTime.of(LocalDate.now(), operationHours_Close);
        LocalDateTime companyLocalDateTime = companyEndDateTime.atZone(EST).withZoneSameInstant(userLZ).toLocalDateTime();
        LocalTime companyEndLocal = companyLocalDateTime.toLocalTime();
        return companyEndLocal;
    }
    /** ----------------------------------------------------------------------------------------------------------------- */
    /**
     * Business operation hours
     * */
    public static boolean operationCompanyTime(LocalDateTime appStart, LocalDateTime appEnd){
        /**
         * Variable 'userLocalZone' store the zone Id of the user for later use
         * */
        ZoneId userLocalZone = ZoneId.systemDefault();

        /**
         * Variable 'companyZone_EST' stores the zone Id of the company (Eastern Standard Time)
         * for later use
         * */
        ZoneId companyZone_EST = ZoneId.of("America/New_York");//Eastern Standard Time


        /**
         * Converts business hours in EST to Users time zone
         * */
        LocalDateTime startEST = appStart.atZone(userLocalZone).withZoneSameInstant(companyZone_EST).toLocalDateTime();
        LocalDateTime endEST = appEnd.atZone(userLocalZone).withZoneSameInstant(companyZone_EST).toLocalDateTime();
        LocalDateTime companyStart_EST = startEST.withHour(7).withMinute(59);
        LocalDateTime companyEnd_EST = endEST.withHour(22).withMinute(1);

        /**
         * If appointment starts before company business hours
         * or ends after company business hours, user will receive an
         * alert to inform them.
         * */
        if (startEST.isBefore(companyStart_EST) || endEST.isAfter(companyEnd_EST)){
            LocalTime start = Helper.TimeConversion.Company_ltStart();
            LocalTime end = Helper.TimeConversion.Company_ltEnd();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Time Conflict");
            alert.setContentText("Conflict start or end time (Business Hours: 8AM to 10PM EST) \n Appointment must be between " + Company_ltStart().format(DateTimeFormatter.ofPattern("HH:mm")) + " to " + Company_ltEnd().format(DateTimeFormatter.ofPattern("HH:mm")) + " Local User Time");
            alert.showAndWait();
            System.out.println("Schedule appointment between " + start + " and " + end + " local time");
            return true;
        }
        else {
            return false;
        }
    }
    /** ----------------------------------------------------------------------------------------------------------------- */
    /**
     * Used to populate the start and end time combo boxes for creating
     * and modifying appointments
     * */
    public static ObservableList<LocalTime>appTimeComboBoxPopulation(){
        /**
         * Creates an empty list to store the appointment times,
         * used to populate start and end timer combo boxes
         * */
        ObservableList<LocalTime>appTimeList = FXCollections.observableArrayList();
        /**
         * Start Time combo box starts at 6:00 AM and 15 minutes is added until
         * the end time is reached at 10:00 PM
         * */
        LocalTime AppointmentStartTime = LocalTime.of(8, 0); //Combo Box Start
        LocalTime AppointmentEndTime = LocalTime.of(22, 0); //Combo Box End

        while (AppointmentStartTime.isBefore(AppointmentEndTime.plusSeconds(2))){
            appTimeList.add(AppointmentStartTime);

            /**
             * Increments appointment time by 15 minutes
             * */
            AppointmentStartTime = AppointmentStartTime.plusMinutes(15);
        }
        return appTimeList;
    }

    /**
     *
     * Note:
     *  ////
     * */
}
