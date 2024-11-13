package hw4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
    Создать справочник сотрудников
    Необходимо:
    Создать класс справочник сотрудников, который содержит внутри
    коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
    Табельный номер
    Номер телефона
    Имя
    Стаж

    Добавить метод, который ищет сотрудника по стажу (может быть список)
    Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
    Добавить метод, который ищет сотрудника по табельному номеру
    Добавить метод добавления нового сотрудника в справочник
    */
    final static int yearExp = 8;
    final static String nameForFind = "Ольга";
    final static int employeeID = 1003;
    private static List<Employee> list = new ArrayList<>();
    static {
        list.add(new Employee(89052600003L, "Сергей", LocalDate.now()));
        list.add(new Employee(89093741001L, "Анна", LocalDate.of(2023,7,20)));
        list.add(new Employee(89942600840L, "Пётр", LocalDate.of(2019,12,1)));
        list.add(new Employee(89114600973L, "Ольга", LocalDate.of(2016, 6,6)));
        list.add(new Employee(89018721986L, "Ольга", LocalDate.of(2017, 3,5)));
        list.add(new Employee(89124923097L, "Станислав", LocalDate.of(2016, 6,7)));
    }
    public static void main(String[] args) {
        EmployeeReference reference = new EmployeeReference<>(list, new WorkerService());

        System.out.printf("Стаж работы в компании %s (в годах):" + System.lineSeparator(), yearExp);
        reference.searchEmpByExperienceYear(yearExp).stream().forEach(System.out::println);

        System.out.println();
        System.out.printf("Список сотрудников с именем \'%s\':" + System.lineSeparator(), nameForFind);
        reference.searchEmpByName(nameForFind).stream().forEach(System.out::println);

        System.out.println();
        System.out.printf("Сотрудников с табельным номеров \'%s\':" + System.lineSeparator(), employeeID);
        System.out.println(reference.searchEmpByEmployeeID(employeeID));

        System.out.println();
        reference.showAllReference();
        reference.addNewEmployee(89894568432L, "Василий", LocalDate.of(2024, 11, 11));
        System.out.println("Добавили нового сотрудника.");
        reference.showAllReference();
    }
}
