package org.theOneWinDev.personList;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     * Данный метод парсит CSV файл, извлекает информацию о людях и их подразделениях, а затем создает
     * соответствующие объекты {@link Person} и {@link Department}.
     *
     * @param csvFilePath Путь к CSV файлу, содержащему данные о людях.
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
                if (nextLine.length < 6) {
                    System.out.println("Некорректная строка в CSV: " + String.join(";", nextLine));
                    continue; // Пропускаем некорректные строки
                }

                // Данные из CSV
                int id = 0;
                try {
                    id = Integer.parseInt(nextLine[0].trim()); // ID человека
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ID в строке: " + String.join(";", nextLine));
                    continue;
                }

                String name = nextLine[1].trim(); // Имя
                String gender = nextLine[2].trim(); // Пол
                String birthDateStr = nextLine[3].trim(); // Дата рождения
                String departmentName = nextLine[4].trim(); // Название подразделения
                double salary = 0;
                try {
                    String salaryStr = nextLine[5].trim(); // Зарплата
                    if (salaryStr.matches("\\d+(\\.\\d+)?")) { // Проверяем, является ли строка числом
                        salary = Double.parseDouble(salaryStr);
                    } else {
                        System.out.println("Некорректная зарплата в строке: " + String.join(";", nextLine));
                        continue; // Пропускаем строку с некорректной зарплатой
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка обработки зарплаты в строке: " + String.join(";", nextLine));
                    continue;
                }

                // Преобразование строки с датой в формат Date
                Date birthDate = parseDate(birthDateStr);
                if (birthDate == null) {
                    System.out.println("Некорректная дата рождения в строке: " + String.join(";", nextLine));
                    continue;
                }

                // Создаем или находим подразделение
                Department department = findOrCreateDepartment(departmentName);

                // Создаем объект Person и добавляем его в список
                Person person = new Person(id, name, gender, department, salary, birthDate);
                persons.add(person);
            }
        }
    }

    /**
     * Парсит строку с датой в формат Date.
     *
     * Преобразует строку с датой в формате "dd.MM.yyyy" в объект {@link Date}.
     *
     * @param dateStr Строка с датой.
     * @return Объект Date, если дата корректная, или {@code null}, если дата некорректная.
     */
    Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null; // Если ошибка парсинга, возвращаем null
        }
    }

    /**
     * Находит существующее подразделение по названию или создает новое, если такого нет.
     *
     * Данный метод проверяет, существует ли уже подразделение с заданным названием. Если нет, создает
     * новое подразделение и добавляет его в список.
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
     * Этот метод возвращает список всех загруженных людей.
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
     * Этот метод возвращает список всех подразделений, которые были созданы.
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
     * Этот метод позволяет найти человека по его уникальному идентификатору.
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
     * Этот метод позволяет найти подразделение по его уникальному идентификатору.
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
}