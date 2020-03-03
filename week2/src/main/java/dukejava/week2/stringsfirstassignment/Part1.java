package dukejava.week2.stringsfirstassignment;

public class Part1 {

    public static String findSimpleGene(String dna) {
        final String startCodon = "ATG";
        final String endCodon = "TAA";
        if (!dna.contains(startCodon) || !dna.contains(endCodon)) return "";

        final int startIndex = dna.indexOf(startCodon);
        final int endIndex = dna.indexOf(endCodon) + endCodon.length() - 1;
        final String dnaStrand = dna.substring(startIndex, endIndex + 1);

        return dnaStrand.length() % 3 == 0 ? dnaStrand : "";
    }

}