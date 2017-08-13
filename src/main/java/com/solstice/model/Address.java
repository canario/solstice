package com.solstice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

    private static final long serialVersionUID = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull( message = "error.street.notnull")
    @Column(name = "STREET")
    private String street;

    @NotNull( message = "error.city.notnull")
    @Column(name = "CITY")
    private String city;

    @NotNull( message = "error.state.notnull")
    @Column(name = "STATE")
    private String state;

    @NotNull( message = "error.country.notnull")
    @Column(name = "COUNTRY")
    private String country;

    @NotNull( message = "error.zip.notnull")
    @Column(name = "ZIP")
    private Integer zip;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Contact contact;

    public Address(){}

    public Address(String street, String city, String state, String country, Integer zip, String latitude, String longitude) {
        super();
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
