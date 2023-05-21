package services;

import java.io. * ;
import java.util.ArrayList;
import java.util.List;

public interface CSVReaderWriterService<T> {

    String objectToCSV(T ob);

    T processCSVLine(String line);

    String getFileName();

    default void write(T ob) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileName(), true))) {
            bufferedWriter.write(objectToCSV(ob));
            bufferedWriter.write("\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void writeAll(List<T> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileName()))) {
            for (T ob: list) {
                bufferedWriter.write(objectToCSV(ob));
                bufferedWriter.write("\n");
            }
        } catch (FileNotFoundException e) {
            //todo
            throw new RuntimeException(e);
        } catch (IOException e) {
            //todo
            throw new RuntimeException(e);
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
        } catch (FileNotFoundException e) {
            //TODO
            throw new RuntimeException(e);
        } catch (IOException e) {
            //TODO
            throw new RuntimeException(e);
        }

    }
}