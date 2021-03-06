package com.example.car.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "placemarks"
})
/**
 * Created on 23/09/2016.
 * Class created by Pojo
 * http://www.jsonschema2pojo.org/
 */
public class ObjectCar implements Serializable {

    @JsonProperty("placemarks")
    private List<Placemark> placemarks = new ArrayList<Placemark>();


    /**
     * No args constructor for use in serialization
     */
    public ObjectCar() {
    }

    /**
     * @param placemarks
     */
    public ObjectCar(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

    /**
     * @return The placemarks
     */
    @JsonProperty("placemarks")
    public List<Placemark> getPlacemarks() {
        return placemarks;
    }

    /**
     * @param placemarks The placemarks
     */
    @JsonProperty("placemarks")
    public void setPlacemarks(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

}
