package Models;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the MonthTYpeTotal Appointment Model for the Report Controller */
public class REPORT_StateProvince_TotalCustomer {

    /** Class variables */
    private String stateProvince;
    private int customerTotal;

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** REPORT (MonthType) Model Constructor*/
    public REPORT_StateProvince_TotalCustomer(
            String stateProvince,
            int customerTotal
    ){
        this.stateProvince = stateProvince;
        this.customerTotal = customerTotal;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */

    /**
     * @return State/Province
     * */
    public String getStateProvince() {
        return stateProvince;
    }
    /**
     * @param stateProvince State/Province
     * */
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Customer total per state/province
     * */
    public int getCustomerTotal() {
        return customerTotal;
    }
    /**
     * @param customerTotal Customer total per state/province
     * */
    public void setCustomerTotal(int customerTotal) {
        this.customerTotal = customerTotal;
    }
}
