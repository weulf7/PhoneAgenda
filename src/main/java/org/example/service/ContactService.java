package org.example.service;

import org.example.domain.Contact;
import org.example.persistence.ContactRepository;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import java.sql.SQLException;
import java.util.List;

public class ContactService {

    private static ContactRepository contactRepository=new ContactRepository();

    public void createContact(CreateContactRequest request) throws SQLException {
        System.out.println("Creating contact:"+request);
        contactRepository.createContact(request);
    }

    public List<Contact>getContact() throws SQLException {
        System.out.println("Retrieving contacts:");
        List<Contact> contacts = contactRepository.getContact();

        return contacts;
    }

    public void updateContact(long id, UpdateContactRequest request) throws SQLException {
        System.out.println("Updating contact:"+id);
        contactRepository.updateContact(id,request);
    }

    public void deleteContact(long id) throws SQLException {
        System.out.println("Deleting contact:"+id);{
            contactRepository.deleteContact(id);
        }
    }
}
