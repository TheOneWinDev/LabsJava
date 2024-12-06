package org.theOneWinDev.personList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирования для {@link PersonService}.
 * Проверяет функциональность методов сервиса работы с людьми и подразделениями.
 */
class PersonServiceTest {

    private PersonService personService;

    /**
     * Инициализация тестируемого объекта {@link PersonService} перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }

    /**
     * Тестирует метод {@link PersonService#getPersonById(int)} для поиска человека по ID.
     * Проверяется, что человек может быть найден по ID.
     */
    @Test
    void testGetPersonById() {
        Department department = new Department(1, "IT");
        Person person = new Person(1, "John Doe", "Male", department, 5000, "01-01-1985");
        personService.addPerson(person);

        // Проверяем поиск по ID
        Person foundPerson = personService.getPersonById(1);
        assertNotNull(foundPerson, "Человек должен быть найден по ID");
        assertEquals("John Doe", foundPerson.getName(), "Имя человека должно быть 'John Doe'");
    }

    /**
     * Тестирует метод {@link PersonService#getPersonById(int)} для случая, когда человек не найден по ID.
     * Проверяется, что метод возвращает {@code null}, если человек с указанным ID не существует.
     */
    @Test
    void testGetPersonById_notFound() {
        Department department = new Department(1, "IT");

        // Попытка найти человека, которого нет в списке
        Person foundPerson = personService.getPersonById(99);
        assertNull(foundPerson, "Человек с таким ID не должен быть найден");
    }

    /**
     * Тестирует поиск человека по имени.
     * Проверяется, что можно найти человека по имени среди всех добавленных.
     */
    @Test
    void testGetPersonByName() {
        Department department = new Department(1, "HR");
        Person person = new Person(1, "Alice", "Female", department, 4500, "05-05-1992");
        personService.addPerson(person);

        // Получаем всех людей
        List<Person> persons = personService.getAllPersons();
        Person foundPerson = null;
        for (Person p : persons) {
            if (p.getName().equals("Alice")) {
                foundPerson = p;
                break;
            }
        }

        assertNotNull(foundPerson, "Человек с именем 'Alice' должен быть найден");
        assertEquals("Alice", foundPerson.getName(), "Имя найденного человека должно быть 'Alice'");
    }

    /**
     * Тестирует загрузку данных из пустого CSV файла.
     * Проверяется, что список людей остается пустым, если файл пустой.
     */
    @Test
    void testLoadDataFromCsv_emptyFile() throws Exception {
        String testFilePath = "empty.csv";

        // Загружаем данные из пустого файла
        personService.loadDataFromCsv(testFilePath);

        // Проверяем, что список людей пуст
        List<Person> persons = personService.getAllPersons();
        assertTrue(persons.isEmpty(), "Список людей должен быть пустым");
    }

    /**
     * Тестирует метод {@link PersonService#addPerson(Person)} для добавления человека с корректными данными.
     * Проверяется, что человек был успешно добавлен в список.
     */
    @Test
    void testAddPerson_validData() {
        Department department = new Department(1, "IT");

        // Добавляем человека с корректными данными
        Person person = new Person(3, "Alice", "Female", department, 5000, "12-12-1995");
        personService.addPerson(person);

        // Проверяем, что человек был добавлен
        List<Person> persons = personService.getAllPersons();
        assertEquals(1, persons.size(), "Человек должен быть добавлен");
        assertEquals("Alice", persons.get(0).getName(), "Имя добавленного человека должно быть 'Alice'");
    }
}