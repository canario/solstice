package com.solstice.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solstice.exceptions.UserNotFoundException;
import com.solstice.model.Contact;
import com.solstice.services.ContactService;

@RestController
@RequestMapping(ContactController.BASE_URL)
@Validated
public class ContactController {

	public static final String BASE_URL = "/api/v1/contact";

	private final ContactService service;

	public ContactController(final ContactService service) {
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contact>> getAllContacts() {
		return new ResponseEntity<>(service.getAllContacts(), HttpStatus.OK);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
		contact = service.addNewContact(contact);
		return new ResponseEntity<>(contact, HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateContact(@Valid @RequestBody Contact contact) {
		service.updateContact(contact);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> findContactById(@PathVariable long id) throws UserNotFoundException {
		Contact contact = service.getContactById(id).orElseThrow(() -> new UserNotFoundException());
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> removeContactById(@PathVariable Long id) {
		service.removeContact(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> findContactByEmail(@Valid @Email String mail)
			throws UserNotFoundException {
		Optional<Contact> contact = service.getContactByEmail(mail);// .orElseThrow(() -> new UserNotFoundException());
		return contact.isPresent() ? new ResponseEntity<Contact>(contact.get(), HttpStatus.OK)
				: new ResponseEntity<Contact>(HttpStatus.OK);
	}

}
