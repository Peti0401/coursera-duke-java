package dukejava.week2.stringsfirstassignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Part1Test {

    @Test
    void findSimpleGene_noStartCodon_emptyStringReturned() {
        final String dna = "ATTTATATATT";
        String expectedResult = "";
        String actualResult = Part1.findSimpleGene(dna);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void findSimpleGene_noEndCodon_emptyStringReturned() {
        final String dna = "ATGTTATATATT";
        String expectedResult = "";
        String actualResult = Part1.findSimpleGene(dna);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void findSimpleGene_notAMultipleOf3_emptyStringReturned() {
        final String dna = "ATGGGTAA";
        String expectedResult = "";
        String actualResult = Part1.findSimpleGene(dna);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void findSimpleGene_multipleOf3_emptyStringReturned() {
        final String dna = "AAGATGGAGTAAGGTTAA";
        String expectedResult = "ATGGAGTAA";
        String actualResult = Part1.findSimpleGene(dna);
        assertEquals(expectedResult, actualResult);
    }
}