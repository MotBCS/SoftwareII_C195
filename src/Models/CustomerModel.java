package Models;

public class CustomerModel {

    private int customerId;
    private String customer_Name;
    private String customer_Address;
    private String customer_PostalCode;
    private String customer_PhoneNumber;
    private int customer_StateProvinceId;
    private int customer_Country_Id;
    private String customer_StateProvinceName;
    private String customer_CountryName;

    public CustomerModel(
            int customerId,
            String customer_Name,
            String customer_Address,
            String customer_PostalCode,
            String customer_PhoneNumber,
            int customer_StateProvinceId,
            int customer_Country_Id,
            String customer_StateProvinceName,
            String customer_CountryName
    ){
        this.customerId = customerId;
        this.customer_Name = customer_Name;
        this.customer_Address = customer_Address;
        this.customer_PostalCode = customer_PostalCode;
        this.customer_PhoneNumber = customer_PhoneNumber;
        this.customer_StateProvinceId = customer_StateProvinceId;
        this.customer_Country_Id = customer_Country_Id;
        this.customer_StateProvinceName = customer_StateProvinceName;
        this.customer_CountryName = customer_CountryName;
    }

    public CustomerModel(
            int customerId,
            String customer_Name,
            String customer_Address,
            String customer_PostalCode,
            String customer_PhoneNumber,
            int customer_StateProvinceId,
            int customer_Country_Id
    ){
        this.customerId = customerId;
        this.customer_Name = customer_Name;
        this.customer_Address = customer_Address;
        this.customer_PostalCode = customer_PostalCode;
        this.customer_PhoneNumber = customer_PhoneNumber;
        this.customer_StateProvinceId = customer_StateProvinceId;
        this.customer_Country_Id = customer_Country_Id;
    }

    public CustomerModel(
            int customerId,
            String customer_Name
    ){
        this.customerId = customerId;
        this.customer_Name = customer_Name;
    }

    public CustomerModel(){}

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getCustomer_Address() {
        return customer_Address;
    }

    public void setCustomer_Address(String customer_Address) {
        this.customer_Address = customer_Address;
    }

    public String getCustomer_PostalCode() {
        return customer_PostalCode;
    }

    public void setCustomer_PostalCode(String customer_PostalCode) {
        this.customer_PostalCode = customer_PostalCode;
    }

    public String getCustomer_PhoneNumber() {
        return customer_PhoneNumber;
    }

    public void setCustomer_PhoneNumber(String customer_PhoneNumber) {
        this.customer_PhoneNumber = customer_PhoneNumber;
    }

    public int getCustomer_Country_Id() {
        return customer_Country_Id;
    }

    public void setCustomer_Country_Id(int customer_Country_Id) {
        this.customer_Country_Id = customer_Country_Id;
    }

    public int getCustomer_StateProvinceId() {
        return customer_StateProvinceId;
    }

    public void setCustomer_StateProvinceId(int customer_StateProvinceId) {
        this.customer_StateProvinceId = customer_StateProvinceId;
    }

    public String getCustomer_StateProvinceName() {
        return customer_StateProvinceName;
    }

    public void setCustomer_StateProvinceName(String customer_StateProvinceName) {
        this.customer_StateProvinceName = customer_StateProvinceName;
    }

    public String getCustomer_CountryName() {
        return customer_CountryName;
    }

    public void setCustomer_CountryName(String customer_CountryName) {
        this.customer_CountryName = customer_CountryName;
    }

    @Override
    public String toString(){
        return Integer.toString(customerId) + " " + customer_Name;
    }
}
