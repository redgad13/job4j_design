package ru.job4j.ood.ocp.bad;


import java.util.ArrayList;

public class SalaryCount {
    EmployeesList list;

    public int salaryCount(ArrayList<Employee> list) {
        EmployeesList empList = new EmployeesList();
        Person person = new Person();
        list = empList.getList();
        int totalCount = 0;
        for (Employee employee : list) {
            totalCount += employee.getSalary(person);
        }
        return totalCount;
    }
}
