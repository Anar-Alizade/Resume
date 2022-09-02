package com.company.dao.entity;

import java.util.Objects;

public class Country {

    private Integer id;
    private String birthPlace;
    private String nationality;


    public Country() {
    }

    public Country(Integer id) {
        this.id = id;
    }

    public Country(Integer id, String birthPlace) {
        this.id = id;
        this.birthPlace = birthPlace;
    }

    public Country(Integer id, String birthPlace, String nationality) {
        this.id = id;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return birthPlace + "(" + nationality + ")";
    }
}
