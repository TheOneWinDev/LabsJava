package org.theOneWinDev.personList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования сервиса {@code PersonService}.
 * Содержит тесты для проверки корректности загрузки данных из CSV, работы с людьми и подразделениями.
 */
class PersonServiceTest {

    private PersonService personService;

    /**
     * Настройка перед каждым тестом.
     * Создает новый объект {@code PersonService} для тестирования.
     */
    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }

    /**
     * Тестирование загрузки данных из корректного CSV файла.
     * Проверяет, что данные могут быть успешно загружены без ошибок.
     */
    @Test
    void testLoadDataFromCsv_validFile() {
        try {
            personService.loadDataFromCsv("foreign_names.csv");
        } catch (Exception e) {
            fail("Загрузка данных из CSV файла не удалась: " + e.getMessage());
        }
    }

    /**
     * Тестирование загрузки данных из несуществующего файла.
     * Проверяет, что метод выбрасывает исключение {@link FileNotFoundException}.
     */
    @Test
    void testLoadDataFromCsv_invalidFile() {
        assertThrows(FileNotFoundException.class, () -> {
            personService.loadDataFromCsv("invalid_file.csv");
        });
    }

    /**
     * Тестирование получения всех людей после загрузки данных из CSV файла.
     * Проверяет, что список людей не пустой и содержит хотя бы одного человека.
     */
    @Test
    void testGetAllPersons() {
        try {
            personService.loadDataFromCsv("foreign_names.csv");

            List<Person> persons = personService.getAllPersons();
            assertNotNull(persons, "Список людей не должен быть пустым.");
            assertTrue(persons.size() > 0, "Должен быть хотя бы один человек.");
        } catch (Exception e) {
            fail("Загрузка данных из CSV файла не удалась: " + e.getMessage());
        }
    }

    /**
     * Тестирование получения всех подразделений после загрузки данных из CSV файла.
     * Проверяет, что список подразделений не пуст и содержит хотя бы одно подразделение.
     */
    @Test
    void testGetAllDepartments() {
        try {
            personService.loadDataFromCsv("foreign_names.csv");

            List<Department> departments = personService.getAllDepartments();
            assertNotNull(departments, "Список подразделений не должен быть пустым.");
            assertTrue(departments.size() > 0, "Должно быть хотя бы одно подразделение.");
        } catch (Exception e) {
            fail("Загрузка данных из CSV файла не удалась: " + e.getMessage());
        }
    }

    /**
     * Тестирование метода поиска или создания существующего подразделения.
     * Проверяет, что два вызова с одинаковым названием подразделения возвращают один и тот же объект.
     */
    @Test
    void testFindOrCreateDepartment_existingDepartment() {
        Department department1 = personService.findOrCreateDepartment("HR");
        Department department2 = personService.findOrCreateDepartment("HR");

        assertEquals(department1, department2, "Подразделения должны совпадать.");
    }

    /**
     * Тестирование метода создания нового подразделения.
     * Проверяет, что новое подразделение создается с правильным именем.
     */
    @Test
    void testFindOrCreateDepartment_newDepartment() {
        Department newDepartment = personService.findOrCreateDepartment("Finance");

        assertNotNull(newDepartment, "Подразделение должно быть создано.");
        assertEquals("Finance", newDepartment.getName(), "Имя подразделения должно быть Finance.");
    }

    /**
     * Тестирование поиска человека по его ID.
     * Проверяет, что человек с корректным ID может быть найден.
     */
    @Test
    void testGetPersonById_validId() {
        try {
            personService.loadDataFromCsv("foreign_names.csv");

            List<Person> persons = personService.getAllPersons();
            Person firstPerson = persons.get(0);
            Person person = personService.getPersonById(firstPerson.getId());

            assertNotNull(person, "Человек с таким ID должен быть найден.");
            assertEquals(firstPerson.getId(), person.getId(), "ID человека должен совпадать.");
        } catch (Exception e) {
            fail("Загрузка данных из CSV файла не удалась: " + e.getMessage());
        }
    }

    /**
     * Тестирование поиска человека по некорректному ID.
     * Проверяет, что метод возвращает {@code null} для несуществующего человека.
     */
    @Test
    void testGetPersonById_invalidId() {
        Person person = personService.getPersonById(999); // Некорректный ID
        assertNull(person, "Человек с таким ID не должен существовать.");
    }

    /**
     * Тестирование парсинга корректной даты.
     * Проверяет, что строка с датой корректно преобразуется в объект {@code Date}.
     */
    @Test
    void testParseDate_validDate() {
        Date date = personService.parseDate("12.05.1985");
        assertNotNull(date, "Дата должна быть корректно распарсена.");
    }

    /**
     * Тестирование парсинга некорректной даты.
     * Проверяет, что строка с некорректной датой возвращает {@code null}.
     */
    @Test
    void testParseDate_invalidDate() {
        Date date = personService.parseDate("invalid-date");
        assertNull(date, "Дата должна быть некорректной.");
    }
}