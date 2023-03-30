package Models;

public class REPORT_StateProvince_TotalCustomer {
    private String stateProvince;
    private int customerTotal;

    public REPORT_StateProvince_TotalCustomer(
            String stateProvince,
            int customerTotal
    ){
        this.stateProvince = stateProvince;
        this.customerTotal = customerTotal;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public int getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(int customerTotal) {
        this.customerTotal = customerTotal;
    }
}
