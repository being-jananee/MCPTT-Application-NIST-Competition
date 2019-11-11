package com.example.application;


import java.io.Serializable;

public class LocationItem implements Serializable {
    private String user;
    public Double latitude;
    public Double longitude;

    public LocationItem(String user) {
        this.latitude = null;
        this.longitude = null;
    }

    public LocationItem(String user, Double latitude, Double longitude) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(user).append("\n");
        sb.append("Lat/Long: ").append(latitude).append(", ").append(longitude).append("\n");
        return sb.toString();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
