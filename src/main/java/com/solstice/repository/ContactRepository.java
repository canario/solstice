package com.solstice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solstice.model.Contact;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByEmail(String email);

}
