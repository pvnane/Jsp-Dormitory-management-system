package com.southwind.entity;

public class Dormitory {
    private Integer id;
    private Integer buildingId;
    private String buildingName;
    private String name;
    private Integer type;
    private Integer available;
    private String telephone;

    public Dormitory(Integer id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }

    public Dormitory(Integer buildingId, String name, Integer type, Integer available, String telephone) {
        this.buildingId = buildingId;
        this.name = name;
        this.type = type;
        this.available = available;
        this.telephone = telephone;
    }

    public Dormitory(Integer id, String buildingName, String name, Integer type, Integer available, String telephone) {
        this.id = id;
        this.buildingName = buildingName;
        this.name = name;
        this.type = type;
        this.available = available;
        this.telephone = telephone;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Dormitory(Integer id) {
        this.id = id;
    }

    public Dormitory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
