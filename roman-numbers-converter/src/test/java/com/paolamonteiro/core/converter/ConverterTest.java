package com.paolamonteiro.core.converter;

import org.junit.Assert;
import org.junit.Test;

public class ConverterTest {

    private Converter converter = new Converter();

    @Test
    public void integerToRomanTest() {
        Assert.assertEquals("I", converter.integerToRoman(1));
        Assert.assertEquals("IV", converter.integerToRoman(4));
        Assert.assertEquals("V", converter.integerToRoman(5));
        Assert.assertEquals("IX", converter.integerToRoman(9));
        Assert.assertEquals("XI", converter.integerToRoman(11));
        Assert.assertEquals("XIV", converter.integerToRoman(14));
        Assert.assertEquals("XV", converter.integerToRoman(15));
        Assert.assertEquals("XIX", converter.integerToRoman(19));
        Assert.assertEquals("XX", converter.integerToRoman(20));
    }

    @Test
    public void romanToIntegerTest() {
        Assert.assertEquals(1, converter.romanToInteger("I"));
        Assert.assertEquals(4, converter.romanToInteger("IV"));
        Assert.assertEquals(5, converter.romanToInteger("V"));
        Assert.assertEquals(9, converter.romanToInteger("IX"));
        Assert.assertEquals(11, converter.romanToInteger("XI"));
        Assert.assertEquals(14, converter.romanToInteger("XIV"));
        Assert.assertEquals(15, converter.romanToInteger("XV"));
        Assert.assertEquals(19, converter.romanToInteger("XIX"));
        Assert.assertEquals(20, converter.romanToInteger("XX"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGreaterThan20Test() {
        converter.romanToInteger("XXI");
        converter.romanToInteger("C");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidDigitsTest() {
        converter.romanToInteger("VX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentWithNumberTest() {
        converter.romanToInteger("2X");
        converter.romanToInteger("X8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void romanToIntNullTest() {
        converter.romanToInteger(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void romanToIntEmptyArgumentTest() {
        converter.romanToInteger(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void integerToRomanErrorTest() {
        converter.integerToRoman(0);
        converter.integerToRoman(100);
        converter.integerToRoman(21);
    }
}