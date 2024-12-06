package org.theOneWinDev.personList;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с данными о людях и подразделениях.
 * Обрабатывает загрузку данных из CSV файла, создание и поиск людей и подразделений.
 */
public class PersonService implements PersonServiceInterface {
    private List<Person> persons = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private int departmentCounter = 1; // Генерация ID для подразделений
    private int personCounter = 1; // Генерация ID для людей

    /**
     * Загружает данные о людях из CSV файла.
     *
     * @param csvFilePath Путь к CSV файлу.
     * @throws Exception Если произошла ошибка при загрузке файла или данных.
     */
    @Override
    public void loadDataFromCsv(String csvFilePath) throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
        if (in == null) {
            throw new FileNotFoundException("CSV файл не найден");
        }

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build()) // Устанавливаем разделитель
                .build()) {

            String[] nextLine;
            boolean firstLine = true; // Флаг для пропуска первой строки (заголовков)

            while ((nextLine = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false; // Пропускаем первую строку (заголовки)
                    continue;
                }

                // Проверяем, что строка содержит достаточно столбцов
                if (nextLine.length < 5) {
                    System.out.println("Некорректная строка в CSV: " + String.join(";", nextLine));
                    continue; // Пропускаем некорректные строки
                }

                // Данные из CSV
                String name = nextLine[0].trim(); // Убираем лишние пробелы
                String gender = nextLine[1].trim();
                String departmentName = nextLine[2].trim();
                double salary;
                try {
                    salary = Double.parseDouble(nextLine[3].trim()); // Убираем лишние пробелы
                } catch (NumberFormatException e) {
                    System.out.println("Некорректная зарплата в строке: " + String.join(";", nextLine));
                    continue;
                }
                String birthDate = nextLine[4].trim();

                // Создаем или находим подразделение
                Department department = findOrCreateDepartment(departmentName);

                // Создаем объект Person и добавляем его в список
                Person person = new Person(personCounter++, name, gender, department, salary, birthDate);
                persons.add(person);
            }
        }
    }

    /**
     * Находит существующее подразделение по названию или создает новое, если такого нет.
     *
     * @param departmentName Название подразделения.
     * @return Объект подразделения.
     */
    Department findOrCreateDepartment(String departmentName) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                return department;
            }
        }
        Department newDepartment = new Department(departmentCounter++, departmentName);
        departments.add(newDepartment);
        return newDepartment;
    }

    /**
     * Возвращает список всех людей.
     *
     * @return Список людей.
     */
    @Override
    public List<Person> getAllPersons() {
        return persons;
    }

    /**
     * Возвращает список всех подразделений.
     *
     * @return Список подразделений.
     */
    @Override
    public List<Department> getAllDepartments() {
        return departments;
    }

    /**
     * Находит человека по его ID.
     *
     * @param id Идентификатор человека.
     * @return Объект человека, или {@code null}, если человек с таким ID не найден.
     */
    @Override
    public Person getPersonById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    /**
     * Находит подразделение по его ID.
     *
     * @param id Идентификатор подразделения.
     * @return Объект подразделения, или {@code null}, если подразделение с таким ID не найдено.
     */
    @Override
    public Department getDepartmentById(int id) {
        for (Department department : departments) {
            if (department.getId() == id) {
                return department;
            }
        }
        return null;
    }

    /**
     * Добавляет нового человека в список.
     *
     * @param person Человек для добавления.
     */
    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Добавляет новое подразделение в список.
     *
     * @param department Подразделение для добавления.
     */
    @Override
    public void addDepartment(Department department) {
        departments.add(department);
    }
}