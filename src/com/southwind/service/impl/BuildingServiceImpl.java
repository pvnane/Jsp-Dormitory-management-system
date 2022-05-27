package com.southwind.service.impl;

import com.southwind.dao.BuildingDao;
import com.southwind.dao.DormitoryDao;
import com.southwind.dao.StudentDao;
import com.southwind.dao.impl.BuildingDaoImpl;
import com.southwind.dao.impl.DormitoryDaoImpl;
import com.southwind.dao.impl.StudentDaoImpl;
import com.southwind.entity.Building;
import com.southwind.entity.Dormitory;
import com.southwind.service.BuildingService;

import java.util.List;

public class BuildingServiceImpl implements BuildingService {

    private BuildingDao buildingDao = new BuildingDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Building> list() {
        return this.buildingDao.list();
    }

    @Override
    public List<Building> search(String key, String value) {
        if(value.equals("")) return this.buildingDao.list();
        return this.buildingDao.search(key, value);
    }

    @Override
    public void save(Building building) {
        Integer save = this.buildingDao.save(building);
        if(save != 1) throw new RuntimeException("添加楼宇信息失败");
    }

    @Override
    public void update(Building building) {
        Integer update = this.buildingDao.update(building);
        if(update != 1) throw new RuntimeException("更新楼宇信息失败");
    }

    @Override
    public void delete(Integer id) {
        //学生换宿舍
        List<Integer> dormitoryIdList = this.dormitoryDao.findDormitoryIdByBuildingId(id);
        for (Integer dormitoryId : dormitoryIdList) {
            List<Integer> studentIdList = this.studentDao.findStudentIdByDormitoryId(dormitoryId);
            for (Integer studentId : studentIdList) {
                Integer availableId = this.dormitoryDao.availableId();
                Integer updateDorimtory = this.studentDao.updateDorimtory(studentId, availableId);
                Integer subAvailable = this.dormitoryDao.subAvailable(availableId);
                if(updateDorimtory != 1 || subAvailable != 1) throw new RuntimeException("学生更换宿舍失败");
            }
        }
        //删除宿舍
        for (Integer dormitoryId : dormitoryIdList) {
            Integer delete = this.dormitoryDao.deleteById(dormitoryId);
            if(delete != 1) throw new RuntimeException("宿舍信息删除失败");
        }
        //删除楼宇
        Integer delete = this.buildingDao.delete(id);
        if(delete != 1) throw new RuntimeException("楼宇信息删除失败");
    }
}
