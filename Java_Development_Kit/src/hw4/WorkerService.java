package hw4;

import java.time.LocalDate;

public class WorkerService <T extends Employee> {
    public String getName(T worker) {
        return worker.getName();
    }
    public int getEmployeeID(T worker) {
        return worker.getEmployeeID();
    }
    public long getNumber(T worker) {
        return worker.getNumber();
    }
    public LocalDate getDateWhenAccepted(T worker) {
        return worker.getDateWhenAccepted();
    }
    public void setNumber(T worker, long number) {
        worker.setNumber(number);
    }
    public void setName(T worker, String name) {
        worker.setName(name);
    }
    public String toString(T worker) {
        return "Сотрудник {" +
                "имя = " + worker.getName() +
                ", табельный номер = " + worker.getEmployeeID() +
                ", телефонный номер = " + worker.getNumber() +
                ", дата приема на работу = " + worker.getDateWhenAccepted() +
                '}';
    }
    public int getEmpByExperienceYear(T worker) {
        return LocalDate.now().getYear() - worker.getDateWhenAccepted().getYear();
    }
    public Employee addNewEmployee(long number, String name, LocalDate dateWhenAccepted) {
        return new Employee(number, name, dateWhenAccepted);
    }
}
