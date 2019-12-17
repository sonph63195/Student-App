package com.duatson.studentapp.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Map;

public class Service implements Serializable {
    private String id;
    private String name;
    private String description;
    private double fee;
    private String icon;
    private String thumbnail;
    private String email;
    private String phone;

    public Service() {
        // empty constructor
    }

    public Service(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Service(String id, String name, String description, double fee, String icon, String thumbnail, String email, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.icon = icon;
        this.thumbnail = thumbnail;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s - %s", id, name);
    }
}
