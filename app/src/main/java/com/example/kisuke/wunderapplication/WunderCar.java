package com.example.kisuke.wunderapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "placemarks"
})
/**
 * Created on 23/09/2016.
 * Class created by Pojo
 * http://www.jsonschema2pojo.org/
 */
public class WunderCar implements Serializable {

    @JsonProperty("placemarks")
    private List<Placemark> placemarks = new ArrayList<Placemark>();


    /**
     * No args constructor for use in serialization
     *
     */
    public WunderCar() {
    }

    /**
     *
     * @param placemarks
     */
    public WunderCar(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

    /**
     *
     * @return
     *     The placemarks
     */
    @JsonProperty("placemarks")
    public List<Placemark> getPlacemarks() {
        return placemarks;
    }

    /**
     *
     * @param placemarks
     *     The placemarks
     */
    @JsonProperty("placemarks")
    public void setPlacemarks(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

}
