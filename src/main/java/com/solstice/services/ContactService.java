package com.solstice.services;


import java.util.List;
import java.util.Optional;

import com.solstice.exceptions.UniqueEmailException;
import com.solstice.model.Contact;

public interface ContactService {

    Contact addNewContact(Contact contact) throws UniqueEmailException;

    void removeContact(Long id);

    Optional<Contact> getContactById(Long id);

    List<Contact> getAllContacts();

    void updateContact(Contact contact);

	Optional<Contact> getContactByEmail(String email);
}
