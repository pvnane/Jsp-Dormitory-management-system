package com.southwind.dao;

import com.southwind.entity.Building;

import java.util.List;

public interface BuildingDao {
    public List<Building> list();
    public List<Building> search(String key, String value);
    public Integer save(Building building);
    public Integer update(Building building);
    public Integer delete(Integer id);
}
