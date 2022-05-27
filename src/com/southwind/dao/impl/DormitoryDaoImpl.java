package com.southwind.dao.impl;

import com.southwind.dao.DormitoryDao;
import com.southwind.entity.Dormitory;
import com.southwind.entity.DormitoryAdmin;
import com.southwind.entity.Student;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryDaoImpl implements DormitoryDao {
    @Override
    public List<Dormitory> list() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select d.id,b.name,d.name,d.type,d.available,d.telephone from dormitory d,building b where d.building_id = b.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String name = resultSet.getString(3);
                Integer type = resultSet.getInt(4);
                Integer available = resultSet.getInt(5);
                String telephone = resultSet.getString(6);
                list.add(new Dormitory(id, buildingName, name, type, available, telephone));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Dormitory> search(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select d.id,b.name,d.name,d.type,d.available,d.telephone from dormitory d,building b where d.building_id = b.id and d."+key+" like '%"+value+"%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String name = resultSet.getString(3);
                Integer type = resultSet.getInt(4);
                Integer available = resultSet.getInt(5);
                String telephone = resultSet.getString(6);
                list.add(new Dormitory(id, buildingName, name, type, available, telephone));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Dormitory> availableList() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id,name from dormitory where available > 0";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                list.add(new Dormitory(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Integer subAvailable(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory set available = available-1 where id = "+id;
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

    @Override
    public Integer addAvailable(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory set available = available+1 where id = "+id;
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

    @Override
    public List<Integer> findDormitoryIdByBuildingId(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id from dormitory where building_id = "+id;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public Integer availableId() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id from dormitory where available > 0 limit 0,1";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "delete from dormitory where id = "+id;
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

    @Override
    public Integer save(Dormitory dormitory) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into dormitory(building_id,name,type,available,telephone) values(?,?,?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dormitory.getBuildingId());
            statement.setString(2, dormitory.getName());
            statement.setInt(3, dormitory.getType());
            statement.setInt(4, dormitory.getAvailable());
            statement.setString(5, dormitory.getTelephone());
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer update(Dormitory dormitory) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory set name = ?,telephone = ? where id = ?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitory.getName());
            statement.setString(2, dormitory.getTelephone());
            statement.setInt(3, dormitory.getId());
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Dormitory> findByBuildingId(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id,name from dormitory where building_id = "+id;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                list.add(new Dormitory(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }
}
