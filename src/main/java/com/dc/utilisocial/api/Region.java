package com.dc.utilisocial.api;

public class Region {
    private GeoLocation topLeft;
    private GeoLocation bottomRight;

    public Region() {
    }

    public Region(GeoLocation topLeft, GeoLocation bottomRight) {
        super();
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public GeoLocation getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(GeoLocation topLeft) {
        this.topLeft = topLeft;
    }

    public GeoLocation getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(GeoLocation bottomRight) {
        this.bottomRight = bottomRight;
    }

}
