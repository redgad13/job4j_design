package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {
    @Test
    public void whenXMLIsOk() {
        MemStore memStore = new MemStore();
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
        Report engine = new XMLReport(store, parser);
        String expect = "<?xml version=\"1.0\" encoding UTF-8 standalone=\"yes\"?>"
                + "\r\n"
                + "<employees>"
                + "\r\n"
                + "<employee>"
                + "\r\n"
                + "<name>"
                + "Ivan"
                + "</name>"
                + "\r\n"
                + "<hired>"
                + "15:01:2020 01:11"
                + "</hired>"
                + "\r\n"
                + "<fired>"
                + "15:01:2021 01:11"
                + "</fired>"
                + "\r\n"
                + "<salary>"
                + "100.0"
                + "</salary>"
                + "\r\n"
                + "</employee>"
                + "\r\n"
                + "<employee>"
                + "\r\n"
                + "<name>"
                + "Petr"
                + "</name>"
                + "\r\n"
                + "<hired>"
                + "16:02:2021 02:22"
                + "</hired>"
                + "\r\n"
                + "<fired>"
                + "15:01:2022 01:11"
                + "</fired>"
                + "\r\n"
                + "<salary>"
                + "200.0"
                + "</salary>"
                + "\r\n"
                + "</employee>"
                + "\r\n"
                + "<employee>"
                + "\r\n"
                + "<name>"
                + "Sergei"
                + "</name>"
                + "\r\n"
                + "<hired>"
                + "17:03:2022 03:03"
                + "</hired>"
                + "\r\n"
                + "<fired>"
                + "15:01:2023 01:11"
                + "</fired>"
                + "\r\n"
                + "<salary>"
                + "300.0"
                + "</salary>"
                + "\r\n"
                + "</employee>"
                + "\r\n"
                + "</employees>";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}