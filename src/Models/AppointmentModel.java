package Models;

import java.time.LocalDateTime;

public class AppointmentModel {
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

    public AppointmentModel(
            int appId,
            String appTitle,
            String appDescription,
            int appContact,
            String appType,
            LocalDateTime appStart,
            LocalDateTime appEnd,
            int appCustomerId,
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
        this.appLocation = appLocation;
        this.appointmentContactName = appointmentContactName;
    }

    public int getAppId() {
        return appId;
    }
    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppTitle() {
        return appTitle;
    }
    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppDescription() {
        return appDescription;
    }
    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public int getAppContact() {
        return appContact;
    }
    public void setAppContact(int appContact) {
        this.appContact = appContact;
    }

    public String getAppType() {
        return appType;
    }
    public void setAppType(String appType) {
        this.appType = appType;
    }

    public LocalDateTime getAppStart() {
        return appStart;
    }
    public void setAppStart(LocalDateTime appStart) {
        this.appStart = appStart;
    }

    public LocalDateTime getAppEnd() {
        return appEnd;
    }
    public void setAppEnd(LocalDateTime appEnd) {
        this.appEnd = appEnd;
    }

    public int getAppCustomerId() {
        return appCustomerId;
    }
    public void setAppCustomerId(int appCustomerId) {
        this.appCustomerId = appCustomerId;
    }

    public int getAppUserId() {
        return appUserId;
    }
    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getAppLocation() {
        return appLocation;
    }
    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    public String getAppointmentContactName() {
        return appointmentContactName;
    }
    public void setAppointmentContactName(String appointmentContactName) {
        this.appointmentContactName = appointmentContactName;
    }
}
