package com.example.listycitylab3;
import java.io.Serializable;

public class City implements Serializable {
    private String city;
    private String province;

    public City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String getCity() {
        return city; }
    public String getProvince() {
        return province; }

    public void setCity(String city) {
        this.city = city; }
    public void setProvince(String province) {
        this.province = province; }
}
