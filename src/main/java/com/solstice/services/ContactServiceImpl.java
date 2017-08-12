package com.solstice.services;

import com.solstice.model.Contact;
import com.solstice.repository.ContactRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final ContactRepository repository;

    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void addNewContact(Contact contact) {
        logger.debug("Saving contact: {}", contact);
        repository.saveAndFlush(contact);
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
        if (contact == null){
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
        repository.saveAndFlush(contact);
    }
}
