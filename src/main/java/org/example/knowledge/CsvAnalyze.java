package org.example.knowledge;

import org.example.pojo.Student;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CsvAnalyze {
    public static void main(String[] args) throws IOException {
        HashMap<String, Student> studentList = new HashMap<>();
        InputStream inputStream = CsvAnalyze.class.getResourceAsStream("/data/test.csv");
        ICsvBeanReader beanReader = new CsvBeanReader(new InputStreamReader(inputStream), CsvPreference.STANDARD_PREFERENCE);
        String[] header = beanReader.getHeader(true);
        final CellProcessor[] processors = new CellProcessor[]{
                new Unique(new ParseInt()),
                new Optional(),
                new Optional(new ParseInt())
        };
        Student student;
        while ((student = beanReader.read(Student.class, header, processors)) != null) {
            studentList.put(student.getName(), student);
        }
        for (Map.Entry<String, Student> entry : studentList.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
