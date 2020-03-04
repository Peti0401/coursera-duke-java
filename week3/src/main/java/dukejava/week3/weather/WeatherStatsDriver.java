package dukejava.week3.weather;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static dukejava.week3.weather.WeatherStats.*;


public class WeatherStatsDriver {

    private static List<File> files = Arrays.asList(getWeatherReportFile("2013", "12", "25"),
                                                    getWeatherReportFile("2013", "12", "30"),
                                                    getWeatherReportFile("2013", "01", "08"),
                                                    getWeatherReportFile("2013", "01", "23"),
                                                    getWeatherReportFile("2013", "02", "01")
    );

    private static List<File> folderFiles = getWeatherReportFiles("2014");

    public static void main(String[] args) throws IOException {
        // q4
        File fq4 = getWeatherReportFile("2014", "06", "29");
        CSVRecord rq4 = recordWithLowestHumidityInFile(fq4);
        System.out.print("rq4 = "); printCSVRecord(rq4);

        // q5
        File fq5 = getWeatherReportFile("2014", "07", "22");
        CSVRecord rq5 = recordWithLowestHumidityInFile(fq5);
        System.out.print("rq5 = "); printCSVRecord(rq5);

        // q6 - q7
        List<File> lq6 = getWeatherReportFiles("2013");
        File fq6 = fileWithLowestHumidityInFolder(lq6);
        CSVRecord rq6 = recordWithLowestHumidityInFile(fq6);
        System.out.print("rq6 = "); printCSVRecord(rq6);

        // q8
        File fq8 = getWeatherReportFile("2013", "08", "10");
        double avgQ8 = averageTemperatureInFile(fq8);
        System.out.println("avgQ8 = " + avgQ8);

        // q9
        File fq9 = getWeatherReportFile("2013", "09", "02");
        double avgQ9 = averageTemperatureWithHumidityInFile(fq9, 80.0);
        System.out.println("avgQ9 = " + avgQ9);

        // q10 - q11
        List<File> lq10 = getWeatherReportFiles("2013");
        File fq10 = fileWithColdestTemperatureInFolder(lq10);
        CSVRecord rq10 = recordWithColdestTemperatureInFile(fq10);
        System.out.print("rq10 = "); printCSVRecord(rq10);

        File file = fileWithColdestTemperatureInFolder(files);
        CSVRecord rq11 = recordWithColdestTemperatureInFile(file);
        System.out.print("rq11 = "); printCSVRecord(rq11);
    }



    static void printCSVRecord(CSVRecord csvRecord) {
        for (int i = 0; i < csvRecord.size(); i++) {
            System.out.print(csvRecord.get(i) + "   ");
        }
        System.out.println();
    }

}
