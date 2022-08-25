package com.example.dtos;

public class Item {
    private String key;
    private String value;
    private int priority;
    private int expiryTime;

    public Item(String key, String value, int priority, int expiryTime) {
        this.key = key;
        this.value = value;
        this.priority = priority;
        this.expiryTime = expiryTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(int expiryTime) {
        this.expiryTime = expiryTime;
    }
}
