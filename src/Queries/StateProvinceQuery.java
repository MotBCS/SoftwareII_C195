package Queries;

import Helper.JavaDatabaseConnection;
import Models.StateProvinceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateProvinceQuery {

    public static ObservableList<StateProvinceModel>obtainStatProvinceById(){
        ObservableList<StateProvinceModel> stateProvinceList = FXCollections.observableArrayList();
        try {
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
        }
        return stateProvinceList;
    }
}
