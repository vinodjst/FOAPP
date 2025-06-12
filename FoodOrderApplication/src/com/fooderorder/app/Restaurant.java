package com.fooderorder.app;

public class Restaurant {

    private int restId;

    private String restName;

    private String restAddress;

    private String restType;

    //ratings....


    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public String getRestType() {
        return restType;
    }

    public void setRestType(String restType) {
        this.restType = restType;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "restId=" + restId +
                ", restName='" + restName + '\'' +
                ", restAddress='" + restAddress + '\'' +
                ", restType='" + restType + '\'' +
                '}';
    }
}
