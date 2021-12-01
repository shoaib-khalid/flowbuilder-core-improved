package com.kalsym.flowbuilder.formatted.models.vertex;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum DataFomat {
    @JsonProperty("JSON")
    JSON,
    @JsonProperty("XML")
    XML,
    @JsonProperty("")
    NULL;

    @JsonCreator
    public static DataFomat forValue(String value){
        for(DataFomat c: values()) {
            if(c.name().equals(value)) { //change accordingly
                return c;
            }
        }
        return null;

    }



}
