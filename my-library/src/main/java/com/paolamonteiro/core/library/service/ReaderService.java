package com.paolamonteiro.core.library.service;

import com.paolamonteiro.core.library.dao.ReaderDAO;
import com.paolamonteiro.core.library.exceptions.LibraryException;
import com.paolamonteiro.core.library.models.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReaderService {

    private ReaderDAO readerDao = new ReaderDAO();

    private List<Reader> readerList = new ArrayList<>();

    public boolean saveReader(Reader reader) {
        readerList.forEach(oldReader -> {
            if (oldReader.equals(reader))
                throw new LibraryException("Reader already registered.");
        });
        readerList.add(reader);
        readerDao.save(readerList);
        return true;
    }

    public Optional<Reader> getReaderById(Integer id) {
        return readerDao.getAll().stream().filter(r -> r.getId().equals(id)).findAny();
    }
}
