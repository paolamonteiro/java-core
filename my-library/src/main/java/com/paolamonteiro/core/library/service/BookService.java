package com.paolamonteiro.core.library.service;

import com.paolamonteiro.core.library.dao.BookDAO;
import com.paolamonteiro.core.library.exceptions.BookNotFoundException;
import com.paolamonteiro.core.library.exceptions.LibraryException;
import com.paolamonteiro.core.library.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {

    private BookDAO bookDao;
    private List<Book> bookList;

    public BookService(BookDAO bookDao){
        this.bookDao = bookDao;
        this.bookList = new ArrayList<>(bookDao.getAll());
    }

    public BookService() {
        bookDao = new BookDAO();
        bookList = new ArrayList<>(bookDao.getAll());
    }

    public boolean saveBook(Book book) {
        bookList.forEach(oldBook -> {
            if (oldBook.equals(book))
                throw new LibraryException("We already have this book.");
        });
        bookList.add(book);
        bookDao.save(bookList);
        return true;
    }

    public boolean removeBookById(Integer id) {
        Optional<Book> bookToRemove = bookList.stream().filter(b -> b.getId().equals(id)).findAny();
        bookToRemove.ifPresent(book -> bookList.remove(bookToRemove.get()));
        bookDao.save(bookList);
        return true;
    }

    public List<Book> getBooks() {
        return bookList;
    }

    public Optional<Book> getBookByTitle(String title) {
        Optional<Book> book = Optional.ofNullable(bookList.stream().filter(b -> b.getTitle().contains(title)).findFirst().orElseThrow(IllegalArgumentException::new));
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book not found.");
        }
        return book;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> listSameAuthor = bookList.stream().filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase())).collect(Collectors.toList());
        if (listSameAuthor.isEmpty()) {
            throw new BookNotFoundException("We don't have this author in the catalog yet.");
        }
        return listSameAuthor;
    }

    public Optional<Book> getBookById(int id) {
        Optional<Book> book = bookList.stream().filter(b -> b.getId() == id).findAny();
        if (!book.isPresent()) {
            throw new BookNotFoundException("Copy not found");
        }
        return book;
    }
}
