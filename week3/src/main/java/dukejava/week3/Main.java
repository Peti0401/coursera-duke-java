package dukejava.week3;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

    public static void main(String[] args) {
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        for (CSVRecord csvRecord : csvParser) {
            System.out.println(csvRecord.get(1));
        }
    }

}
