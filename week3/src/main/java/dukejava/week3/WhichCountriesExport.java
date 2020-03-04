package dukejava.week3;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WhichCountriesExport {

    public static List<String> whichCountriesExport(CSVParser csvParser, String item) throws IOException {

        return csvParser.getRecords()
                .stream()
                .filter(csvRecord -> csvRecord.get("Exports").contains(item))
                .map(csvRecord -> csvRecord.get("Country"))
                .collect(Collectors.toList());

    }

    public static String countryInfo(CSVParser csvParser, String country) throws IOException {
        Optional<CSVRecord> countryOptional = csvParser.getRecords().stream()
                .filter(csvRecord -> csvRecord.get(0).equals(country))
                .findFirst();

        if (countryOptional.isPresent()) {
            CSVRecord csvRecord = countryOptional.get();
            List<String> list = Arrays.asList(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2));
            return String.join(": ", list);
        } else {
            return "NOT FOUND";
        }
    }

    public static List<String> listExportersTwoProducts(CSVParser csvParser, String item1, String item2) throws IOException {
        return csvParser.getRecords().stream()
                .filter(csvRecord -> {
                    List<String> exportList = Arrays.asList(csvRecord.get(1).split(", "));
                    boolean containsItem1 = exportList.contains(item1);
                    boolean containsItem2 = exportList.contains(item2);
                    return containsItem1 && containsItem2;
                })
                .map(csvRecord -> csvRecord.get(0))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        FileResource fileResource = new FileResource("/Users/gyarmatip/Documents/Coding/LearningProjects/Coursera/Java Programming - Solving Problems with Software/duke-java-projects/week3/src/main/resources/dukejava/week3/exportdata.csv");
        CSVParser csvParser = fileResource.getCSVParser();
        //System.out.println(countryInfo(csvParser, "Algeria"));
        List<String> list = listExportersTwoProducts(csvParser, "opium", "cotton");
        System.out.println("list = " + list);
    }

}
