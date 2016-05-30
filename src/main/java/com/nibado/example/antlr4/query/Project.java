package com.nibado.example.antlr4.query;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private String teamlead;
    private int budget;
    private LocalDate startdate;
    private LocalDate enddate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(String teamlead) {
        this.teamlead = teamlead;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teamlead='" + teamlead + '\'' +
                ", budget=" + budget +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                '}';
    }
}
