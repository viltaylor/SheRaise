package com.example.sheraise.ui.theme;

public class JobsModel {
    String title;
    String company;
    String salary;
    String date;
    String applicants;
    String location;
    String type;
    int logoCompany;
    int backgroundColor;

    public JobsModel(String title, String company, String salary, String date, String applicants, String location, String type, int logoCompany, int backgroundColor) {
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.date = date;
        this.applicants = applicants;
        this.location = location;
        this.type = type;
        this.logoCompany = logoCompany;
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getLogoCompany() {
        return logoCompany;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getApplicants() {
        return applicants;
    }

    public String getDate() {
        return date;
    }

    public String getSalary() {
        return salary;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }
}
