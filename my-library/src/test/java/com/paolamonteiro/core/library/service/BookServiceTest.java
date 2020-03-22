package com.paolamonteiro.core.library.service;

import com.paolamonteiro.core.library.dao.BookDAO;
import com.paolamonteiro.core.library.models.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookService bookService;

    private List<Book> mockBookList;

    @Before
    public void setup() {
        mockBookList = new ArrayList<>();
        when(bookDAO.getAll()).thenReturn(mockBookList);
        bookService = new BookService(bookDAO);
    }

    @Test
    public void saveBookTest() {
        Book book1 = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");
        Book book2 = new Book(2, "The Body in the Library", "Agatha Christie");

        bookService.saveBook(book1);
        bookService.saveBook(book2);

        Assert.assertTrue(bookService.getBooks().contains(book1));
        Assert.assertTrue(bookService.getBooks().contains(book2));

        Assert.assertEquals(2, bookService.getBooks().size());
    }

    @Test
    public void removeBookByIdTest() {
        Book book = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");

        bookService.removeBookById(1);
        Assert.assertFalse(bookService.getBooks().contains(book));
    }

    @Test
    public void getBooksTest() {
        Book book1 = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");
        Book book2 = new Book(2, "The Body in the Library", "Agatha Christie");

        bookService.saveBook(book1);
        bookService.saveBook(book2);

        Assert.assertEquals(2, bookService.getBooks().size());
    }

    @Test
    public void getBookByTitle() {
        Book book = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");

        bookService.saveBook(book);

        Assert.assertEquals(bookService.getBookByTitle(book.getTitle()), Optional.of(book));
    }

    @Test
    public void getBooksByAuthor() {
        Book book = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");

        bookService.saveBook(book);

        List<Book> books = bookService.getBooksByAuthor("Sir Arthur Conan Doyle");

        Assert.assertTrue(books.contains(bookService.getBooksByAuthor(book.getAuthor()).get(0)));
    }
}