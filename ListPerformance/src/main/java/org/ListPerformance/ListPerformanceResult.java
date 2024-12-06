package org.ListPerformance;

/**
 * Класс, представляющий результат тестирования производительности операции с коллекцией.
 * Содержит информацию о выполненной операции и времени, затраченном на её выполнение.
 */
public class ListPerformanceResult {

    private final String operation;  // Операция, которую тестировали (например, "add", "get", "remove")
    private final long duration;     // Время выполнения операции в наносекундах

    /**
     * Конструктор для создания результата тестирования.
     *
     * @param operation Операция, которую тестировали (например, "add", "get", "remove").
     * @param duration Время выполнения операции в наносекундах.
     */
    public ListPerformanceResult(String operation, long duration) {
        this.operation = operation;
        this.duration = duration;
    }

    /**
     * Получает название операции.
     *
     * @return Название операции.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Получает продолжительность выполнения операции.
     *
     * @return Время выполнения операции в наносекундах.
     */
    public long getDuration() {
        return duration;
    }
}