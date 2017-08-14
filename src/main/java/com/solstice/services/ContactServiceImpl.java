package com.solstice.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.solstice.exceptions.UniqueEmailException;
import com.solstice.exceptions.UserNotFoundException;
import com.solstice.model.Contact;
import com.solstice.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final ContactRepository repository;

	public ContactServiceImpl(ContactRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Contact addNewContact(Contact contact) throws UniqueEmailException {
		logger.debug("Saving contact: {}", contact);
		if(repository.findByEmail(contact.getEmail()).isPresent()) {
			throw new UniqueEmailException();
		}
		return repository.saveAndFlush(contact);
	}

	@Override
	@Transactional
	public void removeContact(Long id) {
		logger.debug("Removing contact with id: {}", id);
		repository.delete(id);
	}

	@Override
	public Optional<Contact> getContactById(Long id) {
		Contact contact = repository.findOne(id);
		if (contact == null) {
			return Optional.empty();
		} else {
			Optional<Contact> optionalContact = Optional.of(contact);
			return optionalContact;
		}
	}

	@Override
	public List<Contact> getAllContacts() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void updateContact(Contact contact) {
		logger.debug("Updating userId: {}", contact.getId());
		if(contact.getId() == null) {
			throw new UserNotFoundException();
		}
		// TODO: improve the way of the ids of the phone and address to not duplicate then
		Contact persistedContact = repository.findOne(contact.getId());
		if(	contact.getAddress() != null ){
			contact.getAddress().setId(persistedContact.getAddress().getId());
		}
		if(contact.getPhone() != null) {
			contact.getPhone().setId(persistedContact.getPhone().getId());
		}
		repository.saveAndFlush(contact);
	}

	@Override
	public Optional<Contact> getContactByEmail(String email) {
		return repository.findByEmail(email);
	}
}
