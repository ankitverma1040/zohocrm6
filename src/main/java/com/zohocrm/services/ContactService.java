package com.zohocrm.services;

import java.util.List;

import com.zohocrm.entities.Contact;

public interface ContactService {
	public void saveContact(Contact contact);

	 List<Contact> getContacts();

	public Contact getContactById(long id);
}
