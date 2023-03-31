package Queries;

import Helper.JavaDatabaseConnection;
import Models.StateProvinceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateProvinceQuery {

    /** - GET STATE/PROVINCE  -------------------------------------------------------------------------------------*/


    public static ObservableList<StateProvinceModel>obtainStatProvince(){
        ObservableList<StateProvinceModel> stateProvinceList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int stateProvinceId = resultSet.getInt("Division_ID");
                String stateProvinceName = resultSet.getString("Division");
                int countryId = resultSet.getInt("Country_ID");
                StateProvinceModel stateProvinceModel = new StateProvinceModel(
                        stateProvinceId,
                        stateProvinceName,
                        countryId
                );
                stateProvinceList.add(stateProvinceModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to get state/provinces by Id");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return stateProvinceList;
    }

    /** - GET STATE/PROVINCE BY ID  -------------------------------------------------------------------------------------*/

    public static StateProvinceModel stateProvinceById(int stateProvinceId){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, stateProvinceId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                int stateProvince_Id = resultSet.getInt("Division_ID");
                String stateProvinceName = resultSet.getString("Division");
                StateProvinceModel stateProvinceModel = new StateProvinceModel(
                        stateProvince_Id,
                        stateProvinceName
                );
                return stateProvinceModel;
            }
        } catch (SQLException exception) {
            System.out.println("Unable to get state/province name by Id (SQL Error)");
        }
        return null;
    }

    /** - GET STATE/PROVINCE BY COUNTRY ID  -------------------------------------------------------------------------------------*/

    public static ObservableList<StateProvinceModel>obtainDivisionByCountryId(int countryId) throws SQLException{
        ObservableList<StateProvinceModel> stateProvinceByCountry = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int stateProvinceId = resultSet.getInt("Division_ID");
                String stateProvinceName = resultSet.getString("Division");
                countryId = resultSet.getInt("Country_ID");

                StateProvinceModel stateProvinceModel = new StateProvinceModel(
                        stateProvinceId,
                        stateProvinceName,
                        countryId
                );
                stateProvinceByCountry.add(stateProvinceModel);
            }
        } finally {
            JavaDatabaseConnection.closeConnection();
        }
        return stateProvinceByCountry;
    }
}
