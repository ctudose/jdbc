package com.pluralsight.audition.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pluralsight.audition.jdbc.model.Country;
import com.pluralsight.audition.jdbc.ConnectionManager;

public class CountryDao {
	private static final String GET_ALL_COUNTRIES = "select * from country";
	private static final String GET_COUNTRIES_BY_NAME = "select * from country where name like ?";
	private static final String GET_COUNTRY_BY_CODE_NAME = "select * from country where code_name = ?";
	private static final String UPDATE_COUNTRY_NAME = "update country set name = ? where code_name = ?";
	private static final String DELETE_COUNTRY_BY_NAME = "delete from country where name like ?";
	private static final String WRONG_SQL = "selec * from country";

	public List<Country> getCountryList() {
		List<Country> countryList = new ArrayList<>();

		try (Connection connection = ConnectionManager.openConnection();
		     PreparedStatement statement = connection.prepareStatement(GET_ALL_COUNTRIES)){
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return countryList;
	}

	public List<Country> getCountry(String codeName) {
		List<Country> countryList = new ArrayList<>();

		try (Connection connection = ConnectionManager.openConnection();
		     PreparedStatement statement = connection.prepareStatement(GET_COUNTRY_BY_CODE_NAME)){
			statement.setString(1, codeName);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return countryList;
	}

	public List<Country> getCountryListStartWith(String startSequence) {
		List<Country> countryList = new ArrayList<>();

		try (Connection connection = ConnectionManager.openConnection();
			 PreparedStatement statement = connection.prepareStatement(GET_COUNTRIES_BY_NAME)){
			statement.setString(1, startSequence + "%");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return countryList;
	}

	public int updateCountry(String name, String codeName) {
		try (Connection connection = ConnectionManager.openConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE_COUNTRY_NAME)){
			statement.setString(1, name);
			statement.setString(2, codeName);
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int deleteCountryListStartWith(String startSequence) {
		try(Connection connection = ConnectionManager.openConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_COUNTRY_BY_NAME)) {
			statement.setString(1, startSequence + "%");
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void executeWrongSQL() {
		try(Connection connection = ConnectionManager.openConnection();
			PreparedStatement statement = connection.prepareStatement(WRONG_SQL)) {
			statement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
