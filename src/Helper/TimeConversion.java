package Helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimeConversion {

    public static LocalTime Company_ltStart(){
        LocalTime operationHours_Open = LocalTime.of(8,0);
        ZoneId EST = ZoneId.of("America/New_York");
        ZoneId userLZ = ZoneId.systemDefault();
        LocalDateTime companyEST = LocalDateTime.of(LocalDate.now(), operationHours_Open);
        LocalDateTime companyLocal = companyEST.atZone(EST).withZoneSameInstant(userLZ).toLocalDateTime();
        LocalTime companyStartLocal = companyLocal.toLocalTime();
        return companyStartLocal;
    }

    public static LocalTime Company_ltEnd(){
        LocalTime operationHours_Close = LocalTime.of(22,0);
        ZoneId EST = ZoneId.of("America/New_York");
        ZoneId userLZ = ZoneId.systemDefault();
        LocalDateTime companyEndDateTime = LocalDateTime.of(LocalDate.now(), operationHours_Close);
        LocalDateTime companyLocalDateTime = companyEndDateTime.atZone(EST).withZoneSameInstant(userLZ).toLocalDateTime();
        LocalTime companyEndLocal = companyLocalDateTime.toLocalTime();
        return companyEndLocal;
    }

    public static boolean operationCompanyTime(LocalDateTime appStart, LocalDateTime appEnd){
        ZoneId userLocalZone = ZoneId.systemDefault();
        ZoneId companyZone_EST = ZoneId.of("America/New_York");

        LocalDateTime startEST = appStart.atZone(userLocalZone).withZoneSameInstant(companyZone_EST).toLocalDateTime();
        LocalDateTime endEST = appEnd.atZone(userLocalZone).withZoneSameInstant(companyZone_EST).toLocalDateTime();
        LocalDateTime companyStart_EST = startEST.withHour(8).withMinute(0);
        LocalDateTime companyEnd_EST = endEST.withHour(22).withMinute(0);

        if (startEST.isBefore(companyStart_EST) || endEST.isAfter(companyEnd_EST)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Time Conflict");
            alert.setContentText("Conflict start or end time (Business Hours: 8AM to 10PM)");
            alert.showAndWait();
            return true;
        }
        else {
            return false;
        }
    }

    public static ObservableList<LocalTime>appTimeComboBoxPopulation(){
        ObservableList<LocalTime>appTimeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22, 0);

        while (start.isBefore(end.plusSeconds(1))){
            appTimeList.add(start);
            start = start.plusMinutes(15);
        }
        return appTimeList;
    }
}
