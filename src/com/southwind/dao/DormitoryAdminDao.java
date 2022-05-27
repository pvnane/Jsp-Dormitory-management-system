package com.southwind.dao;

import com.southwind.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminDao {
    public List<DormitoryAdmin> list();
    public List<DormitoryAdmin> search(String key,String value);
    public Integer save(DormitoryAdmin dormitoryAdmin);
    public Integer update(DormitoryAdmin dormitoryAdmin);
    public Integer delete(Integer id);
    public DormitoryAdmin findByUsername(String username);
}
