package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

public class Tax {
    @SerializedName("name")
    public String name;

    @SerializedName("value")
    public String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
