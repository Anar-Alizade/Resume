package com.company.main;

import com.company.dao.impl.*;
import com.company.dao.inter.*;

public class Context {

    public static UserDaoInter insanceUserDao() {
        return new UserDaoImpl();
    }

    public static CountryDaoInter insanceCountryDao() {
        return new CountryDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmpHistory() {
        return new EmploymentHistoryDaoImpl();
    }

    public static SkillDaoInter instanceSkilLDao() {
        return new SkillDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

}
