package Contollers;

import Models.CountryModel;
import Models.StateProvinceModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateCustomerController {
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
    }

    public void filterByCountry(ActionEvent actionEvent) {
    }
}
