package org.ListPerformance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульные тесты для класса ListPerformance, проверяющие производительность
 * и корректность операций для различных реализаций List (ArrayList и LinkedList).
 */
public class ListPerformanceTest {
    private ListPerformance tester;

    /**
     * Инициализация объекта ListPerformance перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        tester = new ListPerformance();
    }

    /**
     * Тестирует метод {@code testAdd} для ArrayList с фиксированным количеством итераций.
     */
    @Test
    void testAddArrayList() {
        List<Integer> arrayList = new ArrayList<>();
        int iterations = 1000;

        // Проверяем, что метод testAdd не вызывает исключений
        assertDoesNotThrow(() -> tester.testAdd(arrayList, iterations),
                "Метод testAdd завершился с исключением для ArrayList");
        // Проверяем, что размер списка совпадает с количеством итераций
        assertEquals(iterations, arrayList.size(),
                "Размер ArrayList должен совпадать с количеством итераций после добавления");
    }

    /**
     * Тестирует метод {@code testAdd} для LinkedList с фиксированным количеством итераций.
     */
    @Test
    void testAddLinkedList() {
        List<Integer> linkedList = new LinkedList<>();
        int iterations = 1000;

        // Проверяем, что метод testAdd не вызывает исключений
        assertDoesNotThrow(() -> tester.testAdd(linkedList, iterations),
                "Метод testAdd завершился с исключением для LinkedList");
        // Проверяем, что размер списка совпадает с количеством итераций
        assertEquals(iterations, linkedList.size(),
                "Размер LinkedList должен совпадать с количеством итераций после добавления");
    }

    /**
     * Тестирует метод {@code testGet} для ArrayList с предварительно добавленными данными.
     */
    @Test
    void testGetArrayList() {
        List<Integer> arrayList = new ArrayList<>();
        int iterations = 1000;
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }

        // Проверяем, что метод testGet не вызывает исключений
        assertDoesNotThrow(() -> tester.testGet(arrayList, iterations),
                "Метод testGet завершился с исключением для ArrayList");
    }

    /**
     * Тестирует метод {@code testGet} для LinkedList с предварительно добавленными данными.
     */
    @Test
    void testGetLinkedList() {
        List<Integer> linkedList = new LinkedList<>();
        int iterations = 1000;
        for (int i = 0; i < iterations; i++) {
            linkedList.add(i);
        }

        // Проверяем, что метод testGet не вызывает исключений
        assertDoesNotThrow(() -> tester.testGet(linkedList, iterations),
                "Метод testGet завершился с исключением для LinkedList");
    }

    /**
     * Тестирует метод {@code testRemove} для ArrayList после добавления элементов.
     */
    @Test
    void testRemoveArrayList() {
        List<Integer> arrayList = new ArrayList<>();
        int iterations = 1000;
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }

        // Проверяем, что метод testRemove не вызывает исключений
        assertDoesNotThrow(() -> tester.testRemove(arrayList, iterations),
                "Метод testRemove завершился с исключением для ArrayList");
        // Проверяем, что ArrayList пуст после удаления всех элементов
        assertTrue(arrayList.isEmpty(),
                "ArrayList должен быть пустым после удаления всех элементов");
    }

    /**
     * Тестирует метод {@code testRemove} для LinkedList после добавления элементов.
     */
    @Test
    void testRemoveLinkedList() {
        List<Integer> linkedList = new LinkedList<>();
        int iterations = 1000;
        for (int i = 0; i < iterations; i++) {
            linkedList.add(i);
        }

        // Проверяем, что метод testRemove не вызывает исключений
        assertDoesNotThrow(() -> tester.testRemove(linkedList, iterations),
                "Метод testRemove завершился с исключением для LinkedList");
        // Проверяем, что LinkedList пуст после удаления всех элементов
        assertTrue(linkedList.isEmpty(),
                "LinkedList должен быть пустым после удаления всех элементов");
    }

    /**
     * Тестирует работу всех методов с нулевым количеством итераций, чтобы убедиться в их корректности для пустых операций.
     */
    @Test
    void testZeroIterations() {
        List<Integer> arrayList = new ArrayList<>();
        // Проверяем работу метода testAdd с нулевым количеством элементов
        assertDoesNotThrow(() -> tester.testAdd(arrayList, 0),
                "Ошибка при добавлении нулевого количества элементов");
        // Проверяем работу метода testGet с пустым списком
        assertDoesNotThrow(() -> tester.testGet(arrayList, 0),
                "Ошибка при получении из пустого списка");
        // Проверяем работу метода testRemove с пустым списком
        assertDoesNotThrow(() -> tester.testRemove(arrayList, 0),
                "Ошибка при удалении из пустого списка");
        // Проверяем, что список остаётся пустым при нулевых итерациях
        assertTrue(arrayList.isEmpty(),
                "ArrayList должен оставаться пустым при нулевых итерациях");
    }

    /**
     * Тестирует метод {@code testAdd} с большим количеством итераций, чтобы проверить масштабируемость.
     */
    @Test
    void testLargeIterations() {
        List<Integer> arrayList = new ArrayList<>();
        int iterations = 1_000_000;

        // Проверяем метод testAdd с большим количеством элементов
        assertDoesNotThrow(() -> tester.testAdd(arrayList, iterations),
                "Метод testAdd завершился с исключением для большого числа итераций");
        // Проверяем, что размер списка совпадает с количеством итераций
        assertEquals(iterations, arrayList.size(),
                "Размер ArrayList должен совпадать с количеством итераций");
    }
}