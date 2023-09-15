package ru.job4j.ood.srp.report;

import ru.job4j.ood.ocp.bad.EmployeesList;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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
            JAXBContext context = JAXBContext.newInstance(EmployeeForXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                Employees employees = new Employees();
                for (EmployeeForXML employee : employees.getEmployees()) {
                    marshaller.marshal(employee, writer);
                    xml = writer.getBuffer().toString();
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    public static class EmployeeForXML {

        private String name;
        private String hired;
        private String fired;
        private double salary;

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

    public static class Employees {
        private List<EmployeeForXML> employees;

        public Employees() {
        }

        public Employees(List<EmployeeForXML> employees) {
            this.employees = employees;
        }

        public List<EmployeeForXML> getEmployees() {
            return employees;
        }

        public void setEmployees(List<EmployeeForXML> employees) {
            this.employees = employees;
        }
    }
}