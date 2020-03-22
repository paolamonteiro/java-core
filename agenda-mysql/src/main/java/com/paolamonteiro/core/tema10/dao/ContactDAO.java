package com.paolamonteiro.core.agenda.dao;

import com.paolamonteiro.core.agenda.exception.AgendaException;
import com.paolamonteiro.core.agenda.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactDAO {

    private DBConnection dbConnection;
    private static Connection connection;

    public ContactDAO() {
        dbConnection = new DBConnection();
    }

    public Integer save(Contact contact) {
        String query = "INSERT INTO contact (id, name, phoneNumber, email) VALUES (default, ?, ?, ?)";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, contact.getName());
            pstm.setString(2, contact.getPhoneNumber());
            pstm.setString(3, contact.getEmail());
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            throw new AgendaException("Connection error: " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return Statement.RETURN_GENERATED_KEYS;
    }

    public List<Contact> getAll() {
        String query = "SELECT * FROM contact";
        List<Contact> contactList = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setPhoneNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
                contactList.add(contact);
            }
            if (contactList.isEmpty()) {
                return Collections.emptyList();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            throw new AgendaException("Error accessing data: " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return contactList;
    }

    public void deleteContact(int id) {
        String query = "DELETE FROM contact WHERE (id = ?)";
        try {
            connection = dbConnection.getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new AgendaException("Error removing data: " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    public List<Contact> getContactById(Integer id) {
        String query = "SELECT * FROM contact WHERE id = ?";
        List<Contact> contacts = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setPhoneNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
                contacts.add(contact);
            }
            if (contacts.isEmpty()) {
                return Collections.emptyList();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            throw new AgendaException("Error accessing data: " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return contacts;
    }

    public List<Contact> getContactByName(String name) {
        String query = "SELECT * FROM contact WHERE name = ?";
        List<Contact> contacts = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setPhoneNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
                contacts.add(contact);
            }
            if (contacts.isEmpty()) {
                return Collections.emptyList();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            throw new AgendaException("Error accessing data: " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return contacts;
    }

    public List<Contact> getContactByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM contact WHERE phoneNumber = ?";
        List<Contact> contacts = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, phoneNumber);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setPhoneNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
                contacts.add(contact);
            }
            if (contacts.isEmpty()) {
                return Collections.emptyList();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            throw new AgendaException("Error accessing data: " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return contacts;
    }

    public List<Contact> orderContactsById() {
        String query = "SELECT * FROM contact ORDER BY id ASC";
        List<Contact> contactList = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setPhoneNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
                contactList.add(contact);
            }
            if (contactList.isEmpty()) {
                return Collections.emptyList();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(connection);
        }
        return contactList;
    }

    public List<Contact> orderContactsByName() {
        String query = "SELECT * FROM contact ORDER BY name ASC";
        List<Contact> contactList = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setPhoneNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
                contactList.add(contact);
            }
            if (contactList.isEmpty()) {
                return Collections.emptyList();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection(connection);
        }
        return contactList;
    }
}