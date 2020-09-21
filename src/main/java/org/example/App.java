package org.example;

import org.example.persistence.ContactRepository;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );

        ContactRepository repository=new ContactRepository();
//        CreateContactRequest request= new CreateContactRequest();
//        request.setFirstName("Antal");
//        request.setLastName("Matyas");
//        request.setPhoneNumber("0770888999");
//        repository.createContact(request);

//        UpdateContactRequest request=new UpdateContactRequest();
//        request.setFirstname("Miklos");
//        request.setLastName("Istvan");
//        request.setPhoneNumber("0112345678");
//        repository.updateContact(2,request);
//        repository.deleteContact(1);

//        System.out.println(repository.getContact());
//
//        repository.deleteContact(6);
    }
}
