package dukejava.week4;

import static dukejava.week4.BabyNames.*;

public class BabyNamesDriver {

    public static void main(String[] args) {

        // q1
        int numFemaleNamesIn1900 = totalBirths(1900).numDistinctFemaleNames;
        System.out.println("numFemaleNamesIn1900 = " + numFemaleNamesIn1900);

        // q2
        int numMaleNamesIn1905 = totalBirths(1905).numDistinctMaleNames;
        System.out.println("numMaleNamesIn1905 = " + numMaleNamesIn1905);

        // q3
        int emilyRank1960 = getRank(1960, "Emily", "F");
        System.out.println("emilyRank1960 = " + emilyRank1960);

        // q4
        int frankRank1971 = getRank(1971, "Frank", "M");
        System.out.println("frankRank1971 = " + frankRank1971);

        // q5
        String femaleNameAtRank350In1980 = getName(1980, 350, "F");
        System.out.println("femaleNameAtRank350In1980 = " + femaleNameAtRank350In1980);

        // q6
        String maleNameAtRank450in1982 = getName(1982, 450, "M");
        System.out.println("maleNameAtRank450in1982 = " + maleNameAtRank450in1982);

        // q7
        String alternativeSusan1972_2014 = whatIsNameInYear("Susan", 1972, 2014, "F");
        System.out.println("alternativeSusan1972_2014 = " + alternativeSusan1972_2014);

        // q8
        String alternativeOwne1974_2014 = whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println("alternativeOwne1974_2014 = " + alternativeOwne1974_2014);

        // q9
        int highestRankGenevieve = yearOfHighestRank("Genevieve", "F").year;
        System.out.println("highestRankGenevieve = " + highestRankGenevieve);

        // q10
        int highestRankMich = yearOfHighestRank("Mich", "M").year;
        System.out.println("highestRankMich = " + highestRankMich);

        // q11
        double averageRankSusan = getAverageRank("Susan", "F");
        System.out.println("averageRankSusan = " + averageRankSusan);

        // q12
        double averageRankRobert = getAverageRank("Robert", "M");
        System.out.println("averageRankRobert = " + averageRankRobert);

        // q13
        int totalBirthsRankedHigher1990_Emily = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println("totalBirthsRankedHigher1990_Emily = " + totalBirthsRankedHigher1990_Emily);

    }

}
