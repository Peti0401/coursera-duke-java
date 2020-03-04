package dukejava.week4;

import edu.duke.FileResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BabyNames {

    final static String BY_YEAR_FOLDER = "/Users/gyarmatip/Documents/Coding/LearningProjects/Coursera/Java Programming - Solving Problems with Software/duke-java-projects/week4/src/main/resources/dukejava/week4/us_babynames/us_babynames_by_year";
    final static String BY_DECADE_FOLDER = "/Users/gyarmatip/Documents/Coding/LearningProjects/Coursera/Java Programming - Solving Problems with Software/duke-java-projects/week4/src/main/resources/dukejava/week4/us_babynames/us_babynames_by_decade";
    final static String TEST_FOLDER = "/Users/gyarmatip/Documents/Coding/LearningProjects/Coursera/Java Programming - Solving Problems with Software/duke-java-projects/week4/src/main/resources/dukejava/week4/us_babynames/us_babynames_test";

    @AllArgsConstructor
    @ToString
    static class BirthData {
        int total;
        int numMales;
        int numFemales;
        int numDistinctMaleNames;
        int numDistinctFemaleNames;
    }

    @SneakyThrows
    static BirthData totalBirths(FileResource fileResource) {
        List<CSVRecord> records = fileResource.getCSVParser(false).getRecords();

        int total = records.stream()
                .map(csvRecord -> Integer.parseInt(csvRecord.get(2)))
                .reduce(Integer::sum).get();

        int numGirls = records.stream()
                .filter(csvRecord -> csvRecord.get(1).equals("F"))
                .map(csvRecord -> Integer.parseInt(csvRecord.get(2)))
                .reduce(Integer::sum).get();

        int numDistinctGirlNames = ((int) records.stream()
                .filter(csvRecord -> csvRecord.get(1).equals("F"))
                .count());

        return new BirthData(total, total - numGirls, numGirls, records.size() - numDistinctGirlNames, numDistinctGirlNames);
    }

    @SneakyThrows
    static BirthData totalBirths(int year) {
        final String filePath = String.format("%s/yob%d.csv", BY_YEAR_FOLDER, year);
        FileResource fileResource = new FileResource(new File(filePath));
        return totalBirths(fileResource);
    }

    @SneakyThrows
    static int getRank(int year, String name, String gender) {
        final String filePath = String.format("%s/yob%d.csv", BY_YEAR_FOLDER, year);
        FileResource fileResource = new FileResource(new File(filePath));
        List<CSVRecord> records = fileResource.getCSVParser(false).getRecords();

        Optional<CSVRecord> csvRecordOptional = records.stream()
                .filter(csvRecord -> csvRecord.get(0).equals(name) && csvRecord.get(1).equals(gender))
                .findFirst();

        if (csvRecordOptional.isPresent()) {
            if (gender.equals("F")) return 1 + records.indexOf(csvRecordOptional.get());
            BirthData birthData = totalBirths(fileResource);
            if (gender.equals("M")) return 1 + records.indexOf(csvRecordOptional.get()) - birthData.numDistinctFemaleNames;
        }

        return -1;
    }

    @SneakyThrows
    static String getName(int year, int rank, String gender) {
        final String filePath = String.format("%s/yob%d.csv", BY_YEAR_FOLDER, year);
        FileResource fileResource = new FileResource(new File(filePath));
        List<CSVRecord> records = fileResource.getCSVParser(false).getRecords();

        BirthData birthData = totalBirths(fileResource);
        if (!(rank <= birthData.numDistinctFemaleNames && rank <= birthData.numDistinctMaleNames)) return "NO_NAME";

        List<String> allNames = records.stream()
                .map(csvRecord -> csvRecord.get(0))
                .collect(Collectors.toList());

        int femaleIndex = rank - 1;
        int maleIndex = rank + birthData.numDistinctFemaleNames - 1;

        return gender.equals("F") ? allNames.get(femaleIndex) : allNames.get(maleIndex);
    }

    static String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        return rank != -1 ? getName(newYear, rank, gender) : "NO_NAME";
    }

    @AllArgsConstructor
    @ToString
    static class RankData {
        int year;
        int rank;
    }

    static RankData yearOfHighestRank(String name, String gender, int startYear, int endYear) {
        if (!(1880 <= startYear && endYear <= 2014)) return null;

        int min = Integer.MAX_VALUE;
        int year = 0;
        for (int i = startYear; i <= endYear; i++) {
            int rank = getRank(i, name, gender);
            if (rank == -1) continue;
            if (rank < min) {
                min = rank;
                year = i;
            }
        }
        return new RankData(year, min);
    }

    static RankData yearOfHighestRank(String name, String gender) {
        return yearOfHighestRank(name, gender, 1880, 2014);
    }

    static double getAverageRank(String name, String gender, int startYear, int endYear) {
        int sum = 0;
        int count = 0;
        for (int i = startYear; i <= endYear; i++) {
            int rank = getRank(i, name, gender);
            if (rank == -1) continue;
            sum += rank;
            count++;
        }

        return count != 0 ? (double) sum / (double) count : -1.0;
    }

    static double getAverageRank(String name, String gender) {
        return getAverageRank(name, gender, 1880, 2014);
    }

    @SneakyThrows
    static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        final String filePath = String.format("%s/yob%d.csv", BY_YEAR_FOLDER, year);
        FileResource fileResource = new FileResource(new File(filePath));
        List<CSVRecord> records = fileResource.getCSVParser(false).getRecords();

        Optional<CSVRecord> recordOptional = records.stream()
                .filter(csvRecord -> csvRecord.get(0).equals(name))
                .findAny();
        if (recordOptional.isEmpty()) return -1;

        CSVRecord csvRecord = recordOptional.get();
        BirthData birthData = totalBirths(fileResource);
        List<CSVRecord> sublist = new ArrayList<>();
        if (gender.equals("F")) {
            sublist = records.subList(0, records.indexOf(csvRecord));
        } else {
            records = records.stream().filter(record -> record.get(1).equals("M")).collect(Collectors.toList());
            sublist = records.subList(birthData.numDistinctFemaleNames, records.indexOf(csvRecord));
        }


        return sublist.stream()
                .map(record -> Integer.parseInt(record.get(2)))
                .reduce(Integer::sum).orElse(-1);
    }

    @SneakyThrows
    static int getTotalBirthsWithName(int year, String name, String gender) {
        final String filePath = String.format("%s/yob%d.csv", BY_YEAR_FOLDER, year);
        FileResource fileResource = new FileResource(new File(filePath));
        List<CSVRecord> records = fileResource.getCSVParser(false).getRecords();

        return records.stream()
                .filter(csvRecord -> csvRecord.get(0).equals(name))
                .map(csvRecord -> Integer.parseInt(csvRecord.get(2)))
                .reduce(Integer::sum).orElse(0);
    }


}
