package Models;

public class REPORT_MonthType_TotalAppointment {
    private String appMonth;
    private String appType;
    private int totalPerMonth;

    public REPORT_MonthType_TotalAppointment(
            String appMonth,
            String appType,
            int totalPerMonth
    ){
        this.appMonth = appMonth;
        this.appType = appType;
        this.totalPerMonth = totalPerMonth;
    }

    public String getAppMonth() {
        return appMonth;
    }

    public void setAppMonth(String appMonth) {
        this.appMonth = appMonth;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public int getTotalPerMonth() {
        return totalPerMonth;
    }

    public void setTotalPerMonth(int totalPerMonth) {
        this.totalPerMonth = totalPerMonth;
    }
}
