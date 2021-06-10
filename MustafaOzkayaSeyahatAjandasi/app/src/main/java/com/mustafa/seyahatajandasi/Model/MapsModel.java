package com.mustafa.seyahatajandasi.Model;

import java.io.Serializable;

public class MapsModel implements Serializable {

    private String address;
     private double latitude;
     private double longitude;

    public MapsModel(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }


    public Double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }





}
