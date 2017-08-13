package com.solstice.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.solstice.exceptions.UserNotFoundException;
import com.solstice.model.Contact;
import com.solstice.services.ContactService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> createEmployee(@Valid @RequestBody Contact contact) {
		contact = service.addNewContact(contact);
		return new ResponseEntity<>(contact, HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateEmployee(@RequestBody Contact contact) {
		service.updateContact(contact);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> findEmployeeById(@PathVariable long id) throws UserNotFoundException {
		Optional<Contact> contact = service.getContactById(id);// .orElseThrow(() -> new UserNotFoundException());
		return contact.isPresent() ? new ResponseEntity<Contact>(contact.get(), HttpStatus.OK)
				: new ResponseEntity<Contact>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> removeEmployeeById(@PathVariable Long id) {
		service.removeContact(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
