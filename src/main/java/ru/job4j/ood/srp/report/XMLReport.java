package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XMLReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding UTF-8 standalone=\"yes\"?>")
                .append(System.lineSeparator())
                .append("<employees>");
        for (Employee employee : store.findBy(filter)) {
            builder.append(System.lineSeparator())
                    .append("<employee>")
                    .append(System.lineSeparator())
                    .append("<name>")
                    .append(employee.getName())
                    .append("</name>")
                    .append(System.lineSeparator())
                    .append("<hired>")
                    .append(dateTimeParser.parse(employee.getHired()))
                    .append("</hired>")
                    .append(System.lineSeparator())
                    .append("<fired>")
                    .append(dateTimeParser.parse(employee.getFired()))
                    .append("</fired>")
                    .append(System.lineSeparator())
                    .append("<salary>")
                    .append(employee.getSalary())
                    .append("</salary>")
                    .append(System.lineSeparator())
                    .append("</employee>");
        }
        builder.append(System.lineSeparator())
                .append("</employees>");
        return builder.toString();
    }
}
