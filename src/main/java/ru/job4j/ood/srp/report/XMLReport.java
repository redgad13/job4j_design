package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm");
            try (StringWriter writer = new StringWriter()) {
                Employees employees = new Employees(store.findBy(filter), format);
                    marshaller.marshal(employees, writer);
                    xml = writer.getBuffer().toString();
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlType(propOrder = {"name", "hired", "fired", "salary"})
    public static class EmployeeForXML {
        private String name;
        private String hired;
        private String fired;
        private double salary;

        public EmployeeForXML() {
        }

        public EmployeeForXML(Employee employee, SimpleDateFormat format) {
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

        @XmlRootElement(name = "employees")
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Employees {

        @XmlElement(name = "employee")
        private List<EmployeeForXML> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees, SimpleDateFormat format) {
            this.employees = empToEmpXML(employees, format);
        }

        public List<EmployeeForXML> getEmployees() {
            return employees;
        }

        public void setEmployees(List<EmployeeForXML> employees) {
            this.employees = employees;
        }

        private List<EmployeeForXML> empToEmpXML(List<Employee> employees, SimpleDateFormat format) {
            List<EmployeeForXML> rsl = new ArrayList<>();
            for (Employee employee : employees) {
                rsl.add(new EmployeeForXML(employee, format));
            }
            return rsl;
        }
    }
}