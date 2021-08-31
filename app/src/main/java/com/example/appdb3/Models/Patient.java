package com.example.appdb3.Models;

public class Patient {
    public  int ID;
    public  String Name;
    public String Form;
    public  String Place;
    public int Cost;

    public Patient(int ID, String name, String form, String place, int cost) {
        this.ID = ID;
        Name = name;
        Form = form;
        Place = place;
        Cost = cost;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }
}
