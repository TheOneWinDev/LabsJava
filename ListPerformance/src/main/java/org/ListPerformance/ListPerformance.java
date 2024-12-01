package org.ListPerformance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для тестирования производительности коллекций ArrayList и LinkedList.
 * Сравниваются методы add, remove и get.
 */
public class ListPerformance {

    /**
     * Тестирует производительность коллекций ArrayList и LinkedList для заданного количества операций.
     *
     * @param iterations количество итераций для выполнения операций
     */
    public static void testPerformance(int iterations) {
        // Сравнение ArrayList
        List<Integer> arrayList = new ArrayList<>();
        System.out.println("ArrayList:");
        testListOperations(arrayList, iterations);

        // Сравнение LinkedList
        List<Integer> linkedList = new LinkedList<>();
        System.out.println("LinkedList:");
        testListOperations(linkedList, iterations);
    }

    /**
     * Выполняет операции с коллекцией и измеряет время их выполнения.
     *
     * @param list коллекция для тестирования
     * @param iterations количество итераций
     */
    private static void testListOperations(List<Integer> list, int iterations) {
        long startTime, endTime;

        // Тестирование метода add
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }
        endTime = System.nanoTime();
        System.out.printf("add: %d раз, время: %d нс\n", iterations, (endTime - startTime));

        // Тестирование метода get
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        System.out.printf("get: %d раз, время: %d нс\n", iterations, (endTime - startTime));

        // Тестирование метода remove
        startTime = System.nanoTime();
        // Удаление первого элемента
        if (iterations > 0) {
            list.subList(0, iterations).clear();
        }
        endTime = System.nanoTime();
        System.out.printf("remove: %d раз, время: %d нс\n", iterations, (endTime - startTime));
    }

    public static void main(String[] args) {
        // Количество операций для тестирования
        int iterations = 1000;
        testPerformance(iterations);
    }
}