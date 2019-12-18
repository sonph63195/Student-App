package com.duatson.studentapp.model;

import java.io.Serializable;

public class Request implements Serializable {
    private String id;
    private String serviceId;
    private String time;
    private String status;
    private String note;
    private String[] attachments;

    public Request() {
    }

    public Request(String id, String serviceId, String time, String status, String note, String[] attachments) {
        this.id = id;
        this.serviceId = serviceId;
        this.time = time;
        this.status = status;
        this.note = note;
        this.attachments = attachments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String[] getAttachments() {
        return attachments;
    }

    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }
}
