package org.theOneWinDev.personList;

import java.util.List;

/**
 * Интерфейс для сервиса работы с данными о людях и подразделениях.
 * Определяет основные операции для загрузки данных, поиска и добавления людей и подразделений.
 */
public interface PersonServiceInterface {

    /**
     * Загружает данные о людях из CSV файла.
     *
     * @param csvFilePath Путь к CSV файлу.
     * @throws Exception Если произошла ошибка при загрузке файла или данных.
     */
    void loadDataFromCsv(String csvFilePath) throws Exception;

    /**
     * Возвращает список всех людей.
     *
     * @return Список всех людей.
     */
    List<Person> getAllPersons();

    /**
     * Возвращает список всех подразделений.
     *
     * @return Список всех подразделений.
     */
    List<Department> getAllDepartments();

    /**
     * Находит человека по его ID.
     *
     * @param id Идентификатор человека.
     * @return Объект человека, или {@code null}, если человек с таким ID не найден.
     */
    Person getPersonById(int id);

    /**
     * Находит подразделение по его ID.
     *
     * @param id Идентификатор подразделения.
     * @return Объект подразделения, или {@code null}, если подразделение с таким ID не найдено.
     */
    Department getDepartmentById(int id);

    /**
     * Добавляет нового человека в список.
     *
     * @param person Человек для добавления.
     */
    void addPerson(Person person);

    /**
     * Добавляет новое подразделение в список.
     *
     * @param department Подразделение для добавления.
     */
    void addDepartment(Department department);
}