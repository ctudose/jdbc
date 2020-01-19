package com.pluralsight.audition.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pluralsight.audition.jdbc.dao.CountryDao;
import com.pluralsight.audition.jdbc.model.Country;

import static com.pluralsight.audition.jdbc.CountriesHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class CountriesDatabaseTest {
    private CountryDao countryDao = new CountryDao();

    @BeforeEach
    public void setUp() {
        TablesManager.createTable();
        initExpectedCountries();
        loadCountries();
    }

    @Test
    public void testInsertCountries() {
        List<Country> countryList = countryDao.getCountryList();
        assertNotNull(countryList, () -> "The list of countries should not be null");
        assertEquals(expectedCountryList.size(), countryList.size(),
                () -> "The size of the countries list is wrong");
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i),
                    () -> "Wrong country found in the list");
        }
    }

    @Test
    public void testSelectCountriesStartingWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertNotNull(countryList,
                () -> "The list of countries with names starting with A should not be null");
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size(),
                () -> "The size of the countries list with names starting with A is wrong");
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i),
                    () -> "Wrong country found in the list of countries starting with A");
        }
    }

    @Test
    public void testWrongSQL() {
        Throwable throwable =
                assertThrows(RuntimeException.class, () -> countryDao.executeWrongSQL(),
                        () -> "The execution of the SQL query should throw RuntimeException");
        assertTrue(throwable.getMessage().contains("Syntax error in SQL statement \"SELEC[*] * FROM COUNTRY \""),
                () -> "The SQL query should fail as having a syntax error");
        assertTrue(throwable.getCause() instanceof SQLException,
                () -> "The cause of the exception should be an instance of SQLException");
    }

    @Test
    public void testUpdateCountries() {
        assertEquals(1, countryDao.updateCountry("Russian Federation", "RU"),
                () -> "There should be exactly one country with the code name \"RU\" having an updated name");
        List<Country> countryList = countryDao.getCountry("RU");
        assertNotNull(countryList,
                () -> "The list of the countries with updated names should not be null");
        assertEquals("Russian Federation", countryList.get(0).getName(),
                () -> "The name of the updated country should be \"Russian Federation\"");
    }

    @Test
    public void testDeleteCountries() {
        assertEquals(1, countryDao.deleteCountryListStartWith("A"),
                () -> "There should be exactly one deleted country with the name starting with \"A\"");
        List<Country> countryList = countryDao.getCountry("AU");
        assertEquals(0, countryList.size(),
                () -> "After deletion, there should be no country with the code name \"AU\"");
    }

    @AfterEach
    public void dropDown() {
        TablesManager.dropTable();
    }

}