package Contollers;

import Helper.JavaDatabaseConnection;
import Models.CountryModel;
import Models.CustomerModel;
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
 * @author Mya Thomas
 * */

public class ModifyCustomerController implements Initializable {

    /** Buttons, Text-Fields, Combo Boxes */
    public TextField modifyCustomerPhoneNoText;
    public ComboBox<CountryModel> modifyCustomerCountryComboBox;
    public ComboBox<StateProvinceModel> modifyCustomerStateProvinceComboBox;
    public TextField modifyCustomerIDText;
    public TextField modifyCustomerNameText;
    public TextField modifyCustomerAddressText;
    public TextField modifyCustomerPostalCodeText;
    public Button modifyCustomerCancelBtn;
    public Button saveBtn;
    private CustomerModel obtainConsumer;

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
        CountryModel countryModel = modifyCustomerCountryComboBox.getValue(); //Customer Country Combo Box
        try {
            modifyCustomerStateProvinceComboBox.setItems(StateProvinceQuery.obtainDivisionByCountryId(countryModel.getCountryId()));
        } catch (SQLException exception) {
            System.out.println("Unable to filter state/province by country Id");
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param actionEvent When the 'cancel' button is clicked the user will be
     *                    brought back to the main customer screen. Where they
     *                    can view all customers in a table
     * */
    public void toCustomerMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
        Stage stage = (Stage) modifyCustomerCancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,1019.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * @param customerModel The 'obtainDate' method gets the user input
     *                      and sets the value fields
     * */
    public void obtainData(CustomerModel customerModel) throws SQLException{
        /** Text Fields */
        modifyCustomerIDText.setText(Integer.toString(customerModel.getCustomerId())); //Customer Id
        modifyCustomerNameText.setText(customerModel.getCustomer_Name()); //Customer Name
        modifyCustomerAddressText.setText(customerModel.getCustomer_Address()); //Customer Address
        modifyCustomerPhoneNoText.setText(customerModel.getCustomer_PhoneNumber()); //Customer Phone
        modifyCustomerPostalCodeText.setText(customerModel.getCustomer_PostalCode()); //Customer Postal Code

        /** Combo Boxes */
        StateProvinceModel stateProvinceModel = StateProvinceQuery.stateProvinceById(customerModel.getCustomer_StateProvinceId());
        modifyCustomerStateProvinceComboBox.setValue(stateProvinceModel); // Customer State/Province

        CountryModel countryModel = CountryQuery.countryById(customerModel.getCustomer_Country_Id());
        modifyCustomerCountryComboBox.setValue(countryModel); //Customer Country

        CountryModel countryModel1 = modifyCustomerCountryComboBox.getValue();
        modifyCustomerStateProvinceComboBox.setItems(StateProvinceQuery.obtainDivisionByCountryId(countryModel1.getCountryId())); //Customer State province filtered by County Combo box
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    public void saveAddCustomer(ActionEvent actionEvent) {
        try {
            JavaDatabaseConnection.openConnection();
            /**
             * Get Customer Id  of existing appointment
             * */
            int customerId = Integer.parseInt(modifyCustomerIDText.getText());

            /**
             * If customer name text field is empty,
             * user receives an alert to inform them
             * */
            if (modifyCustomerNameText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to save");
                alert.setContentText("Empty Customer Name");
                return;
            }
            String customer_Name = modifyCustomerNameText.getText();

            /** --------------------------------------------------------------------- */
            /**
             * If customer address text field is empty,
             * user receives an alert to inform them
             * */
            if (modifyCustomerAddressText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to save");
                alert.setContentText("Empty Customer Address");
                return;
            }
            String customer_Address = modifyCustomerAddressText.getText();

            /** --------------------------------------------------------------------- */

            /**
             * If customer postal code text field is empty,
             * user receives an alert to inform them
             * */
            if (modifyCustomerPostalCodeText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to save");
                alert.setContentText("Empty Customer Postal Code");
                return;
            }
            String customer_PostalCode = modifyCustomerPostalCodeText.getText();

            /** --------------------------------------------------------------------- */

            /**
             * If customer phone number text field is empty,
             * user receives an alert to inform them
             * */
            if (modifyCustomerPhoneNoText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to save");
                alert.setContentText("Empty Customer Phone Number");
                return;
            }
            String customer_PhoneNumber = modifyCustomerPhoneNoText.getText();

            /** --------------------------------------------------------------------- */

            int customer_StateProvinceId = modifyCustomerStateProvinceComboBox.getValue().getStateProvinceId(); //Customer Division
            int customer_Country_Id = modifyCustomerCountryComboBox.getValue().getCountryId(); //Customer Country

            /**
             * If all fields contain a value the 'modifyExistingCustomer' method
             * from the Customer Query will execute and update the selected customers information
             * */
            CustomerQuery.modifyExistingCustomer(customer_Name, customer_Address, customer_PostalCode, customer_PhoneNumber, customer_StateProvinceId, customerId, customer_Country_Id);
            Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            Scene scene = new Scene(root,1019.0,720.0);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException | IOException exception) {
            System.out.println("Unable to modify existing customer");
        }
    }

    /** ----------------------------------------------------------------------------------------------------------------- */

    /**
     * Populates the country combo box on start up.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyCustomerCountryComboBox.setItems(CountryQuery.obtainAllCountries()); //Set Items to Country Combo Box
    }
}
