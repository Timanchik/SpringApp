package com.microrent.backend.models.searchingAndFiltering;


import java.io.Serializable;

public class SortRequest implements Serializable {
    private String key;
    private SortDirection direction;

    public SortRequest(String key, SortDirection direction) {
        this.key = key;
        this.direction = direction;
    }

    public SortRequest() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }
}
