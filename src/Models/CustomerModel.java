package Models;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the customer model */
public class CustomerModel {

    /** Class variables */

    private int customerId;
    private String customer_Name;
    private String customer_Address;
    private String customer_PostalCode;
    private String customer_PhoneNumber;
    private int customer_StateProvinceId;
    private int customer_Country_Id;
    private String customer_StateProvinceName;
    private String customer_CountryName;
/** --------------------------------------------------------------------------------------------------------------------------- */

    /** Customer Model Constructor */
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

    /** Customer Model Constructor */
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

    /** Customer Model Constructor */
    public CustomerModel(
            int customerId,
            String customer_Name
    ){
        this.customerId = customerId;
        this.customer_Name = customer_Name;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */
    public CustomerModel(){}

    /**
     * @return Customer Id
     * */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * @param customerId Consumer ID (Customer ID)
     * */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Name
     * */
    public String getCustomer_Name() {
        return customer_Name;
    }
    /**
     * @param customer_Name
     * */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Address
     * */
    public String getCustomer_Address() {
        return customer_Address;
    }
    /**
     * @param  customer_Address
     * */
    public void setCustomer_Address(String customer_Address) {
        this.customer_Address = customer_Address;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Postal Code
     * */
    public String getCustomer_PostalCode() {
        return customer_PostalCode;
    }
    /**
     * @param customer_PostalCode
     * */
    public void setCustomer_PostalCode(String customer_PostalCode) {
        this.customer_PostalCode = customer_PostalCode;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Phone Number
     * */
    public String getCustomer_PhoneNumber() {
        return customer_PhoneNumber;
    }
    /**
     * @param customer_PhoneNumber
     * */
    public void setCustomer_PhoneNumber(String customer_PhoneNumber) {
        this.customer_PhoneNumber = customer_PhoneNumber;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Country Id
     * */
    public int getCustomer_Country_Id() {
        return customer_Country_Id;
    }
    /**
     * @param customer_Country_Id
     * */
    public void setCustomer_Country_Id(int customer_Country_Id) {
        this.customer_Country_Id = customer_Country_Id;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Division Id
     * */
    public int getCustomer_StateProvinceId() {
        return customer_StateProvinceId;
    }
    /**
     * @param customer_StateProvinceId
     * */
    public void setCustomer_StateProvinceId(int customer_StateProvinceId) {
        this.customer_StateProvinceId = customer_StateProvinceId;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Division Name
     * */
    public String getCustomer_StateProvinceName() {
        return customer_StateProvinceName;
    }
    /**
     * @param customer_StateProvinceName
     * */
    public void setCustomer_StateProvinceName(String customer_StateProvinceName) {
        this.customer_StateProvinceName = customer_StateProvinceName;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer Country Name
     * */
    public String getCustomer_CountryName() {
        return customer_CountryName;
    }
    /**
     * @param customer_CountryName
     * */
    public void setCustomer_CountryName(String customer_CountryName) {
        this.customer_CountryName = customer_CountryName;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */
    /**
     * @return Consumer ID concatenate with Consumer Name
     * */
    @Override
    public String toString(){
        return Integer.toString(customerId) + " " + customer_Name;
    }
}
