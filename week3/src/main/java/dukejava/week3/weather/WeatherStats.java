package dukejava.week3.weather;

import edu.duke.FileResource;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WeatherStats {

    static final String FOLDER_PREFIX = "/Users/gyarmatip/Documents/Coding/LearningProjects/Coursera/Java Programming - Solving Problems with Software/duke-java-projects/week3/src/main/resources/dukejava/week3/weather/nc_weather";

    static final String TEMPERATURE_HEADER = "TemperatureF";
    static final String HUMIDITY_HEADER = "Humidity";

    @SneakyThrows
    static CSVRecord recordWithColdestTemperatureInFile(CSVParser csvParser) {
        List<CSVRecord> records = csvParser.getRecords();
        return records.stream()
                .filter(csvRecord -> !csvRecord.get(TEMPERATURE_HEADER).equals("-9999"))
                .min(Comparator.comparingDouble(o -> Double.parseDouble(o.get(TEMPERATURE_HEADER))))
                .get();
    }

    static CSVRecord recordWithColdestTemperatureInFile(File file) {
        return recordWithColdestTemperatureInFile(getWeatherReportCSVParser(file));
    }

    static File fileWithColdestTemperatureInFolder(List<File> files) {
        files.sort((o1, o2) ->
        {
            CSVRecord record1 = recordWithColdestTemperatureInFile(o1);
            Double value1 = Double.parseDouble(record1.get(TEMPERATURE_HEADER));
            CSVRecord record2 = recordWithColdestTemperatureInFile(o2);
            Double value2 = Double.parseDouble(record2.get(TEMPERATURE_HEADER));
            return value1.compareTo(value2);
        });
        return files.get(0);
    }

    static File fileWithColdestTemperatureInFolder(String folder) {
        return fileWithColdestTemperatureInFolder(Arrays.asList(Objects.requireNonNull(new File(folder).listFiles())));
    }

    @SneakyThrows
    static CSVRecord recordWithLowestHumidityInFile(CSVParser csvParser) {
        List<CSVRecord> records = csvParser.getRecords();
        return records.stream()
                .filter(csvRecord -> !csvRecord.get(HUMIDITY_HEADER).equals("N/A"))
                .min(Comparator.comparingDouble(o -> Double.parseDouble(o.get(HUMIDITY_HEADER))))
                .get();
    }

    static CSVRecord recordWithLowestHumidityInFile(File file) {
        return recordWithLowestHumidityInFile(getWeatherReportCSVParser(file));
    }

    static File fileWithLowestHumidityInFolder(List<File> files) {
        files.sort((o1, o2) ->
        {
            CSVRecord record1 = recordWithLowestHumidityInFile(o1);
            Double value1 = Double.parseDouble(record1.get(HUMIDITY_HEADER));
            CSVRecord record2 = recordWithLowestHumidityInFile(o2);
            Double value2 = Double.parseDouble(record2.get(HUMIDITY_HEADER));
            return value1.compareTo(value2);
        });
        return files.get(0);
    }
    static File fileWithLowestHumidityInFolder(String folder) {
        return fileWithLowestHumidityInFolder(Arrays.asList(Objects.requireNonNull(new File(folder).listFiles())));
    }

    static CSVParser getWeatherReportCSVParser(File file) {
        return new FileResource(file).getCSVParser();
    }

    static CSVParser getWeatherReportCSVParser(String year, String month, String day) {
        return new FileResource(getWeatherReportFile(year, month, day)).getCSVParser();
    }

    static File getWeatherReportFile(String year, String month, String day) {
        final String path = String.format("%s/%s/weather-%s-%s-%s.csv", FOLDER_PREFIX, year, year, month, day);
        return new File(path);
    }

    static List<File> getWeatherReportFiles(String year) {
        final String path = String.format("%s/%s", FOLDER_PREFIX, year);
        return Arrays.asList(Objects.requireNonNull(new File(path).listFiles()));
    }

    @SneakyThrows
    static double averageTemperatureInFile(CSVParser csvParser) {
        List<CSVRecord> records = csvParser.getRecords();
        return records.stream()
                .map(csvRecord -> Double.parseDouble(csvRecord.get(TEMPERATURE_HEADER)))
                .reduce(Double::sum)
                .get() / (double) records.size();
    }
    @SneakyThrows
    static double averageTemperatureInFile(File file) {
        return averageTemperatureInFile(new FileResource(file).getCSVParser());
    }

    @SneakyThrows
    static double averageTemperatureWithHumidityInFile(CSVParser csvParser, Double humidity) {
        List<CSVRecord> humidRecords = csvParser.getRecords().stream()
                .filter(csvRecord -> Double.parseDouble(csvRecord.get(HUMIDITY_HEADER)) >= humidity)
                .collect(Collectors.toList());

        return humidRecords.stream()
                .map(csvRecord -> Double.parseDouble(csvRecord.get(TEMPERATURE_HEADER)))
                .reduce(Double::sum)
                .get() / (double) humidRecords.size();
    }

    @SneakyThrows
    static double averageTemperatureWithHumidityInFile(File file, Double humidity) {
        return averageTemperatureWithHumidityInFile(new FileResource(file).getCSVParser(), humidity);
    }

    static double minHumid(List<File> files) {
        return files.stream()
                .map(FileResource::new)
                .map(FileResource::getCSVParser)
                .map(csvParser -> {
                    try {
                        return csvParser.getRecords();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .flatMap(List::stream)
                .map(csvRecord -> csvRecord.get(HUMIDITY_HEADER))
                .filter(s -> !s.equals("N/A"))
                .map(Double::parseDouble)
                .min(Comparator.comparingDouble(o -> o))
                .get();
    }

}
