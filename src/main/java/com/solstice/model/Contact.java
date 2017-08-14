package com.solstice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Date;

@Validated
@Entity(name = "Contact")
@Table(name = "contact", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Contact implements Serializable {

	private static final long serialVersionUID = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "error.name.notnull")
	@Size(min = 1, max =256, message = "error.name.length")
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

	@Email(message = "error.email.wrong.format")
	@NotNull(message = "error.email.notnull")
	@Size(min = 1, max =256, message = "error.email.length")
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "WEBSITE")
	private String website;

	@Column(name = "BIRTHDATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Date birthdate;

	@JsonInclude(Include.NON_NULL)
	@OneToOne(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Phone phone;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@OneToOne(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Address address;

	public Contact() {
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (favorite ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((largeImageURL == null) ? 0 : largeImageURL.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((smallImageURL == null) ? 0 : smallImageURL.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
