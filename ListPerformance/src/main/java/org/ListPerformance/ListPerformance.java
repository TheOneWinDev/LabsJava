package org.ListPerformance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для проведения тестов производительности операций с коллекциями List.
 * Реализует интерфейс {@link ListPerformanceInterface} для тестирования добавления,
 * получения и удаления элементов в различных реализациях List.
 */
public class ListPerformance implements ListPerformanceInterface {
    // Список для хранения результатов тестов
    private final List<ListPerformanceResult> results = new ArrayList<>();

    /**
     * Тестирует операцию добавления элементов в список.
     * Измеряет время, затраченное на добавление {@code iterations} элементов в список.
     *
     * @param list       Список, в который добавляются элементы.
     * @param iterations Количество элементов для добавления.
     */
    @Override
    public void testAdd(List<Integer> list, int iterations) {
        long startTime = System.nanoTime();  // Время начала выполнения
        for (int i = 0; i < iterations; i++) {
            list.add(i);  // Добавление элементов в список
        }
        long endTime = System.nanoTime();  // Время окончания выполнения
        results.add(new ListPerformanceResult("add", endTime - startTime));  // Сохранение результата теста
    }

    /**
     * Тестирует операцию получения элементов из списка.
     * Измеряет время, затраченное на получение {@code iterations} элементов из списка.
     *
     * @param list       Список, из которого получаются элементы.
     * @param iterations Количество элементов для получения.
     */
    @Override
    public void testGet(List<Integer> list, int iterations) {
        long startTime = System.nanoTime();  // Время начала выполнения
        for (int i = 0; i < iterations; i++) {
            list.get(i);  // Получение элементов из списка
        }
        long endTime = System.nanoTime();  // Время окончания выполнения
        results.add(new ListPerformanceResult("get", endTime - startTime));  // Сохранение результата теста
    }

    /**
     * Тестирует операцию удаления элементов из списка.
     * Измеряет время, затраченное на удаление {@code iterations} элементов из начала списка.
     *
     * @param list       Список, из которого удаляются элементы.
     * @param iterations Количество элементов для удаления.
     */
    @Override
    public void testRemove(List<Integer> list, int iterations) {
        long startTime = System.nanoTime();  // Время начала выполнения
        if (iterations > 0) {
            list.subList(0, iterations).clear();  // Удаление элементов из списка
        }
        long endTime = System.nanoTime();  // Время окончания выполнения
        results.add(new ListPerformanceResult("remove", endTime - startTime));  // Сохранение результата теста
    }

    /**
     * Отображает результаты всех тестов в виде таблицы.
     */
    @Override
    public void displayResults() {
        // Формирование строки таблицы с результатами
        String table = results.stream()
                .map(result -> String.format("| %-10s | %15d нс |", result.getOperation(), result.getDuration()))
                .collect(Collectors.joining("\n"));

        // Вывод заголовка таблицы и данных
        System.out.println("+------------+---------------------+");
        System.out.println("| Операция   | Время выполнения    |");
        System.out.println("+------------+---------------------+");
        System.out.println(table);
        System.out.println("+------------+---------------------+");
    }
}