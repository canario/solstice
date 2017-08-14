package com.solstice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.solstice.model.Contact;
import com.solstice.repository.ContactRepository;
import com.solstice.services.ContactService;
import com.solstice.services.ContactServiceImpl;

public class ContactServiceTest {

	private ContactRepository repository;

	private ContactService contactService;

	@Before
	public void setUp() {
		repository = Mockito.mock(ContactRepository.class);
		contactService = new ContactServiceImpl(repository);
	}

	@Test
	public void testGetAllContacts() {

		List<Contact> contacts = createContacts();
		when(repository.findAll()).thenReturn(contacts);
		List<Contact> contactsResponse = contactService.getAllContacts();

		assertThat(contactsResponse.size()).isEqualTo(contacts.size());
		assertThat(contactsResponse.get(0).getCompany()).isEqualTo(contacts.get(0).getCompany());
		assertThat(contactsResponse.get(0).getEmail()).isEqualTo(contacts.get(0).getEmail());
		assertThat(contactsResponse.get(0).getName()).isEqualTo(contacts.get(0).getName());
	}

	@Test
	public void testGetContactById() {

		Contact contact = createContact();
		when(repository.findOne(new Long(1))).thenReturn(contact);
		Optional<Contact> contactResponseOptional = contactService.getContactById(new Long(1));
		if (contactResponseOptional.isPresent()) {
			Contact contactResponse = contactResponseOptional.get();
			assertThat(contactResponse.getId()).isEqualTo(contact.getId());
			assertThat(contactResponse.getCompany()).isEqualTo(contact.getCompany());
			assertThat(contactResponse.getEmail()).isEqualTo(contact.getEmail());
			assertThat(contactResponse.getName()).isEqualTo(contact.getName());
		}
	}

	@Test
	public void testGetContactByIdEmptyResponse() {

		when(repository.findOne(new Long(1))).thenReturn(null);
		Optional<Contact> contactResponseOptional = contactService.getContactById(new Long(1));
		assertThat(contactResponseOptional.isPresent()).isEqualTo(false);
	}

	@Test
	public void testCreate() {

		Contact contact = createContact();
		when(repository.saveAndFlush(contact)).thenReturn(contact);
		when(repository.findByEmail(contact.getEmail())).thenReturn(Optional.empty());
		Contact contactResponse = contactService.addNewContact(contact);
		assertThat(contactResponse.getId()).isEqualTo(contact.getId());
	}

	private List<Contact> createContacts() {
		List<Contact> contacts = new ArrayList<>();
		contacts.add(createContact());
		return contacts;
	}

	private Contact createContact() {
		Contact contact = new Contact();
		contact.setEmail("essie@vaill.com");
		contact.setName("Essie Vaill");
		contact.setCompany("Litronic Industries");
		contact.setId(new Long(1));
		return contact;
	}

}
