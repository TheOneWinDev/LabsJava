package org.theOneWinDev.personList;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Главный класс приложения, демонстрирующий работу с сервисом обработки данных о людях и подразделениях.
 * Содержит основной метод {@code main}, который загружает данные из CSV файла и выводит информацию о людях и подразделениях.
 * Программа выводит ID, имя, пол, подразделение, зарплату и дату рождения каждого человека,
 * а также информацию о всех подразделениях.
 */
public class Main {

    /**
     * Основной метод приложения.
     * Загружает данные о людях и подразделениях из CSV файла и выводит информацию о людях и подразделениях в консоль.
     *
     * @param args Массив строковых параметров командной строки.
     *             Этот параметр не используется в текущей версии программы, но может быть расширен в будущем.
     */
    public static void main(String[] args) {
        // Создание экземпляра сервиса для работы с данными
        PersonService personService = new PersonService();

        try {
            // Загрузка данных из CSV файла
            personService.loadDataFromCsv("foreign_names.csv");

            // Создание форматировщика для даты
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            // Получение списка всех людей
            List<Person> persons = personService.getAllPersons();
            for (Person person : persons) {
                // Форматируем дату рождения перед выводом
                String formattedDate = dateFormat.format(person.getBirthDate());

                // Вывод информации о каждом человеке
                System.out.println("ID: " + person.getId() + ", Имя: " + person.getName() + ", Пол: " + person.getGender() +
                        ", Подразделение: " + person.getDepartment().getName() + ", Зарплата: " + person.getSalary() +
                        ", Дата рождения: " + formattedDate);
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