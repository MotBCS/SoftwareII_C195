package Models;

import java.time.LocalDateTime;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the appointment model */
public class AppointmentModel {

    /** Class variables */
    private int appId;
    private String appTitle;
    private String appDescription;
    private int appContact;
    private String appType;
    private LocalDateTime appStart;
    private LocalDateTime appEnd;
    private int appCustomerId;
    private int appUserId;
    private String appLocation;
    private String appointmentContactName;

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** Appointment Model Constructor */

    public AppointmentModel(
            int appId,
            String appTitle,
            String appDescription,
            int appContact,
            String appType,
            LocalDateTime appStart,
            LocalDateTime appEnd,
            int appCustomerId,
            int appUserId,
            String appLocation
    ){
        this.appId = appId;
        this.appTitle = appTitle;
        this.appDescription = appDescription;
        this.appContact = appContact;
        this.appType = appType;
        this.appStart = appStart;
        this.appEnd = appEnd;
        this.appCustomerId = appCustomerId;
        this.appUserId = appUserId;
        this.appLocation = appLocation;

    }

    /** Appointment Model Constructor */

    public AppointmentModel(
            int appId,
            String appTitle,
            String appDescription,
            int appContact,
            String appType,
            LocalDateTime appStart,
            LocalDateTime appEnd,
            int appCustomerId,
            int appUserId,
            String appLocation,
            String appointmentContactName
    ){
        this.appId = appId;
        this.appTitle = appTitle;
        this.appDescription = appDescription;
        this.appContact = appContact;
        this.appType = appType;
        this.appStart = appStart;
        this.appEnd = appEnd;
        this.appCustomerId = appCustomerId;
        this.appUserId = appUserId;
        this.appLocation = appLocation;
        this.appointmentContactName = appointmentContactName;
    }

    /** Appointment Model Constructor */

    public AppointmentModel(
            int appId,
            LocalDateTime appStart
    ){
        this.appId = appId;
        this.appStart = appStart;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */


    /**
     * @return appointment Id
     * */
    public int getAppId() {
        return appId;
    }
    /**
     * @param appId sets appointment ID
     * */
    public void setAppId(int appId) {
        this.appId = appId;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return  Appointment Title
     * */
    public String getAppTitle() {
        return appTitle;
    }
    /**
     * @param appTitle Appointment Title
     * */
    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Appointment Description
     * */
    public String getAppDescription() {
        return appDescription;
    }
    /**
     * @param appDescription  Appointment Description
     * */
    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    public int getAppContact() {
        return appContact;
    }
    public void setAppContact(int appContact) {
        this.appContact = appContact;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Appointment Type
     * */
    public String getAppType() {
        return appType;
    }
    /**
     * @param appType Appointment Type
     * */
    public void setAppType(String appType) {
        this.appType = appType;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Appointment Start Time
     * */
    public LocalDateTime getAppStart() {
        return appStart;
    }
    /**
     * @param appStart Appointment Start Time
     * */
    public void setAppStart(LocalDateTime appStart) {
        this.appStart = appStart;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Appointment End Time
     * */
    public LocalDateTime getAppEnd() {
        return appEnd;
    }
    /**
     * @param appEnd Appointment
     * */
    public void setAppEnd(LocalDateTime appEnd) {
        this.appEnd = appEnd;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return consumers Id (Customers Id)
     * */
    public int getAppCustomerId() {
        return appCustomerId;
    }
    /**
     * @param appCustomerId consumer Id (Customer Id)
     * */
    public void setAppCustomerId(int appCustomerId) {
        this.appCustomerId = appCustomerId;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return User ID
     * */
    public int getAppUserId() {
        return appUserId;
    }
    /**
     * @param appUserId User ID
     * */
    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Appointment Location
     * */
    public String getAppLocation() {
        return appLocation;
    }
    /**
     * @param appLocation Appointment Location
     * */
    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Contact Name
     * */
    public String getAppointmentContactName() {
        return appointmentContactName;
    }
    /**
     * @param appointmentContactName Name of Contact
     * */
    public void setAppointmentContactName(String appointmentContactName) {
        this.appointmentContactName = appointmentContactName;
    }
}
