package hw4;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeReference <T extends Employee> {
// +  Добавить метод, который ищет сотрудника по стажу (может быть список)
// +  Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
// +  Добавить метод, который ищет сотрудника по табельному номеру
//    Добавить метод добавления нового сотрудника в справочник
    List<T> reference;
    WorkerService service;

    public EmployeeReference(List<T> reference, WorkerService service) {
        this.reference = reference;
        this.service = service;
    }
    public List<T> searchEmpByExperienceYear(int year) {
        return reference.stream().filter(x -> service.getEmpByExperienceYear(x) == year).collect(Collectors.toList());
    }
    public List<T> searchEmpByName(String name) {
        return reference.stream().filter(x -> service.getName(x).equalsIgnoreCase(name)).collect(Collectors.toList());
    }
    public T searchEmpByEmployeeID(int id) {
        return reference.stream().filter(x -> service.getEmployeeID(x) == id).findFirst().get();
    }
    public boolean addNewEmployee(long number, String name, LocalDate dateWhenAccepted) {
        Employee employee = service.addNewEmployee(number, name, dateWhenAccepted);
        return reference.add((T) employee);
    }
    public void showAllReference() {
        reference.stream().forEach(System.out::println);
    }
}
