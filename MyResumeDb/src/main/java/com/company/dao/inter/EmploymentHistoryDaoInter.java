package com.company.dao.inter;

import com.company.dao.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getAll();

    public EmploymentHistory getEmpHistoryById(int id);

    public boolean removeEmpHistory(int id);

    public boolean insertEmpHistory(EmploymentHistory emp);

    public boolean updateEmpHistory(EmploymentHistory emp);

}
