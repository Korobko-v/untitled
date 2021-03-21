package org.levelup.trello.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("ALL")
public class StringUtilsTest {

    @Test
    @DisplayName("reverse(String): Should throw NPE if input string is null")
    public void testReverse_whenInputIsNull_thenReturnNull() {
//        String result = StringUtils.reverse(null);
//        Assertions.assertNull(result);
        Assertions.assertThrows(NullPointerException.class, () -> StringUtils.reverse(null));
    }

    @Test
    public void testReverse_whenInputIsEmpty_thenReturnSameString() {
        String emptyString = "";
        String result = StringUtils.reverse(emptyString);
        Assertions.assertEquals(emptyString, result);
        Assertions.assertSame(emptyString, result);
    }
    @Test
    public void testReverse_whenUsualString_thenReverseAndReturn() {
        String original = "qwerty";
        String expectedResult = "ytrewq";
        String result = StringUtils.reverse(original);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testConcat() {
        String s1 = "a";
        String s2 = "b";
        String expected = "ab";
        String result = StringUtils.concat(s1, s2);
        Assertions.assertEquals(expected, result);
    }

}