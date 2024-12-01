package org.ListPerformance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListPerformanceTest {

    /**
     * Тест производительности для ArrayList с 1000 операциями.
     */
    @Test
    public void testArrayListPerformance() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(1000); // Тестируем на 1000 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела (например, 1 секунда)
        Assertions.assertTrue(duration < 1000000000, "Тест производительности слишком долгий для ArrayList");
    }

    /**
     * Тест производительности для LinkedList с 1000 операциями.
     */
    @Test
    public void testLinkedListPerformance() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(1000); // Тестируем на 1000 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела (например, 1 секунда)
        Assertions.assertTrue(duration < 1000000000, "Тест производительности слишком долгий для LinkedList");
    }

    /**
     * Тест производительности для ArrayList с 100 операциями.
     */
    @Test
    public void testArrayListPerformanceSmall() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(100); // Тестируем на 100 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела (например, 1 секунда)
        Assertions.assertTrue(duration < 1000000000, "Тест производительности слишком долгий для ArrayList");
    }

    /**
     * Тест производительности для LinkedList с 100 операциями.
     */
    @Test
    public void testLinkedListPerformanceSmall() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(100); // Тестируем на 100 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела (например, 1 секунда)
        Assertions.assertTrue(duration < 1000000000, "Тест производительности слишком долгий для LinkedList");
    }

    /**
     * Тест производительности для ArrayList с 5000 операциями.
     */
    @Test
    public void testArrayListPerformanceMedium() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(5000); // Тестируем на 5000 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела
        Assertions.assertTrue(duration < 5000000000L, "Тест производительности слишком долгий для ArrayList");
    }

    /**
     * Тест производительности для LinkedList с 5000 операциями.
     */
    @Test
    public void testLinkedListPerformanceMedium() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(5000); // Тестируем на 5000 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела
        Assertions.assertTrue(duration < 5000000000L, "Тест производительности слишком долгий для LinkedList");
    }

    /**
     * Тест производительности для ArrayList с 10000 операциями.
     */
    @Test
    public void testArrayListPerformanceLarge() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(10000); // Тестируем на 10000 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела
        Assertions.assertTrue(duration < 10000000000L, "Тест производительности слишком долгий для ArrayList");
    }

    /**
     * Тест производительности для LinkedList с 10000 операциями.
     */
    @Test
    public void testLinkedListPerformanceLarge() {
        long startTime = System.nanoTime();
        ListPerformance.testPerformance(10000); // Тестируем на 10000 операций
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Проверка, что время выполнения теста не превышает разумного предела
        Assertions.assertTrue(duration < 10000000000L, "Тест производительности слишком долгий для LinkedList");
    }
}