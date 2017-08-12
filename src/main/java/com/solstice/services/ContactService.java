package com.solstice.services;


import java.util.List;
import java.util.Optional;

import com.solstice.model.Contact;

public interface ContactService {

    void addNewContact(Contact contact);

    void removeContact(Long id);

    Optional<Contact> getContactById(Long id);

    List<Contact> getAllContacts();

    void updateContact(Contact contact);
}
