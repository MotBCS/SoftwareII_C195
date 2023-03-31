package Queries;

import Helper.JavaDatabaseConnection;
import Models.CountryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryQuery {
    public static ObservableList<CountryModel>obtainAllCountries(){
        ObservableList<CountryModel> allCountriesList = FXCollections.observableArrayList();
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Country_ID, Country FROM countries";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int countryId = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                CountryModel countryModel = new CountryModel(
                        countryId,
                        countryName
                );
                allCountriesList.add(countryModel);
            }
        } catch (SQLException exception) {
            System.out.println("Unable to obtain all countries (SQL Error)");
        }finally {
            JavaDatabaseConnection.closeConnection();
        }
        return allCountriesList;
    }

    public static CountryModel countryById(int countryId){
        try {
            JavaDatabaseConnection.openConnection();
            String SQL = "SELECT Country_ID, Country FROM countries WHERE Country_ID = ?";
            PreparedStatement preparedStatement = JavaDatabaseConnection.connection.prepareStatement(SQL);
            preparedStatement.setInt(1, countryId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                int country_Id = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                CountryModel countryModel = new CountryModel(country_Id, countryName);
                return countryModel;
            }

        } catch (SQLException exception) {
            System.out.println("Unable to get country by Id (SQL Error)");
        }
        return null;
    }
}
