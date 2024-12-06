package org.theOneWinDev.personList;

import java.util.List;

/**
 * Главный класс приложения, который демонстрирует работу с сервисом обработки данных о людях и подразделениях.
 * Содержит метод {@code main}, загружающий данные из CSV файла, выводящий информацию о людях и подразделениях.
 */
public class Main {

    /**
     * Основной метод приложения.
     * Загружает данные о людях и подразделениях из CSV файла, выводит информацию о людях и подразделениях.
     *
     * @param args Массив строковых параметров командной строки.
     */
    public static void main(String[] args) {
        // Создание экземпляра сервиса для работы с данными
        PersonService personService = new PersonService();

        try {
            // Загрузка данных из CSV файла
            personService.loadDataFromCsv("people.csv");

            // Получение списка всех людей
            List<Person> persons = personService.getAllPersons();
            for (Person person : persons) {
                // Вывод информации о каждом человеке
                System.out.println("ID: " + person.getId() + ", Имя: " + person.getName() + ", Пол: " + person.getGender() +
                        ", Подразделение: " + person.getDepartment().getName() + ", Зарплата: " + person.getSalary() +
                        ", Дата рождения: " + person.getBirthDate());
            }

            // Получение списка всех подразделений
            List<Department> departments = personService.getAllDepartments();
            for (Department department : departments) {
                // Вывод информации о каждом подразделении
                System.out.println("Подразделение ID: " + department.getId() + ", Название: " + department.getName());
            }

        } catch (Exception e) {
            // Обработка исключений
            e.printStackTrace();
        }
    }
}