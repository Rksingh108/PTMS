package com.ptms.app.dao;

import com.ptms.app.model.Employee;
import com.ptms.app.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao implements IEmployeeDao{
    @Override
    public void addEmployee(Employee employee) {
        final String sql = "insert into employees (id,user_id,designation,department) values(?,?,?,?)";
        try(Connection conn = DataBaseConnection.getConnection();) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, employee.getId());
            statement.setInt(2, employee.getUserId());
            statement.setString(3, employee.getDesignation());
            statement.setString(4, employee.getDepartment());
            statement.executeUpdate();


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(Employee employee) {

    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeByUserId(int userId) {
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        return List.of();
    }

    static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = new Employee(1,101,"Developer","IT");
        dao.addEmployee(employee);
        System.out.println("Employee added successfully");

    }
}
