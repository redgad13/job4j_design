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
        String expect = "[\n" +
                "  {\n" +
                "    \"name\": \"Ivan\",\n" +
                "    \"hired\": {\n" +
                "      \"year\": 2020,\n" +
                "      \"month\": 0,\n" +
                "      \"dayOfMonth\": 15,\n" +
                "      \"hourOfDay\": 1,\n" +
                "      \"minute\": 11,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"fired\": {\n" +
                "      \"year\": 2021,\n" +
                "      \"month\": 0,\n" +
                "      \"dayOfMonth\": 15,\n" +
                "      \"hourOfDay\": 1,\n" +
                "      \"minute\": 11,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"salary\": 100.0\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Petr\",\n" +
                "    \"hired\": {\n" +
                "      \"year\": 2021,\n" +
                "      \"month\": 1,\n" +
                "      \"dayOfMonth\": 16,\n" +
                "      \"hourOfDay\": 2,\n" +
                "      \"minute\": 22,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"fired\": {\n" +
                "      \"year\": 2022,\n" +
                "      \"month\": 0,\n" +
                "      \"dayOfMonth\": 15,\n" +
                "      \"hourOfDay\": 1,\n" +
                "      \"minute\": 11,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"salary\": 200.0\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Sergei\",\n" +
                "    \"hired\": {\n" +
                "      \"year\": 2022,\n" +
                "      \"month\": 2,\n" +
                "      \"dayOfMonth\": 17,\n" +
                "      \"hourOfDay\": 3,\n" +
                "      \"minute\": 3,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"fired\": {\n" +
                "      \"year\": 2023,\n" +
                "      \"month\": 0,\n" +
                "      \"dayOfMonth\": 15,\n" +
                "      \"hourOfDay\": 1,\n" +
                "      \"minute\": 11,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"salary\": 300.0\n" +
                "  }\n" +
                "]";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

}