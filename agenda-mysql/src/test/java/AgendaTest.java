import com.paolamonteiro.core.agenda.controller.Agenda;
import com.paolamonteiro.core.agenda.dao.ContactDAO;
import com.paolamonteiro.core.agenda.model.Contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AgendaTest {

    @Mock
    private ContactDAO contactDAO;

    @InjectMocks
    private Agenda agenda;

    @Before
    public void setup() {
        agenda = new Agenda(contactDAO);
    }

    @Test
    public void addContactTest() {
        Contact paola = new Contact(1, "Paola", "(51)999999999", "paola@ilegra.com");
        Contact gabi = new Contact(2, "Gabriela", "(51)988888888", "gabriela@ilegra.com");
        agenda.addContact(paola);
        agenda.addContact(gabi);
        verify(contactDAO).save(paola);
        verify(contactDAO).save(gabi);
    }

    @Test
    public void searchByNameTest() {
        Contact paola = new Contact(1, "Paola", "(51)999999999", "paola@ilegra.com");
        List<Contact> mockList = new ArrayList<>();
        mockList.add(paola);
        when(contactDAO.getContactByName(paola.getName())).thenReturn(mockList);
        agenda.searchByName("Paola");
        verify(contactDAO).getContactByName(paola.getName());
    }

    @Test
    public void searchByIdTest() {
        Contact paola = new Contact(1, "Paola", "(51)999999999", "paola@ilegra.com");
        List<Contact> mockList = new ArrayList<>();
        mockList.add(paola);
        when(contactDAO.getContactById(paola.getId())).thenReturn(mockList);
        Assert.assertEquals(agenda.searchById(paola.getId()), Optional.of(paola));
    }

    @Test
    public void listContactsTest() {
        Contact yan = new Contact(3, "Yan", "(51)988888888", "yan@ilegra.com");
        Contact camila = new Contact(4, "Camila", "(51)988888888", "camila@ilegra.com");
        Contact nicole = new Contact(5, "Nicole", "(51)988888888", "nicole@ilegra.com");
        List<Contact> contactsForTest = new ArrayList<>(agenda.listContacts());
        contactsForTest.add(yan);
        contactsForTest.add(camila);
        contactsForTest.add(nicole);
        Assert.assertThat(contactsForTest.size(), is(3));
        Assert.assertTrue(contactsForTest.contains(camila));
        Assert.assertEquals(yan, contactsForTest.get(0));
        Assert.assertEquals(camila, contactsForTest.get(1));
        Assert.assertEquals(nicole, contactsForTest.get(2));
    }

    @Test
    public void removeContactTest() {
        Contact paola = new Contact(1, "Paola", "(51)999999999", "paola@ilegra.com");
        List<Contact> contactsForTest = new ArrayList<>(agenda.listContacts());
        contactsForTest.add(paola);
        when(contactDAO.getContactById(paola.getId())).thenReturn(contactsForTest);
        agenda.removeContactById(1);
        verify(contactDAO).deleteContact(1);
    }

    @Test
    public void sortByIdTest() {
        Contact yan = new Contact(3, "Yan", "(51)988888888", "yan@ilegra.com");
        Contact camila = new Contact(2, "Camila", "(51)988888888", "camila@ilegra.com");
        Contact nicole = new Contact(1, "Nicole", "(51)988888888", "nicole@ilegra.com");
        List<Contact> contactsForTest = new ArrayList<>(agenda.listContacts());
        contactsForTest.add(yan);
        contactsForTest.add(camila);
        contactsForTest.add(nicole);
        when(contactDAO.orderContactsById()).thenReturn(contactsForTest);
        agenda.sortById();
        verify(contactDAO).orderContactsById();
    }
    
    @Test
    public void sortByNameTest() {
        Contact yan = new Contact(1, "Yan", "(51)988888888", "yan@ilegra.com");
        Contact camila = new Contact(2, "Camila", "(51)988888888", "camila@ilegra.com");
        Contact nicole = new Contact(3, "Nicole", "(51)988888888", "nicole@ilegra.com");
        List<Contact> contactsForTest = new ArrayList<>(agenda.listContacts());
        contactsForTest.add(yan);
        contactsForTest.add(camila);
        contactsForTest.add(nicole);
        when(contactDAO.orderContactsByName()).thenReturn(contactsForTest);
        agenda.sortByName();
        verify(contactDAO).orderContactsByName();
    }
}