
package com.mydrafts.android.randomuser.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;

    /**
     * No args constructor for use in serialization
     */
    public Location() {
    }

    /**
     * @param street
     * @param state
     * @param city
     */
    public Location(String street, String city, String state) {
        super();
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return getStreet().concat(", ").concat(getCity()).concat(", ").concat(getState());
    }
}
