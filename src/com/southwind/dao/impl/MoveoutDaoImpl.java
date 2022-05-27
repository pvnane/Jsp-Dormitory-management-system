package com.southwind.dao.impl;

import com.southwind.dao.MoveoutDao;
import com.southwind.entity.Moveout;
import com.southwind.entity.Student;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveoutDaoImpl implements MoveoutDao {
    @Override
    public Integer save(Moveout moveout) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into moveout(student_id,dormitory_id,reason,create_date) values(?,?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, moveout.getStudentId());
            statement.setInt(2, moveout.getDormitoryId());
            statement.setString(3, moveout.getReason());
            statement.setString(4, moveout.getCreateDate());
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Moveout> list() {
        Connection connection = JDBCUtil.getConnection();
        String sql = " select m.id,s.name,d.name,m.reason,m.create_date from moveout m,student s,dormitory d where m.student_id = s.id and m.dormitory_id = d.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Moveout> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String studentName = resultSet.getString(2);
                String dormitoryName = resultSet.getString(3);
                String reason = resultSet.getString(4);
                String createDate = resultSet.getString(5);
                list.add(new Moveout(id, studentName, dormitoryName, reason, createDate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public List<Moveout> search(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = " select m.id,s.name,d.name,m.reason,m.create_date from moveout m,student s,dormitory d where m.student_id = s.id and m.dormitory_id = d.id";
        String keyStatement = "";
        if(key.equals("studentName")){
            keyStatement = " and s.name";
        }
        if(key.equals("dormitoryName")){
            keyStatement = " and d.name";
        }
        sql = sql + keyStatement + " like '%"+value+"%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Moveout> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String studentName = resultSet.getString(2);
                String dormitoryName = resultSet.getString(3);
                String reason = resultSet.getString(4);
                String createDate = resultSet.getString(5);
                list.add(new Moveout(id, studentName, dormitoryName, reason, createDate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }
        return list;
    }
}
