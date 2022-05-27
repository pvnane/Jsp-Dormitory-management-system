package com.southwind.dao.impl;

import com.southwind.dao.BuildingDao;
import com.southwind.entity.Building;
import com.southwind.entity.Student;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoImpl implements BuildingDao {
    @Override
    public List<Building> list() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select b.id,b.name,b.introduction,d.name,d.id from building b,dormitory_admin d where b.admin_id = d.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Building> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String introduction = resultSet.getString(3);
                String adminName = resultSet.getString(4);
                Integer adminId = resultSet.getInt(5);
                list.add(new Building(id, name, introduction, adminId, adminName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Building> search(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select b.id,b.name,b.introduction,d.name,d.id from building b,dormitory_admin d where b.admin_id = d.id and b."+key+" like '%"+value+"%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Building> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String introduction = resultSet.getString(3);
                String adminName = resultSet.getString(4);
                Integer adminId = resultSet.getInt(5);
                list.add(new Building(id, name, introduction, adminId, adminName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Integer save(Building building) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into building(name,introduction,admin_id) values(?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, building.getName());
            statement.setString(2, building.getIntroduction());
            statement.setInt(3, building.getAdminId());
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer update(Building building) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update building set name = ?,introduction = ?,admin_id = ? where id = ?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, building.getName());
            statement.setString(2, building.getIntroduction());
            statement.setInt(3, building.getAdminId());
            statement.setInt(4, building.getId());
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "delete from building where id = "+id;
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }
}
