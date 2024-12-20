package org.ListPerformance;

import java.util.List;

/**
 * Интерфейс для тестирования производительности операций с коллекциями List.
 * Определяет методы для тестирования добавления, получения и удаления элементов,
 * а также для отображения результатов тестов.
 */
public interface ListPerformanceInterface {

    /**
     * Тестирует операцию добавления элементов в список.
     * Измеряет время, затраченное на добавление {@code iterations} элементов в список.
     *
     * @param list       Список, в который добавляются элементы.
     * @param iterations Количество элементов для добавления.
     */
    void testAdd(List<Integer> list, int iterations);

    /**
     * Тестирует операцию получения элементов из списка.
     * Измеряет время, затраченное на получение {@code iterations} элементов из списка.
     *
     * @param list       Список, из которого получаются элементы.
     * @param iterations Количество элементов для получения.
     */
    void testGet(List<Integer> list, int iterations);

    /**
     * Тестирует операцию удаления элементов из списка.
     * Измеряет время, затраченное на удаление {@code iterations} элементов из начала списка.
     *
     * @param list       Список, из которого удаляются элементы.
     * @param iterations Количество элементов для удаления.
     */
    void testRemove(List<Integer> list, int iterations);

    /**
     * Отображает результаты всех тестов в виде таблицы.
     */
    void displayResults();
}