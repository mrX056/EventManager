/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Yassine
 */
public class Event {
    int id;
    String name, date, location;

    public Event(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }
    
    public Event(int id, String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public Event() {}

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public String toString(){
        return "Event{" + "id=" +id+ ", name=" +name+ ", date=" +date+ ", location=" +location+ "}";
    }
}
