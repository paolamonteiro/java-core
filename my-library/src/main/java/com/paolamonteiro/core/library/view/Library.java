package com.paolamonteiro.core.library.view;

import com.paolamonteiro.core.library.dao.LoanDAO;
import com.paolamonteiro.core.library.exceptions.LibraryException;
import com.paolamonteiro.core.library.models.Book;
import com.paolamonteiro.core.library.models.Loan;
import com.paolamonteiro.core.library.models.Reader;
import com.paolamonteiro.core.library.service.BookService;
import com.paolamonteiro.core.library.service.ReaderService;
import com.paolamonteiro.core.library.service.ReportService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.time.temporal.ChronoUnit.DAYS;

public class Library {

    private BookService bookService;
    private ReaderService readerService;
    private LoanDAO loanDao;

    private ReportService reports = new ReportService();

    public static final Integer DEADLINE = 7;
    private static final Integer AMOUNT_OF_BOOKS = 5;
    private static final Double PENALTY_PER_DAY = 5.00;

    private List<Loan> loanList;

    public Library(BookService bookService, ReaderService readerService, LoanDAO loanDao) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.loanDao = loanDao;
        this.loanList = new ArrayList<>(loanDao.getAll());
    }
    public Library() {
        loanList = new ArrayList<>(loanDao.getAll());
    }

    public boolean saveBook(Book book) {
        return bookService.saveBook(book);
    }

    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookService.getBookByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookService.getBooksByAuthor(author);
    }

    public boolean removeBookById(Integer id) {
        return bookService.removeBookById(id);
    }

    public boolean saveReader(Reader reader) {
        return readerService.saveReader(reader);
    }

    public boolean makeLoan(Book book, Reader reader) {
        Optional<Book> bookToTake = bookService.getBookById(book.getId());
        Optional<Reader> reader1 = readerService.getReaderById(reader.getId());

        if (!(bookToTake.isPresent()) || !reader1.isPresent()) {
            return false;
        }
        if (loanList.size() > 0) {
            for (Loan loan : loanList) {
                if (loan.getBookId().equals(book.getId())) {
                    return false;
                }
                List<Loan> loansOfThisReader = new ArrayList<>();
                if (loan.getReaderId().equals(reader.getId())) {
                    loansOfThisReader.add(loan);
                    if (loansOfThisReader.size() > AMOUNT_OF_BOOKS) {
                        return false;
                    }
                }
            }
        }
        loanList.add(new Loan(bookToTake.get().getId(), reader1.get().getId(), reader1.get().getName(), LocalDate.now()));
        loanDao.save(loanList);
        return true;
    }

    public boolean returnBooks(Book book, Reader reader) {
        for (Loan loan : loanList) {
            if (loan.getBookId().equals(book.getId()) && loan.getReaderId().equals(reader.getId())) {
                loan.setBookReturnDate(LocalDate.now());
                loan.setLatePenalty(calcPenalty(loan));
                if (loan.getLatePenalty() > 0.00) {
                    throw new LibraryException("Please, pay the late penalty: $" + loan.getLatePenalty());
                }
                loanDao.save(loanList);
            }
        }
        return true;
    }

    public double calcPenalty(Loan loan) {
        double penalty = 0.00;
        long totalDays = DAYS.between(loan.getLoanDate(), loan.getBookReturnDate());
        if (totalDays > DEADLINE) {
            penalty = (totalDays * PENALTY_PER_DAY) - DEADLINE;
        }
        return penalty;
    }

    public boolean renewLoan(Book book, Reader reader) {
        for (Loan loan : loanList) {
            if (loan.getBookId().equals(book.getId()) && loan.getReaderId().equals(reader.getId())) {
                loan.setBookReturnDate(LocalDate.now());
                loan.setLatePenalty(calcPenalty(loan));
                if (loan.getLatePenalty() > 0.00) {
                    throw new LibraryException("Please, pay the late penalty: $" + loan.getLatePenalty());
                }
                makeLoan(book, reader);
                loanDao.save(loanList);
            }
        }
        return true;
    }

    public String delayedLoansReport() {
        return reports.delayedBooks(loanList);
    }

    public String getTop10Report() { return reports.getTop10ReadersReport(loanList); }

    public List<Loan> getLoans() {
        return loanList;
    }
}
