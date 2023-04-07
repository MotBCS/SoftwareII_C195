Software II C195 - Appointment Scheduling Application

Purpose Of The Application:
    The purpose of this application is to allow users,
    schedule appointments. The user will be able to
    create, modify, view and delete customers and
    appointments.
-----------------------------------------------------

Student Information:
    Author: Mya Thomas
    Email: mth1759@wgu.edu
    Application Version: Software II - Advanced Java Concepts QAM2
    Date: March 30, 2023

-----------------------------------------------------

    IDE: IntelliJ IDEA Community Edition 2021.1.3
    JDK: Java SE 11.0.11
    JavaFX: javafx-sdk-17.0.1
    MySQL Driver Version: mysql-connector-java-8.0.25




How to use the application:
-------------------------------------------------------
Login Screen:
    When the application is launched, user will be brought to the login screen.
    Please use the following to login.

    Username: "test" OR "TEST"
    Password: "test" OR "TEST"

    Or

    Username: "admin" OR "ADMIN"
    Password: "admin" OR "ADMIN"

-------------------------------------------------------------------------------------------------------------
Main Menu Screen:
    The main menu screen allows the user to navigate to the three main application screens.
    The user can navigate to:
        - Appointments
        - Customers
        - Reports
    The user can also click the logout button to return to the login screen where they can
    quit or change to a different user.

-------------------------------------------------------------------------------------------------------------
Main Appointment Screen:
    The main appointment screen will allow users to view, create, modify and delete appointments.
    The appointment table is set to view all appointments by default, but the user can also view
    appointments by the current month or week.

    CREATING AN APPOINTMENT
    -----------------------
    User can click the 'Create Appointment' to add a new appointment to the table. When the navigation
    button is clicked the user will be redirected to a new screen that will contain empty text field
    where the user can fill in information about the appointment such as title, type, location,
    description, start and end date, contact, user, start and end time. When the user clicks save
    the application will check for empty text/value fields, clashing appointments, and time/date
    conflicts.

    OVERLAPPING APPOINTMENT TRIGGER
    -------------------------------
    (Triggered by appointments overlapping with SAME customer ID on SAME day)

    The overlapping appointment alert is triggered when a customer has created an appointment and saves it
    to the table, then creates another appointment on the same day, that clashes with their previously
    scheduled appointment. Since a customer can not attend two different meetings at once, the trigger looks
    at the customer ID to check for overlapping appointments on the same day. If a customer has a appointment
    scheduled on one day and another scheduled on a different day, the overlapping appointment alert will
    not be triggered.

    MODIFYING AN APPOINTMENT
    -------------------------
    User can modify existing appointments by first selecting an appointment in the table by clicking it,
    then pressing the 'modify' button. The user will be brought to a new screen where they can edit the
    selected appointment. The user will receive an alert if the modified appointment clashes with another
    appointment, or if there is a time or date conflict.

    DELETING AN APPOINTMENT
    -----------------------
    User can also delete appointments from the table, by first selecting an existing appointment then clicking
    the 'delete' button.

-------------------------------------------------------------------------------------------------------------
Customer Screen:
    The main customer screen can allow the user to view, create, modify and delete customers.

    CREATING A CUSTOMER
    -------------------
    When the user clicks the 'Create Customer' button, they will be redirected to a new screen
    where they are able to create a new customer. The user can enter a name, address, postal,
    phone number, state/province (division), and country.
    When selecting a country and division for a new customer, the country combo box will filter
    the state/province (division) combo box by country.

    MODIFYING A CUSTOMER
    --------------------
    To modify a customer, the user must first select a customer in the table then click the modify button.
    The user will be brought to a new screen where they can edit the selected customers information.

    DELETING A CUSTOMER
    --------------------
    User can also delete a customer from the table, by selecting the customer then clicking the delete
    button. If the selected customer has any associated appointments, the user will receive a warning
    to inform them about the associated appointments, if the user selects the 'OK' button on the warning
    the customer and their associated appointments will be deleted. If the user selects 'cancel' on the
    warning dialog box, the alert will close and the customer will still exist in the table.

-------------------------------------------------------------------------------------------------------------
Report Screen:
    The report screen allows the user to view appointments by selected contact, total appointments by month
    and type. As well as total number of customers per state/province (division)

    ADDITIONAL REPORT (A3F)
    -----------------------
    For the additional report of my choice, I decided to create a report table that displays the total customers
    per each state/province that are currently register within the application. The will display all registered
    customers divisions regardless of the country.