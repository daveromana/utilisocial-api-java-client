package com.dc.utilisocial.api;

public class GeoLocation {
    private Double latitude;
    private Double longitude;
    
    public GeoLocation() {}
    
    public GeoLocation(Double latitude, Double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoLocation [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

}
