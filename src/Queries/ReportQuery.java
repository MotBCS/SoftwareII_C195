package Queries;

import Helper.JavaDatabaseConnection;
import Models.AppointmentModel;
import Models.REPORT_MonthType_TotalAppointment;
import Models.REPORT_StateProvince_TotalCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportQuery {

    /** - REPORT (TOTAL APPOINTMENTS BY MONTH AND TYPE) -------------------------------------------------------------------------------------*/

    public static ObservableList<REPORT_MonthType_TotalAppointment> appByMonthType(){
        ObservableList<REPORT_MonthType_TotalAppointment> appMonthTypeList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT DISTINCT monthname(Start) Month, type, count(*) AS Total FROM appointments GROUP BY Type;";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String appMonth = resultSet.getString("Month");
                String appType = resultSet.getString("type");
                int totalPerMonth = resultSet.getInt("Total");

                REPORT_MonthType_TotalAppointment report_monthType_totalAppointment = new REPORT_MonthType_TotalAppointment(
                        appMonth,
                        appType,
                        totalPerMonth
                );
                appMonthTypeList.add(report_monthType_totalAppointment);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain month/type report data from database (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return appMonthTypeList;
    }

    /** - REPORT (TOTAL CUSTOMERS BY STATE/PROVINCE) -------------------------------------------------------------------------------------*/

    public static ObservableList<REPORT_StateProvince_TotalCustomer> customerTotalByStateProvince(){
        ObservableList<REPORT_StateProvince_TotalCustomer> customerTotalByStateProvinceList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Division, count(DISTINCT Customer_ID) AS Total FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID GROUP BY Division;";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String stateProvince = resultSet.getString("Division");
                int customerTotal = resultSet.getInt("Total");

                REPORT_StateProvince_TotalCustomer report_stateProvince_totalCustomer = new REPORT_StateProvince_TotalCustomer(
                        stateProvince,
                        customerTotal
                );
                customerTotalByStateProvinceList.add(report_stateProvince_totalCustomer);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain State/Province report data from database (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return customerTotalByStateProvinceList;
    }


}
