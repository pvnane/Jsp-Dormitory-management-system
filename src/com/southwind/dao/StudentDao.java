package com.southwind.dao;

import com.southwind.entity.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> list();
    public List<Student> search(String key,String value);
    public Integer save(Student student);
    public Integer update(Student student);
    public Integer delete(Integer id);
    public List<Integer> findStudentIdByDormitoryId(Integer id);
    public Integer updateDorimtory(Integer studentId,Integer dormitoryId);
    public List<Student> moveoutList();
    public List<Student> searchForMoveout(String key,String value);
    public Integer updateStateById(Integer id);
    public List<Student> findByDormitoryId(Integer id);
}
