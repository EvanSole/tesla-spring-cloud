package com.tesla.cloud.core.config.database;


public enum DbShareField {

    MASTER("masterDataSource"),SLAVE("slaveDataSource");

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private DbShareField(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

}
