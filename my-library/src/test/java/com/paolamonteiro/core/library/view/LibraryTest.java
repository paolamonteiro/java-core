package com.paolamonteiro.core.library.view;

import com.paolamonteiro.core.library.dao.LoanDAO;
import com.paolamonteiro.core.library.models.Book;
import com.paolamonteiro.core.library.models.Loan;
import com.paolamonteiro.core.library.models.Reader;
import com.paolamonteiro.core.library.service.BookService;
import com.paolamonteiro.core.library.service.ReaderService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {
    @Mock
    private BookService bookService;
    @Mock
    private ReaderService readerService;
    @Mock
    private LoanDAO loanDao;

    @InjectMocks
    private Library library;

    private List<Loan> loanList;

    @Before
    public void setup() {
        loanList = new ArrayList<>();
        when(loanDao.getAll()).thenReturn(loanList);
        library = new Library(bookService, readerService, loanDao);
    }

    @Test
    public void makeLoanTest() {
        Book book = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");
        Reader paola = new Reader(1, "Paola");

        when(bookService.getBookById(anyInt())).thenReturn(Optional.of(book));
        when(readerService.getReaderById(anyInt())).thenReturn(Optional.of(paola));
        Assert.assertTrue(library.makeLoan(book, paola));
    }

    @Test
    public void renewLoanTest() {
        Book book = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");
        Reader paola = new Reader(1, "Paola");

        when(bookService.getBookById(anyInt())).thenReturn(Optional.of(book));
        when(readerService.getReaderById(anyInt())).thenReturn(Optional.of(paola));
        library.makeLoan(book, paola);

        Assert.assertTrue(library.renewLoan(book, paola));
    }

    @Test
    public void returnBooksTest() {
        Book book = new Book(1, "The Hound of Baskerville", "Sir Arthur Conan Doyle");
        Reader paola = new Reader(1, "Paola");

        when(bookService.getBookById(anyInt())).thenReturn(Optional.of(book));
        when(readerService.getReaderById(anyInt())).thenReturn(Optional.of(paola));
        library.makeLoan(book, paola);

        Assert.assertTrue(library.returnBooks(book, paola));
    }
}