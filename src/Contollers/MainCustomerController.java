package Contollers;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import Models.CustomerModel;
import Queries.AppointmentQuery;
import Queries.CustomerQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** ----------------------------------------------------------------------------------------------------------------- */

/**
 * The 'mainCustomerController' class allows the user to view
 * all customers, as well as navigate to the modify and create customer
 * screen through buttons. Users can also delete customers from
 * this screen as long as the customer does not have any
 * associated appointments. If they do the user will be asked if they
 * are sure they want to delete the selected customer along with their associated appointments
 *
 * @author Mya Thomas
 *
 * */
public class MainCustomerController implements Initializable {

    /** Customer Table and Table Columns */
    public TableView<CustomerModel>customerTable; //Customer Table view (Use for reference)
    public TableColumn<CustomerModel, Integer>customerIDColumn;
    public TableColumn<CustomerModel, String>customerTitleColumn;
    public TableColumn<CustomerModel, String>customerAddressColumn;
    public TableColumn<CustomerModel, String>customerPostalCodeColumn;
    public TableColumn<CustomerModel, String>customerPhoneNoColumn;
    public TableColumn<CustomerModel, Integer>customerDivisionColumn;
    public TableColumn<CustomerModel, Integer>customerCountryColumn;

    /** Buttons */
    public Button deleteCustomerBtn;
    public Button modifyCustomerBtn;
    public Button addNewCustomerBtn;
    public Button backBtn;

    private CustomerModel obtainCustomer;
    ObservableList<CustomerModel> allCustomersList = CustomerQuery.obtainAllCustomers();
    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user clicks the 'back' button they are brought back
     *                    to the main menu where they can navigate to other screens
     *                    in the application. Such as the report or appointments screen.
     * */
    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user clicks the create new customer button, they will be taken
     *                    to another screen where they can create a new customer
     * */
    public void addNewCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AddNewCustomer.fxml"));
        Stage stage = (Stage) addNewCustomerBtn.getScene().getWindow();
        Scene scene = new Scene(root,600.0,505.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user selects the 'modify' button they will be brought ta
     *                    a screen where the user will be able to edit an existing customer
     *                    from the table.
     * */
    public void modifyExistingCustomer(ActionEvent actionEvent) throws IOException, SQLException {
        /**
         * User must select a customer from the table then click the modify button to edit selected
         * customer
         * */
        if (customerTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/ModifyExistingCustomerScreen.fxml"));
            fxmlLoader.load();
            ModifyCustomerController modifyCustomerController = fxmlLoader.getController();
            modifyCustomerController.obtainData(customerTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) modifyCustomerBtn.getScene().getWindow();
            Parent scene = fxmlLoader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();
        }
        else {
            /**
             * If the selection is null, the user will receive an alert
             * to inform them. User must select a customer from the
             * table to modify first, before button is clicked
             * */
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No customer Selected");
            alert.setContentText("Select an existing customer to modify");
            alert.showAndWait();
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'delete' button is clicked the user will
     *                    be able to delete a customer from the table.
     *                    If the selected customer has any associated
     *                    appointments the user will receive a warning
     *                    informing them of the selected customers
     *                    appointments, if the user selects 'OK' on the
     *                    warning they will be able to delete the customer,
     *                    along with the associated appointments.
     *                    If the user clicks 'cancel' on the warning,
     *                    the selected customer will not be removed from the table.
     * */
    public void deleteCustomer(ActionEvent actionEvent) throws IOException {
        /**
         * Set 'associatedAppointment' variable to 0;
         * Once an associated appointment has been found,
         * the variable will no longer be equal to 0.
         *
         * If the variable is greater than 0, user will
         * receive an warning informing them that the
         * selected customer has an appointment linked
         * to by customer id.
         * */
        int associatedAppointment = 0;
        ObservableList<AppointmentModel>allCustomerAppointments = AppointmentQuery.obtainAllAppointments();
        CustomerModel customerModel = customerTable.getSelectionModel().getSelectedItem();
        if (customerModel == null){
            /** (Alert) No customer selected from table */
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Select an existing customer to delete from the table");
            alert.showAndWait();
            return;
        }
        int obtainCustomer = customerTable.getSelectionModel().getSelectedItem().getCustomerId();
        for (AppointmentModel appointmentModel : allCustomerAppointments){
            int appointmentCustomerById = appointmentModel.getAppCustomerId();
            if (appointmentCustomerById == obtainCustomer){
                associatedAppointment++;
            }
        }
        if (associatedAppointment > 0){
            /**
             * Associated Appointment found
             * */
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setHeaderText("Selected Customer has associated appointments");
            alert2.setContentText("Selected customer has " + associatedAppointment + ", associated appointments\n\n" +
                    "Select 'OK' to delete customer from table along with associated appointments.\n" +
                    "Else, select cancel");
            Optional<ButtonType> choice = alert2.showAndWait();
            if (choice.get() == ButtonType.OK){
                for (AppointmentModel appointmentModel : allCustomerAppointments){
                    if (appointmentModel.getAppCustomerId() == obtainCustomer){
                        AppointmentQuery.deleteExistingAppointment(appointmentModel.getAppId());
                    }
                }
                CustomerQuery.deleteExistingCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomerId());
                Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
                alert3.setHeaderText("Customer Deleted Successfully");
                alert3.setContentText("Customer has been deleted from the table");
                alert3.showAndWait();
                allCustomersList = CustomerQuery.obtainAllCustomers();
                customerTable.setItems(allCustomersList);
                customerTable.refresh();
            }
            else if (choice.get() == ButtonType.CANCEL){
                return;
            }
            }
        if (associatedAppointment == 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Customer?");
            alert.setContentText("Are you sure you want to delete this customer?");
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.get() == ButtonType.OK){
                CustomerQuery.deleteExistingCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomerId());
                allCustomersList = CustomerQuery.obtainAllCustomers();
                customerTable.setItems(allCustomersList);
                customerTable.refresh();
            }
            /**
             * If user clicks the 'cancel' button they are brought
             * back to the main customer screen
             * */
            else if (choice.get() == ButtonType.CANCEL){
                return;
            }
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**
         * Customer Table
         * */
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId")); //Customer Id
        customerTitleColumn.setCellValueFactory(new PropertyValueFactory<>("customer_Name")); //Customer Name
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customer_Address")); //Customer Address
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customer_PostalCode")); //Customer Postal Code
        customerPhoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("customer_PhoneNumber")); //Customer Phone Number
        customerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("customer_StateProvinceName")); //Customer Division
        customerCountryColumn.setCellValueFactory(new PropertyValueFactory<>("customer_CountryName")); //Customer Country

        /**
         * Set Items to customer table
         * */
        customerTable.setItems(CustomerQuery.obtainAllCustomers()); //Set Items to table

    }
}
