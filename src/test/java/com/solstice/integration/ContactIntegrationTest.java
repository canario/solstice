package com.solstice.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.solstice.model.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContactIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreate() {

		Contact contactRequest = createContact();
		HttpEntity<Contact> entity = new HttpEntity<Contact>(contactRequest);

		ResponseEntity<Contact> createResponse = restTemplate.exchange("/api/v1/contact", HttpMethod.POST, entity,
				Contact.class);

		assertThat(createResponse.getBody().getEmail()).isEqualTo(contactRequest.getEmail());

		ResponseEntity<Contact> response = restTemplate
				.getForEntity("/api/v1/contact/" + createResponse.getBody().getId(), Contact.class);

		Contact contact = response.getBody();

		assertThat(contact.getId()).isEqualTo(createResponse.getBody().getId());
	}

	@Test
	public void testUpdate() {

		Contact contactRequest = createContact();
		HttpEntity<Contact> entity = new HttpEntity<Contact>(contactRequest);

		ResponseEntity<Contact> createResponse = restTemplate.exchange("/api/v1/contact", HttpMethod.POST, entity,
				Contact.class);

		assertThat(createResponse.getBody().getEmail()).isEqualTo(contactRequest.getEmail());

		createResponse.getBody().setWebsite("https://www.solstice.com/");
		entity = new HttpEntity<Contact>(createResponse.getBody());
		restTemplate.exchange("/api/v1/contact", HttpMethod.PUT, entity, Contact.class);

		ResponseEntity<Contact> response = restTemplate
				.getForEntity("/api/v1/contact/" + createResponse.getBody().getId(), Contact.class);

		Contact contact = response.getBody();

		assertThat(contact.getId()).isEqualTo(createResponse.getBody().getId());

		assertThat(contact.getWebsite()).isEqualTo(createResponse.getBody().getWebsite());
	}

	@Test
	public void testDelete() {

		Contact contactRequest = createContact();
		HttpEntity<Contact> entity = new HttpEntity<Contact>(contactRequest);

		ResponseEntity<Contact> createResponse = restTemplate.exchange("/api/v1/contact", HttpMethod.POST, entity,
				Contact.class);

		assertThat(createResponse.getBody().getEmail()).isEqualTo(contactRequest.getEmail());

		entity = new HttpEntity<Contact>(contactRequest);
		restTemplate.delete("/api/v1/contact/" + createResponse.getBody().getId());

		ResponseEntity<Contact> response = restTemplate
				.getForEntity("/api/v1/contact/" + createResponse.getBody().getId(), Contact.class);

		Contact contact = response.getBody();

		assertThat(contact).isEqualTo(null);

	}

	
	@Test
	public void testGetByIdEmpty() {
		ResponseEntity<Contact> response = restTemplate.getForEntity("/api/v1/contact/1", Contact.class);

		Contact contact = response.getBody();

		assertThat(contact).isEqualTo(null);
	}

	@Test
	public void testGetAll() {
		Contact contactRequest = createContact();
		HttpEntity<Contact> entity = new HttpEntity<Contact>(contactRequest);

		ResponseEntity<Contact> createResponse = restTemplate.exchange("/api/v1/contact", HttpMethod.POST, entity,
				Contact.class);

		createResponse = restTemplate.exchange("/api/v1/contact", HttpMethod.POST, entity,
				Contact.class);
		ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/contact", List.class);

		List<Contact> contacts = response.getBody();

		assertThat(contacts.size()).isEqualTo(2);
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
