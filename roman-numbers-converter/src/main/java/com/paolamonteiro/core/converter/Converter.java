package com.paolamonteiro.core.converter;

public class Converter {

    private String romans[] = {"X", "IX", "V", "IV", "I"};
    private int integer[] = {10, 9, 5, 4, 1};

    public String integerToRoman(int number) throws IllegalArgumentException {

        if (!validateIntDigits(number)) {
            throw new IllegalArgumentException("Please type numbers from 1 to 20.");
        }
        String result = "";
        int i = 0;
        while (number > 0) {
            if (number >= integer[i]) {
                result = result + romans[i];
                number -= integer[i];
            } else {
                i++;
            }
        }
        return result;
    }

    public int romanToInteger(String roman) throws IllegalArgumentException, NullPointerException {

        String previous = "";
        int result = 0;

        if (!validateStringDigits(roman)) {
            throw new IllegalArgumentException("Please type roman numbers from 1 (I) to 20 (XX).");
        }
            for (String chars : roman.split("")) {
                switch (chars) {
                    case "I":
                        result = result + 1;
                        previous = "I";
                        break;
                    case "V":
                        if (previous.equals("I")) {
                            result = result + 3;
                        } else {
                            result = result + 5;
                        }
                        previous = "V";
                        break;
                    case "X":
                        if (previous.equals("I")) {
                            result = result + 8;
                        } else {
                            result = result + 10;
                        }
                        previous = "X";
                        break;
                }
            }
        return result;
    }

    private boolean validateIntDigits(int number) {
        return (number > 0 && number <= 20);
    }

    private boolean validateStringDigits(String roman) {

        char prevChar = ' ';
        for (char digit : roman.toCharArray()) {
            if (digit != 'X' && digit != 'V' && digit != 'I') {
                return false;
            }
            if(prevChar == 'V' && digit != 'I') {
                return false;
            }
            prevChar = digit;
        }
        if(roman == null) {
            return false;
        }
        if (roman.isEmpty()) {
            return false;
        }
        if (roman.length() >= 3 && roman.charAt(0) == 'X' && roman.charAt(1) == 'X') {
            return false;
        }
        return true;
    }
}