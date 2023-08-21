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
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2020, 0, 15, 1, 11),
                new GregorianCalendar(2021, 0, 15, 1, 11), 100);
        Employee worker1 = new Employee("Petr", new GregorianCalendar(2021, 1, 16, 2, 22),
                new GregorianCalendar(2022, 0, 15, 1, 11), 200);
        Employee worker2 = new Employee("Sergei", new GregorianCalendar(2022, 2, 17, 3, 3),
                new GregorianCalendar(2023, 0, 15, 1, 11), 300);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Gson gson = new GsonBuilder().create();
        store.add(worker);
        Report engine = new JSONReport(store, parser, gson);
        String expect = "Name; Hired; Fired; Salary;"
                + System.lineSeparator()
                + "[{\"name\":\"Ivan\",\"hired\":{\"year\":2020,\"month\":0,\"dayOfMonth\":15,\"hourOfDay\":1,\"minute\":11,\"second\":0},\"fired\":{\"year\":2021,\"month\":0,\"dayOfMonth\":15,\"hourOfDay\":1,\"minute\":11,\"second\":0},\"salary\":100.0}]";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

}