package org.mcopenplatform.muoapi.mcopsdk;

import java.io.Serializable;

public class LocationItem implements Serializable {
    private String userId;
    public Double latitude;
    public Double longitude;

    public LocationItem(String userId) {
        this.userId = userId;
        this.longitude = null;
        this.latitude = null;
    }

    public LocationItem(String user, Double latitude, Double longitude) {
        this.userId = user;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(userId).append("\n");
        sb.append("Lat/Long: ").append(latitude).append(", ").append(longitude).append("\n");
        return sb.toString();
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }

}