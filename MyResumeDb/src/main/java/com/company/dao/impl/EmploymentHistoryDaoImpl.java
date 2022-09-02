package com.company.dao.impl;

import com.company.dao.entity.EmploymentHistory;
import com.company.dao.entity.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.EmploymentHistoryDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    public EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(id, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAll() {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from employment_history");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public EmploymentHistory getEmpHistoryById(int id) {
        EmploymentHistory emp = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                emp = getEmploymentHistory(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emp;
    }

    @Override
    public boolean removeEmpHistory(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from employment_history where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertEmpHistory(EmploymentHistory emp) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into employment_history(header, begin_date, end_date, job_description, user_id) values(?,?,?,?,?)");
            java.sql.Date beginDate = new java.sql.Date(emp.getBeginDate().getTime());
            java.sql.Date endDate = new java.sql.Date(emp.getEndDate().getTime());
            stmt.setString(1, emp.getHeader());
            stmt.setDate(2, beginDate);
            stmt.setDate(3, endDate);
            stmt.setString(4, emp.getJobDescription());
            stmt.setInt(5, emp.getUserId().getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmpHistory(EmploymentHistory emp) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update employment_history set header=?, begin_date=?, end_date=?, job_description=?, user_id=? where id = ?");
            java.sql.Date beginDate = new java.sql.Date(emp.getBeginDate().getTime());
            java.sql.Date endDate = new java.sql.Date(emp.getEndDate().getTime());
            stmt.setString(1, emp.getHeader());
            stmt.setDate(2, beginDate);
            stmt.setDate(3, endDate);
            stmt.setString(4, emp.getJobDescription());
            stmt.setInt(5, emp.getUserId().getId());
            stmt.setInt(6, emp.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
