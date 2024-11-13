package hw4;

import java.time.LocalDate;

public class Employee {
    private static int idCount = 1000;
//    Табельный номер
//    Номер телефона
//    Имя
//    Стаж
    private int employeeID;
    private long number;
    private String name;
    private LocalDate dateWhenAccepted;

    public Employee(long number, String name, LocalDate dateWhenAccepted) {
        this.employeeID = ++idCount;
        this.number = number;
        this.name = name;
        this.dateWhenAccepted = dateWhenAccepted;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public long getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }
    public LocalDate getDateWhenAccepted() {
        return dateWhenAccepted;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Сотрудник {" +
                "имя = " + name +
                ", табельный номер = " + employeeID +
                ", телефонный номер = " + number +
                ", дата приема на работу = " + dateWhenAccepted +
                '}';
    }
}
