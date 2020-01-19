package com.pluralsight.audition.jdbc.model;

import java.util.Objects;

public class Country {
    private String codeName;
    private String name;

    public Country(String codeName, String name) {
        this.codeName = codeName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCodeName() {
        return codeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(codeName, country.codeName) &&
               Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeName, name);
    }
}
