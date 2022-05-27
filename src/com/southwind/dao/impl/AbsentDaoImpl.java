package com.southwind.dao.impl;

import com.southwind.dao.AbsentDao;
import com.southwind.entity.Absent;
import com.southwind.entity.Building;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbsentDaoImpl implements AbsentDao {
    @Override
    public Integer save(Absent absent) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into absent(building_id,dormitory_id,student_id,dormitory_admin_id,create_date,reason) values(?,?,?,?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, absent.getBuildingId());
            statement.setInt(2, absent.getDormitoryId());
            statement.setInt(3, absent.getStudentId());
            statement.setInt(4, absent.getDormitoryAdminId());
            statement.setString(5, absent.getCreateDate());
            statement.setString(6, absent.getReason());
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Absent> list() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select a.id,b.name,d.name,s.name,a.reason,da.name,a.create_date from dormitory d,student s,building b,absent a,dormitory_admin da where d.id = a.dormitory_id and s.id = a.student_id and b.id = a.building_id and da.id = a.dormitory_admin_id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Absent> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String dormitoryName = resultSet.getString(3);
                String studentName = resultSet.getString(4);
                String reason = resultSet.getString(5);
                String dormitoryAdminName = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                list.add(new Absent(id, buildingName, dormitoryName, studentName, dormitoryAdminName, createDate, reason));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Absent> search(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select a.id,b.name,d.name,s.name,a.reason,da.name,a.create_date from dormitory d,student s,building b,absent a,dormitory_admin da where d.id = a.dormitory_id and s.id = a.student_id and b.id = a.building_id and da.id = a.dormitory_admin_id";
        String keyStatement = "";
        if(key.equals("buildingName")){
            keyStatement = " and b.name";
        }
        if(key.equals("dormitoryName")){
            keyStatement = " and d.name";
        }
        sql = sql + keyStatement + " like '%"+value+"%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Absent> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String dormitoryName = resultSet.getString(3);
                String studentName = resultSet.getString(4);
                String reason = resultSet.getString(5);
                String dormitoryAdminName = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                list.add(new Absent(id, buildingName, dormitoryName, studentName, dormitoryAdminName, createDate, reason));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }
}
