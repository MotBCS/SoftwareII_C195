package lamdaInterface;

/**
 * This interface is used with the
 * 1# lambda in the login controller.
 * This lambda is used to get the users
 * location based on their time
 * zone.
 *
 * The users time zone then
 * replaces the label text on the
 * login screen, displaying the users
 * time zone.
 * */
public interface UserLocation {
    void userLocation();
}
