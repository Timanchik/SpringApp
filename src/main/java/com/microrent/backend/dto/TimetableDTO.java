package com.microrent.backend.dto;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimetableDTO {
    private int id;

    private DayOfWeek day;

    private LocalTime classTime;

    private GroupDto group;

    public TimetableDTO(int id, DayOfWeek day, LocalTime classTime, GroupDto group) {
        this.id = id;
        this.day = day;
        this.classTime = classTime;
        this.group = group;
    }

    public TimetableDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getClassTime() {
        return classTime;
    }

    public void setClassTime(LocalTime classTime) {
        this.classTime = classTime;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }
}
