package com.pluralsight.audition.jdbc;

import com.pluralsight.audition.jdbc.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesHelper {

    private static final String LOAD_COUNTRIES_SQL = "insert into country (code_name, name) values ";

    private static final String[][] COUNTRY_INIT_DATA = { { "AU", "Australia" }, { "CA", "Canada" },
            { "FR", "France" }, { "DE", "Germany" }, { "ES", "Spain" }, { "IT", "Italy" },
            { "JP", "Japan" }, { "RO", "Romania" }, { "RU", "Russia" },
            { "CH", "Switzerland" }, { "UK", "United Kingdom" }, { "US", "United States" } };

    static List<Country> expectedCountryList;
    static List<Country> expectedCountryListStartsWithA;

    static void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA) {
            String sql = LOAD_COUNTRIES_SQL + "('" + countryData[0] + "', '" + countryData[1] + "');";
            try (Connection connection = ConnectionManager.openConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)){
                 statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void initExpectedCountries() {
        expectedCountryList = new ArrayList<>();
        expectedCountryListStartsWithA = new ArrayList<>();
        for (int i = 0; i < CountriesHelper.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = CountriesHelper.COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
