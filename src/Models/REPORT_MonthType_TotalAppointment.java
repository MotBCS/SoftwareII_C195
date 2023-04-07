package Models;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the MonthTYpeTotal Appointment Model for the Report Controller */
public class REPORT_MonthType_TotalAppointment {

    /** Class variables */
    private String appMonth;
    private String appType;
    private int totalPerMonth;

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** REPORT (MonthType) Model Constructor*/
    public REPORT_MonthType_TotalAppointment(
            String appMonth,
            String appType,
            int totalPerMonth
    ){
        this.appMonth = appMonth;
        this.appType = appType;
        this.totalPerMonth = totalPerMonth;
    }

    /** -------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */

    /**
     * @return Appointment Month
     * */
    public String getAppMonth() {
        return appMonth;
    }
    /**
     * @param appMonth  Appointment Month
     * */
    public void setAppMonth(String appMonth) {
        this.appMonth = appMonth;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */
    /**
     * @return Appointment Type
     * */
    public String getAppType() {
        return appType;
    }
    /**
     * @param appType Appointment Type
     * */
    public void setAppType(String appType) {
        this.appType = appType;
    }
    /** -------------------------------------------------------------------------------------------------------------------------- */
    /**
     * @return appointment Total
     * */
    public int getTotalPerMonth() {
        return totalPerMonth;
    }
    /**
     * @param totalPerMonth Appointment Total By Month
     * */
    public void setTotalPerMonth(int totalPerMonth) {
        this.totalPerMonth = totalPerMonth;
    }
}
