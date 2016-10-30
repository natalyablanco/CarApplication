package com.example.car.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "address",
        "coordinates",
        "engineType",
        "exterior",
        "fuel",
        "interior",
        "name",
        "vin"
})
/**
 * Created on 23/09/2016.
 * Class created using POJO
 * http://www.jsonschema2pojo.org/
 */
public class Placemark implements Serializable {

    @JsonProperty("address")
    private String address;
    @JsonProperty("coordinates")
    private List<Double> coordinates = new ArrayList<Double>();
    @JsonProperty("engineType")
    private String engineType;
    @JsonProperty("exterior")
    private String exterior;
    @JsonProperty("fuel")
    private Integer fuel;
    @JsonProperty("interior")
    private String interior;
    @JsonProperty("name")
    private String name;
    @JsonProperty("vin")
    private String vin;


    /**
     * No args constructor for use in serialization
     */
    public Placemark() {
    }

    /**
     * @param interior
     * @param engineType
     * @param exterior
     * @param address
     * @param vin
     * @param name
     * @param fuel
     * @param coordinates
     */
    public Placemark(String address, List<Double> coordinates, String engineType, String exterior, Integer fuel, String interior, String name, String vin) {
        this.address = address;
        this.coordinates = coordinates;
        this.engineType = engineType;
        this.exterior = exterior;
        this.fuel = fuel;
        this.interior = interior;
        this.name = name;
        this.vin = vin;
    }

    /**
     * @return The address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The coordinates
     */
    @JsonProperty("coordinates")
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates The coordinates
     */
    @JsonProperty("coordinates")
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }


    /**
     * @return The engineType
     */
    @JsonProperty("engineType")
    public String getEngineType() {
        return engineType;
    }

    /**
     * @param engineType The engineType
     */
    @JsonProperty("engineType")
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    /**
     * @return The exterior
     */
    @JsonProperty("exterior")
    public String getExterior() {
        return exterior;
    }

    /**
     * @param exterior The exterior
     */
    @JsonProperty("exterior")
    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    /**
     * @return The fuel
     */
    @JsonProperty("fuel")
    public Integer getFuel() {
        return fuel;
    }

    /**
     * @param fuel The fuel
     */
    @JsonProperty("fuel")
    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    /**
     * @return The interior
     */
    @JsonProperty("interior")
    public String getInterior() {
        return interior;
    }

    /**
     * @param interior The interior
     */
    @JsonProperty("interior")
    public void setInterior(String interior) {
        this.interior = interior;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The vin
     */
    @JsonProperty("vin")
    public String getVin() {
        return vin;
    }

    /**
     * @param vin The vin
     */
    @JsonProperty("vin")
    public void setVin(String vin) {
        this.vin = vin;
    }

}
