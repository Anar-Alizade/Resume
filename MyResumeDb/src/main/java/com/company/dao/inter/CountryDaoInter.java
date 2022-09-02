package com.company.dao.inter;

import com.company.dao.entity.Country;

import java.util.List;

public interface CountryDaoInter {

    public List<Country> getAll();

    public Country getCountryById(int id);

    public boolean removeCountry(int id);

    public boolean insertCountry(Country country);

    public boolean updateCountry(Country country);

}
