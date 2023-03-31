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

    private CustomerModel obtainCustomer;

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

    public void modifyExistingCustomer(ActionEvent actionEvent) throws IOException, SQLException {
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No customer Selected");
            alert.setContentText("Select an existing customer to modify");
            alert.showAndWait();
        }
    }

    public void deleteCustomer(ActionEvent actionEvent) throws IOException {
        int associatedAppointment = 0;
        ObservableList<AppointmentModel>allCustomerAppointments = AppointmentQuery.obtainAllAppointments();
        CustomerModel customerModel = customerTable.getSelectionModel().getSelectedItem();
        if (customerModel == null){
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
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setHeaderText("Selected Customer has associated appointments");
            alert2.setContentText("Selected customer has " + associatedAppointment + ", associated appointments\n\n" +
                    "Select 'OK' to delete customer from table along with associated appointments.\n" +
                    "Else select cancel");
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
            } else if (choice.get() == ButtonType.CANCEL){
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
            else if (choice.get() == ButtonType.CANCEL){
                return;
            }
        }
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
