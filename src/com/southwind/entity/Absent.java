package com.southwind.entity;

public class Absent {
    private Integer id;
    private Integer buildingId;
    private String buildingName;
    private Integer dormitoryId;
    private String dormitoryName;
    private Integer studentId;
    private String studentName;
    private Integer dormitoryAdminId;
    private String dormitoryAdminName;
    private String createDate;
    private String reason;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDormitoryAdminName() {
        return dormitoryAdminName;
    }

    public void setDormitoryAdminName(String dormitoryAdminName) {
        this.dormitoryAdminName = dormitoryAdminName;
    }

    public Absent(Integer id, String buildingName, String dormitoryName, String studentName, String dormitoryAdminName, String createDate, String reason) {
        this.id = id;
        this.buildingName = buildingName;
        this.dormitoryName = dormitoryName;
        this.studentName = studentName;
        this.dormitoryAdminName = dormitoryAdminName;
        this.createDate = createDate;
        this.reason = reason;
    }

    public Absent(Integer buildingId, Integer dormitoryId, Integer studentId, Integer dormitoryAdminId, String createDate, String reason) {
        this.buildingId = buildingId;
        this.dormitoryId = dormitoryId;
        this.studentId = studentId;
        this.dormitoryAdminId = dormitoryAdminId;
        this.createDate = createDate;
        this.reason = reason;
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

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDormitoryAdminId() {
        return dormitoryAdminId;
    }

    public void setDormitoryAdminId(Integer dormitoryAdminId) {
        this.dormitoryAdminId = dormitoryAdminId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
