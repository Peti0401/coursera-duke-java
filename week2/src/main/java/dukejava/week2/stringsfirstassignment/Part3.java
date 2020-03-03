package dukejava.week2.stringsfirstassignment;

public class Part3 {

    /**
     * This method returns true if stringA appears at least twice in stringB, otherwise it returns false.
     * @param stringA
     * @param stringB
     * @return
     */
    public static boolean twoOccurrences(String stringA, String stringB) {
        return stringB.indexOf(stringA) != stringB.lastIndexOf(stringA);
    }


}
