package dukejava.week2.stringsfirstassignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Part3Test {

    @Test
    void twoOccurrences_noOccurrence_falseReturned() {
        final String stringA = "lol";
        final String stringB = "loool";
        assertFalse(Part3.twoOccurrences(stringA, stringB));
    }

    @Test
    void twoOccurrences_oneOccurrence_falseReturned() {
        final String stringA = "lol";
        final String stringB = "lollllooo";
        assertFalse(Part3.twoOccurrences(stringA, stringB));
    }

    @Test
    void twoOccurrences_twoOccurrences_trueReturned() {
        final String stringA = "lol";
        final String stringB = "lolllloolol";
        assertTrue(Part3.twoOccurrences(stringA, stringB));
    }
    @Test
    void twoOccurrences_moreOccurrences_trueReturned() {
        final String stringA = "lol";
        final String stringB = "lollollol";
        assertTrue(Part3.twoOccurrences(stringA, stringB));
    }
}