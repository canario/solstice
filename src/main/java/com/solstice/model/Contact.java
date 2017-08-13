package com.solstice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Contact")
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "FAVORITE")
    private boolean favorite;

    @Column(name = "SMALL_IMAGE_URL")
    private String smallImageURL;

    @Column(name = "LARGE_IMAGE_URL")
    private String largeImageURL;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "BIRTHDATE")
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private Date birthdate;

    @JsonInclude(Include.NON_NULL)
    @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Phone phone;

    @JsonInclude(Include.NON_NULL)
    @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Address address;

    public Contact(){}

    public Contact(String name, String company, boolean favorite, String smallImageURL, String largeImageURL,
                   String email, String website, Date birthdate, Phone phone, Address address) {
        super();
        this.name = name;
        this.company = company;
        this.favorite = favorite;
        this.smallImageURL = smallImageURL;
        this.largeImageURL = largeImageURL;
        this.email = email;
        this.website = website;
        this.birthdate = birthdate;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
        phone.setContact(this);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        address.setContact(this);
    }
}
