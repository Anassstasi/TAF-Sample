package com.taf.sample.framework.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.taf.sample.framework.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CsvUtil {
    /**
     * Converts a single line of domain to CSV line.
     *
     * @param data
     * @return
     */
    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

    /**
     * Converts a CSV file to Waiter object.
     *
     * @param filePath path to CSV file.
     * @param claz     object class.
     * @return list of objects.
     */
    public static <T> List<T> convertToObject(String filePath, Class claz) throws CsvException {
        try (Reader reader = new FileReader(new File(filePath))) {
            CsvClient<T> csvReader = new CsvClientImpl<>(reader, claz);
            return csvReader.readBeans();
        } catch (IOException e) {
            throw new CsvException("Unable to convert CSV file to object");
        }
    }

    /**
     * Writes domain to a CSV file.
     *
     * @param file file to which domain should be written.
     * @param data domain which should be written to the file.
     * @throws IOException
     */
    public static void writeToFile(File file, List<String[]> data) throws CsvException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(data);
        } catch (IOException e) {
            throw new CsvException("Unable to write domain to a CSV file.");
        }
    }

    /**
     * Reads headers of a CSV file.
     *
     * @param filePath CSV file.
     */
    public static String[] readHeader(String filePath) throws CsvException {
        String[] headers;
        try (CSVReader reader = new CSVReader(new FileReader(new File(filePath)))) {
            headers = reader.readNext();
        } catch (IOException e) {
            throw new CsvException("Unable to read headers from CSV file");
        }
        return headers;
    }

    /**
     * Reads headers of a CSV file.
     *
     * @param filePath CSV file.
     */
    public static List<String[]> readContentLines(String filePath) throws CsvException {
        List<String[]> content = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(new File(filePath)))) {
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                content.add(nextRecord);
            }
        } catch (IOException e) {
            throw new CsvException("Unable to read content from CSV file");
        }
        content.remove(0);
        return content;
    }
}
