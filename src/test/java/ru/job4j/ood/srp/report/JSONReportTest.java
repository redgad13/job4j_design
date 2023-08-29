package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class JSONReportTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2020, 0, 15, 1, 11),
                new GregorianCalendar(2021, 0, 15, 1, 11), 100);
        Employee worker1 = new Employee("Petr", new GregorianCalendar(2021, 1, 16, 2, 22),
                new GregorianCalendar(2022, 0, 15, 1, 11), 200);
        Employee worker2 = new Employee("Sergei", new GregorianCalendar(2022, 2, 17, 3, 3),
                new GregorianCalendar(2023, 0, 15, 1, 11), 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report engine = new JSONReport(store, parser);
        StringBuilder expect = new StringBuilder();
        expect.append("[")
                .append(System.lineSeparator())
                .append("{")
                .append(System.lineSeparator())
                .append("\"name\":\"Ivan\",")
                .append(System.lineSeparator())
                .append("\"hired\": \"15:01:2020 01:11\",")
                .append(System.lineSeparator())
                .append("\"fired\": \"15:01:2021 01:11\",")
                .append(System.lineSeparator())
                .append("\"salary\": \"100.0\"")
                .append(System.lineSeparator())
                .append("},")
                .append(System.lineSeparator())
                .append("{")
                .append(System.lineSeparator())
                .append("\"name\":\"Petr\",")
                .append(System.lineSeparator())
                .append("\"hired\": \"16:02:2021 02:22\",")
                .append(System.lineSeparator())
                .append("\"fired\": \"15:01:2022 01:11\",")
                .append(System.lineSeparator())
                .append("\"salary\": \"200.0\"")
                .append(System.lineSeparator())
                .append("},")
                .append(System.lineSeparator())
                .append("{")
                .append(System.lineSeparator())
                .append("\"name\":\"Sergei\",")
                .append(System.lineSeparator())
                .append("\"hired\": \"17:03:2022 03:03\",")
                .append(System.lineSeparator())
                .append("\"fired\": \"15:01:2023 01:11\",")
                .append(System.lineSeparator())
                .append("\"salary\": \"300.0\"")
                .append(System.lineSeparator())
                .append("}")
                .append(System.lineSeparator())
                .append("]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

}