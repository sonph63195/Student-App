package com.duatson.studentapp.model;

import android.content.res.Resources;

import java.io.Serializable;

public class Contact implements Serializable {
    private String title;
    private String content;
    private int icon;

    public Contact(String title, String content, int icon) {
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
