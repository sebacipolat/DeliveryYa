package com.cipolat.deliveryya.base.Model;

import java.io.Serializable;

/**
 * Created by sebastian on 24/09/17.
 */

public class Store implements Serializable {
    private String id,logo_url, title, backgr,status;
    private int score;

    public Store() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackgr() {
        return backgr;
    }

    public void setBackgr(String backgr) {
        this.backgr = backgr;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
