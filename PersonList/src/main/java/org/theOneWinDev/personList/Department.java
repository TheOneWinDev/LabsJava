package org.theOneWinDev.personList;

/**
 * Класс, представляющий подразделение.
 * Содержит информацию о подразделении, такую как его идентификатор и название.
 */
public class Department {
    private int id;
    private String name;

    /**
     * Конструктор для создания объекта {@code Department}.
     *
     * @param id Идентификатор подразделения.
     * @param name Название подразделения.
     */
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает идентификатор подразделения.
     *
     * @return Идентификатор подразделения.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название подразделения.
     *
     * @return Название подразделения.
     */
    public String getName() {
        return name;
    }
}