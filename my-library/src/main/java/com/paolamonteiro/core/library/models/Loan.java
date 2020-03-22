package com.paolamonteiro.core.library.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private Integer bookId;
    private Integer readerId;
    private String readerName;
    private String bookTitle;
    private LocalDate loanDate;
    private LocalDate bookReturnDate;
    private Double latePenalty;
    private long daysOfDelay;

    public Loan(Integer bookId, Integer readerId, String readerName, LocalDate loanDate) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.readerName = readerName;
        this.loanDate = loanDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(LocalDate bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public Double getLatePenalty() {
        return latePenalty;
    }

    public void setLatePenalty(Double latePenalty) {
        this.latePenalty = latePenalty;
    }

    public long getDaysOfDelay() {
        return daysOfDelay;
    }

    public void setDaysOfDelay(long daysOfDelay) {
        this.daysOfDelay = daysOfDelay;
    }

    @Override
    public String toString() {
        return ("\n\nLoan Date: "+getLoanDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                +"\nBook ID : "+ this.getReaderId()
                +" -> Reader: "+this.getReaderName()
                +"\n\n");
    }
}
