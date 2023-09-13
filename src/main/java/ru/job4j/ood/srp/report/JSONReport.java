package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.text.SimpleDateFormat;
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(store.findBy(filter));
    }

    public static class EmployeeForListJson {

        private String name;
        private String hired;
        private String fired;
        private double salary;
        private SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm");

        public EmployeeForListJson(Employee employee) {
            this.name = employee.getName();
            this.hired = format.format(employee.getHired().getTime());
            this.fired = format.format(employee.getFired().getTime());
            this.salary = employee.getSalary();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHired() {
            return hired;
        }

        public void setHired(String hired) {
            this.hired = hired;
        }

        public String getFired() {
            return fired;
        }

        public void setFired(String fired) {
            this.fired = fired;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }
}
