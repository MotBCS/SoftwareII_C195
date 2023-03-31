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

public class CreateCustomerController implements Initializable {
    public TextField addCustomerPhoneNoText;
    public ComboBox<CountryModel>addCustomerCountryComboBox;
    public ComboBox<StateProvinceModel>addCustomerStateProvinceComboBox;
    public TextField addCustomerIDText;
    public TextField addCustomerNameText;
    public TextField addCustomerAddressText;
    public TextField addCustomerPostalCodeText;
    public Button addCustomerCancelBtn;
    public Button saveBtn;

    public void toCustomerMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/CustomerMenuScreen.fxml"));
        Stage stage = (Stage) addCustomerCancelBtn.getScene().getWindow();
        Scene scene = new Scene(root,1019.0,720.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void saveAddCustomer(ActionEvent actionEvent) {
        try {
            JavaDatabaseConnection.openConnection();
            if (addCustomerNameText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer name");
                alert.showAndWait();
                return;
            }
            else if (addCustomerPhoneNoText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer phone number");
                alert.showAndWait();
                return;
            }
            else if (addCustomerAddressText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer address");
                alert.showAndWait();
                return;
            }
            else if (addCustomerPostalCodeText.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Unable to create new customer");
                alert.setContentText("Empty customer name");
                alert.showAndWait();
                return;
            }
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
            else {
                String customer_Name = addCustomerNameText.getText();
                String customer_Address = addCustomerAddressText.getText();
                String customer_PostalCode = addCustomerPostalCodeText.getText();
                String customer_PhoneNumber = addCustomerPhoneNoText.getText();
                StateProvinceModel stateProvinceModel = addCustomerStateProvinceComboBox.getValue();
                int customer_StateProvinceId = stateProvinceModel.getStateProvinceId();
                CustomerQuery.createNewCustomer(
                        customer_Name,
                        customer_Address,
                        customer_PostalCode,
                        customer_PhoneNumber,
                        customer_StateProvinceId
                );
            }
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

    public void filterByCountry(ActionEvent actionEvent) {
        CountryModel countryModel = addCustomerCountryComboBox.getValue();
        try {
            addCustomerStateProvinceComboBox.setItems(StateProvinceQuery.obtainDivisionByCountryId(countryModel.getCountryId()));
        } catch (SQLException exception) {
            System.out.println("Unable to filter state/province by country Id");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerCountryComboBox.setItems(CountryQuery.obtainAllCountries());

    }
}
