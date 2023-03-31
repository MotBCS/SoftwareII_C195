package Contollers;

import Models.CountryModel;
import Models.CustomerModel;
import Models.StateProvinceModel;
import Queries.CountryQuery;
import Queries.StateProvinceQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    public TextField modifyCustomerPhoneNoText;
    public ComboBox<CountryModel> modifyCustomerCountryComboBox;
    public ComboBox<StateProvinceModel> modifyCustomerStateProvinceComboBox;
    public TextField modifyCustomerIDText;
    public TextField modifyCustomerNameText;
    public TextField modifyCustomerAddressText;
    public TextField modifyCustomerPostalCodeText;
    public Button modifyCustomerCancelBtn;
    public Button saveBtn;

    public void filterByCountry(ActionEvent actionEvent) {
        CountryModel countryModel = modifyCustomerCountryComboBox.getValue();
        try {
            modifyCustomerStateProvinceComboBox.setItems(StateProvinceQuery.obtainDivisionByCountryId(countryModel.getCountryId()));
        } catch (SQLException exception) {
            System.out.println("Unable to filter state/province by country Id");
        }
    }

    public void toCustomerMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
        Stage stage = (Stage) modifyCustomerCancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,1019.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void obtainData(CustomerModel customerModel) throws SQLException{
        modifyCustomerIDText.setText(Integer.toString(customerModel.getCustomerId()));
        modifyCustomerNameText.setText(customerModel.getCustomer_Name());
        modifyCustomerAddressText.setText(customerModel.getCustomer_Address());
        modifyCustomerPhoneNoText.setText(customerModel.getCustomer_PhoneNumber());
        modifyCustomerPostalCodeText.setText(customerModel.getCustomer_PostalCode());

        StateProvinceModel stateProvinceModel = StateProvinceQuery.stateProvinceById(customerModel.getCustomer_StateProvinceId());
        modifyCustomerStateProvinceComboBox.setValue(stateProvinceModel);

        CountryModel countryModel = CountryQuery.countryById(customerModel.getCustomer_Country_Id());
        modifyCustomerCountryComboBox.setValue(countryModel);
        CountryModel countryModel1 = modifyCustomerCountryComboBox.getValue();
        modifyCustomerStateProvinceComboBox.setItems(StateProvinceQuery.obtainDivisionByCountryId(countryModel1.getCountryId()));
    }

    public void saveAddCustomer(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyCustomerCountryComboBox.setItems(CountryQuery.obtainAllCountries());
    }
}
