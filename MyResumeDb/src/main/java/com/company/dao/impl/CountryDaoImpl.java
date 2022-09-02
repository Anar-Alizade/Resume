package com.company.dao.impl;

import com.company.dao.entity.Country;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {

    public Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

        Country c = new Country(id, name, nationality);
        return c;
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from country");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country country = getCountry(rs);
                result.add(country);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Country getCountryById(int id) {
        Country country = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select " +
                    "c.id, " +
                    "u.name as country_name," +
                    "us.nationality " +
                    "from " +
                    "user c " +
                    "left join country u on u.id = c.birthplace_id " +
                    "left join country us on us.id = c.nationality_id " +
                    "where c.id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                country = getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean removeCountry(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from country where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertCountry(Country country) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into country(name, nationality) values(?,?)");
            stmt.setString(1, country.getBirthPlace());
            stmt.setString(2, country.getNationality());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCountry(Country country) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update country set name=?, nationality=? where id = ?");
            stmt.setString(1, country.getBirthPlace());
            stmt.setString(2, country.getNationality());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
