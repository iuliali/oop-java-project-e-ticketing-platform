package services;

import exceptions.CSVReadWriteException;

import java.io. * ;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.CSV_EXCEPTION_READ;
import static constants.Constants.CSV_EXCEPTION_WRITE;

public interface CSVReaderWriterService<T> {

    String objectToCSV(T ob);

    T processCSVLine(String line);

    String getFileName();

    default void write(T ob) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileName(), true))) {
            bufferedWriter.write(objectToCSV(ob));
            bufferedWriter.write("\n");

        } catch (IOException exception) {
            throw new CSVReadWriteException(CSV_EXCEPTION_WRITE.formatted(getFileName(), exception.getMessage()));
        }
    }

    default void writeAll(List<T> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileName()))) {
            for (T ob: list) {
                bufferedWriter.write(objectToCSV(ob));
                bufferedWriter.write("\n");
            }
        } catch (IOException exception) {
            throw new CSVReadWriteException(CSV_EXCEPTION_WRITE.formatted(getFileName(), exception.getMessage()));
        }
    }

    default List<T> read() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFileName()))) {
            List<T> resultLines = new ArrayList<>();
            String currentLine = bufferedReader.readLine();
            while (currentLine != null && !currentLine.isEmpty()) {
                resultLines.add(processCSVLine(currentLine));
                currentLine = bufferedReader.readLine();
            }
            return resultLines;
        } catch (IOException exception) {
            throw new CSVReadWriteException(CSV_EXCEPTION_READ.formatted(getFileName(), exception.getMessage()));
        }

    }
}