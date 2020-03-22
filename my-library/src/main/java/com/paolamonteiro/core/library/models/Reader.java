package com.paolamonteiro.core.library.models;

public class Reader {
    private Integer id;
    private String name;

    public Reader(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Boolean equals(Reader nextReader) {
        return this.name.equals(nextReader.getName());
    }

    @Override
    public String toString() {
        return "Reader: " +
                "name: " + this.getName() + "\n";
    }
}
