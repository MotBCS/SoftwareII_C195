package Contollers;

import Models.CustomerModel;
import Queries.CustomerQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainCustomerController implements Initializable {
    public TableView<CustomerModel>customerTable;
    public TableColumn<CustomerModel, Integer>customerIDColumn;
    public TableColumn<CustomerModel, String>customerTitleColumn;
    public TableColumn<CustomerModel, String>customerAddressColumn;
    public TableColumn<CustomerModel, String>customerPostalCodeColumn;
    public TableColumn<CustomerModel, String>customerPhoneNoColumn;
    public TableColumn<CustomerModel, Integer>customerDivisionColumn;
    public TableColumn<CustomerModel, Integer>customerCountryColumn;
    public Button deleteCustomerBtn;
    public Button modifyCustomerBtn;
    public Button addNewCustomerBtn;
    public Button backBtn;

    ObservableList<CustomerModel> allCustomersList = CustomerQuery.obtainAllCustomers();

    public void toMainMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeMenuScreen.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Scene scene = new Scene(root,452.0,400.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void addNewCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AddNewCustomer.fxml"));
        Stage stage = (Stage) addNewCustomerBtn.getScene().getWindow();
        Scene scene = new Scene(root,600.0,505.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void modifyExistingCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ModifyExistingCustomerScreen.fxml"));
        Stage stage = (Stage) modifyCustomerBtn.getScene().getWindow();
        Scene scene = new Scene(root,600.0,505.0);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerTitleColumn.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customer_Address"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customer_PostalCode"));
        customerPhoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("customer_PhoneNumber"));
        customerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("customer_StateProvinceName"));
        customerCountryColumn.setCellValueFactory(new PropertyValueFactory<>("customer_CountryName"));

        customerTable.setItems(CustomerQuery.obtainAllCustomers());

    }
}
