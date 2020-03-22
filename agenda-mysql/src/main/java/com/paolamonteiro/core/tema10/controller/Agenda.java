package com.paolamonteiro.core.agenda.controller;

import com.paolamonteiro.core.agenda.dao.ContactDAO;
import com.paolamonteiro.core.agenda.exception.AgendaException;
import com.paolamonteiro.core.agenda.model.Contact;

import java.util.List;
import java.util.Optional;

public class Agenda {

    private ContactDAO contactDAO;

    public Agenda(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public Agenda() {
        contactDAO = new ContactDAO();
    }

    public void addContact(Contact contact) {
        Optional<Contact> foundContact = contactDAO.getContactByPhoneNumber(contact.getPhoneNumber()).stream().findAny();
        if (foundContact.isPresent()) {
            throw new AgendaException("Contact already registered.");
        }
        contactDAO.save(contact);
    }

    public Optional<Contact> searchByName(String name) {
        Optional<Contact> contact = contactDAO.getContactByName(name).stream().findAny();
        if (!contact.isPresent()) {
            throw new AgendaException("Contact not found.");
        }
        return contact;
    }

    public Optional<Contact> searchById(int id) {
        Optional<Contact> contact = contactDAO.getContactById(id).stream().findAny();
        if (!contact.isPresent()) {
            throw new AgendaException("Contact not found.");
        }
        return contact;
    }

    public List<Contact> listContacts() {
        return contactDAO.getAll();
    }

    public void removeContactById(int id) {
        Optional<Contact> contactToRemove = contactDAO.getContactById(id).stream().findAny();
        if (!contactToRemove.isPresent()) {
            throw new AgendaException("Contact not found.");
        }
        contactDAO.deleteContact(contactToRemove.get().getId());
    }

    public List<Contact> sortById() {
        return contactDAO.orderContactsById();
    }

    public List<Contact> sortByName() {
        return contactDAO.orderContactsByName();
    }
}