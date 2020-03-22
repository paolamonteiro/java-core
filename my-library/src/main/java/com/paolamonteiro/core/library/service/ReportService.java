package com.paolamonteiro.core.library.service;

import com.paolamonteiro.core.library.models.Loan;
import com.paolamonteiro.core.library.view.Library;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReportService {

    public String delayedBooks(List<Loan> loans) {
        StringBuilder reportDelay = new StringBuilder(" ");
        for (Loan loan : loans) {
            LocalDate returnDate = Optional.ofNullable(loan.getBookReturnDate()).orElse(LocalDate.now());
            long loanDays = DAYS.between(loan.getLoanDate(), returnDate);
            long delayedDays = loanDays - Library.DEADLINE;
            if (delayedDays > 0) {
                reportDelay.append("Name: ").append(loan.getReaderName())
                        .append(" , days of delay: ")
                        .append(delayedDays)
                        .append("\n");
            }
        }
        return reportDelay.toString();
    }

    public String getTop10ReadersReport(List<Loan> loans) {

        StringBuilder top10Report = new StringBuilder(" ");

        loans.stream()
                .collect(Collectors.groupingBy(loan -> loan.getReaderName(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .limit(10)
                .collect(Collectors.toList())
            .forEach(l -> top10Report.append(l).append("\n"));

         return top10Report.toString();
    }
}
