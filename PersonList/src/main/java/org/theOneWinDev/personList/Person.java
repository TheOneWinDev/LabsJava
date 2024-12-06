package org.theOneWinDev.personList;

/**
 * Класс, представляющий человека.
 * Содержит информацию о человеке, такую как его идентификатор, имя, пол, подразделение, зарплата и дата рождения.
 */
public class Person {
    private int id;
    private String name;
    private String gender;
    private Department department;
    private double salary;
    private String birthDate;

    /**
     * Конструктор для создания объекта {@code Person}.
     *
     * @param id Идентификатор человека.
     * @param name Имя человека.
     * @param gender Пол человека.
     * @param department Подразделение, в котором работает человек.
     * @param salary Зарплата человека.
     * @param birthDate Дата рождения человека.
     */
    public Person(int id, String name, String gender, Department department, double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Возвращает идентификатор человека.
     *
     * @return Идентификатор человека.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя человека.
     *
     * @return Имя человека.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает пол человека.
     *
     * @return Пол человека.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Возвращает подразделение, в котором работает человек.
     *
     * @return Подразделение человека.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Возвращает зарплату человека.
     *
     * @return Зарплата человека.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Возвращает дату рождения человека.
     *
     * @return Дата рождения человека.
     */
    public String getBirthDate() {
        return birthDate;
    }
}