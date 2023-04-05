package Contollers;

import Helper.JavaDatabaseConnection;
import Models.CountryModel;
import Models.StateProvinceModel;
import Queries.CountryQuery;
import Queries.CustomerQuery;
import Queries.StateProvinceQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** ----------------------------------------------------------------------------------------------------------------- */

/**
 * The 'CreateCustomerController' class allows the user to create a new customer,
 * that can be used to schedule appointments.
 *
 * When the user selects a country, it will filter the state/province combo box by selected country
 *
 * US - Filters States
 * UK - Filter Provinces
 * CANADA - Filter Provinces
 *
 * */
public class CreateCustomerController implements Initializable {

    /** Buttons, Text-Fields, Combo Boxes */
    public TextField addCustomerPhoneNoText;
    public ComboBox<CountryModel>addCustomerCountryComboBox; //filtered by country combo box
    public ComboBox<StateProvinceModel>addCustomerStateProvinceComboBox; //filters state province combo box
    public TextField addCustomerIDText;
    public TextField addCustomerNameText;
    public TextField addCustomerAddressText;
    public TextField addCustomerPostalCodeText;
    public Button addCustomerCancelBtn;
    public Button saveBtn;

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'cancel' button is clicked the user will be
     *                    brought back to the main customer screen. Where they
     *                    can view all customers in a table
     * */
    public void toCustomerMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
        Stage stage = (Stage) addCustomerCancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,1019.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user clicks the save button, the program
     *                    will check that there are not any empty value
     *                    fields, If all fields contain a value the
     *                    program will save and the new customer will
     *                    be added to the customer table.
     * */
    public void saveAddCustomer(ActionEvent actionEvent) {
        try {
            JavaDatabaseConnection.openConnection();
            /**
             * If customer name text field is empty, user will receive an error
             * informing them of the empty value field.
             * */
            if (addCustomerNameText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer name");
                alert.showAndWait();
                return;
            }
            /** ------------------------------------------------------------------- */
            /**
             * If phone number text field is empty, user will receive an error
             * informing them of the empty value field.
             * */
            else if (addCustomerPhoneNoText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer phone number");
                alert.showAndWait();
                return;
            }

            /** ------------------------------------------------------------------- */
            /**
             * If address text field is empty, user will receive an error
             * informing them of the empty value field.
             * */
            else if (addCustomerAddressText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer address");
                alert.showAndWait();
                return;
            }

            /** ------------------------------------------------------------------- */
            /**
             * If postal code text field is empty, user will receive an error
             * informing them of the empty value field.
             * */
            else if (addCustomerPostalCodeText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer name");
                alert.showAndWait();
                return;
            }

            /** ------------------------------------------------------------------- */
            /**
             * If state/province combo box is null, user will receive an error
             * informing them of the empty value field.
             * */
            else if (addCustomerStateProvinceComboBox.getValue() == null){
                CountryModel countryModel = addCustomerCountryComboBox.getValue();
                if (countryModel == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Unable to create new customer");
                    alert.setContentText("Empty customer combo box");
                    alert.showAndWait();
                    return;
                }
            }

            /** ------------------------------------------------------------------- */

            else {
                /**
                 * If all value fields contain a value, it will get all the users
                 * input and execute the create customer query, saving the users
                 * input and populating the customer table with the newly created
                 * customer.
                 * */
                String customer_Name = addCustomerNameText.getText(); //Customer Name
                String customer_Address = addCustomerAddressText.getText(); //Customer Address
                String customer_PostalCode = addCustomerPostalCodeText.getText(); //Customer Postal Code
                String customer_PhoneNumber = addCustomerPhoneNoText.getText(); // Customer phone number
                StateProvinceModel stateProvinceModel = addCustomerStateProvinceComboBox.getValue(); // Customer division
                int customer_StateProvinceId = stateProvinceModel.getStateProvinceId();
                /** Get 'createNewCustomer' method from customer query */
                CustomerQuery.createNewCustomer(
                        customer_Name,
                        customer_Address,
                        customer_PostalCode,
                        customer_PhoneNumber,
                        customer_StateProvinceId
                );
            }

            /** ------------------------------------------------------------------- */

            /**
             * After valid customer is saved, the user will be brought back to the main
             * customer menu.
             * */
            Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            Scene scene = new Scene(root,1019.0,720.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException exception) {
            System.out.println("Unable to create new customer");
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the user selects a country in the country combo box,
     *                    it will user the country Id to filter the state/province
     *                    combo box.
     *
     *                    - US -> Displays US states only
     *                    - UK -> Displays UK provinces only
     *                    - CANADA -> Display canadian provinces only
     * */
    public void filterByCountry(ActionEvent actionEvent) {
        CountryModel countryModel = addCustomerCountryComboBox.getValue();
        try {
            addCustomerStateProvinceComboBox.setItems(StateProvinceQuery.obtainDivisionByCountryId(countryModel.getCountryId()));
        } catch (SQLException exception) {
            System.out.println("Unable to filter state/province by country Id");
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * Populates the country combo box on start up.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(CountryQuery.obtainAllCountries()); //Set Items to combo box

    }
}
