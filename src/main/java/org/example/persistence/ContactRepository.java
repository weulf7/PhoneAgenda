package org.example.persistence;

import org.example.config.DatabaseConfiguration;
import org.example.domain.Contact;
import org.example.transfer.CreateContactRequest;
import org.example.transfer.UpdateContactRequest;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    public void createContact(CreateContactRequest request) throws SQLException {
        String sql="INSERT INTO contacts(first_name, last_name, phone_number) VALUES(?, ?, ?)";

        try(PreparedStatement preparedStatement= DatabaseConfiguration.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,request.getFirstName());
            preparedStatement.setString(2,request.getLastName());
            preparedStatement.setString(3,request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }

    }

    public void deleteContact(long id) throws SQLException {
        String sql="DELETE FROM contacts WHERE id=?";

        try(PreparedStatement preparedStatement=DatabaseConfiguration.getConnection().prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        }

    }

    public void updateContact(long id, UpdateContactRequest request) throws SQLException {
        String sql="UPDATE contacts SET first_name=?, last_name=?, phone_number=? WHERE id=?";

        try(PreparedStatement preparedStatement=DatabaseConfiguration.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,request.getFirstName());
            preparedStatement.setString(2,request.getLastName());
            preparedStatement.setString(3,request.getPhoneNumber());
            preparedStatement.setLong(4,id);

            preparedStatement.executeUpdate();
        }
    }

    public List<Contact> getContact() throws SQLException {
        String sql="SELECT * FROM contacts ";

        List<Contact>contacts=new ArrayList<>();

        try(Statement statement=DatabaseConfiguration.getConnection().createStatement()){
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                Contact contact=new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setPhoneNumber(resultSet.getString("phone_number"));

                contacts.add(contact);
            }
        }
        return contacts;
    }
}
