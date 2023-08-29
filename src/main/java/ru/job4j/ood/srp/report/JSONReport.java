package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public JSONReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("[");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append("{")
                    .append(System.lineSeparator())
                    .append("\"name\":\"")
                    .append(employee.getName()).append("\",")
                    .append(System.lineSeparator())
                    .append("\"hired\": \"")
                    .append(dateTimeParser.parse(employee.getHired())).append("\",")
                    .append(System.lineSeparator())
                    .append("\"fired\": \"")
                    .append(dateTimeParser.parse(employee.getFired())).append("\",")
                    .append(System.lineSeparator())
                    .append("\"salary\": \"")
                    .append(employee.getSalary()).append("\"")
                    .append(System.lineSeparator())
                    .append("},");
        }
        text.deleteCharAt(text.length() - 1);
        text.append(System.lineSeparator())
                .append("]");
        return text.toString();
    }
}
