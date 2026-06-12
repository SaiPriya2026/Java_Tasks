import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        FileData file = new FileData();
        file.setFileName("students.csv");
        file.setFileType("CSV");
        file.setFileSizeInMb(2.5);
        file.setContent("id,name");
        file.setUploadedBy("Admin");

        Predicate<FileData> validate = f ->
                f.getFileSizeInMb() < 5 &&
                        f.getFileType() != null &&
                        f.getContent() != null && !f.getContent().isEmpty() &&
                        f.getUploadedBy() != null;

        Consumer<FileData> validationResult = f -> {
            if (validate.test(f)) {
                System.out.println("Validation: SUCCESS");
            } else {
                System.out.println("Validation: FAILED");
            }
        };

        BiConsumer<String, String> logger =
                (type, msg) -> System.out.println(type + ": " + msg);

        FileProcessor csvProcessor = f ->
                System.out.println("CSV Records Processed Successfully");

        FileProcessor jsonProcessor = f ->
                System.out.println("JSON Processed Successfully");

        FileProcessor xmlProcessor = f ->
                System.out.println("XML Processed Successfully");

        FileProcessor txtProcessor = f ->
                System.out.println("TXT File Processed Successfully");


        Map<String, FileProcessor> processors = new HashMap<>();
        processors.put("CSV", csvProcessor);
        processors.put("JSON", jsonProcessor);
        processors.put("XML", xmlProcessor);
        processors.put("TXT", txtProcessor);


        System.out.println("File Name: " + file.getFileName());

        if (validate.test(file)) {

            validationResult.accept(file);

            FileProcessor processor = processors.get(file.getFileType());

            if (processor != null) {
                logger.accept("Processor Selected", file.getFileType() + " Processor");
                processor.process(file);
            }

        } else {
            System.out.println("Invalid File");
        }
    }
}