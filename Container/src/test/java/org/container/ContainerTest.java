package org.container;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс {@code ContainerTest} содержит тесты для проверки корректности работы контейнера {@code Container}.
 * Тесты используют библиотеку JUnit для проверки функциональности добавления, получения, удаления элементов
 * и обработки исключений в контейнере.
 */
public class ContainerTest {

    /**
     * Тестирует метод {@code add} и проверяет правильность возвращаемого размера контейнера.
     */
    @Test
    public void testAddAndSize() {
        Container<String> container = new Container<>();

        // Проверка начального размера контейнера (должен быть 0)
        assertEquals(0, container.size());

        // Добавление элементов и проверка размера
        container.add("Hello");
        assertEquals(1, container.size());

        container.add("World");
        assertEquals(2, container.size());
    }

    /**
     * Тестирует метод {@code get}, проверяя правильность извлечения элементов по индексу.
     */
    @Test
    public void testGet() {
        Container<Integer> container = new Container<>();
        container.add(10);
        container.add(20);
        container.add(30);

        // Проверка получения элементов по индексу
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
    }

    /**
     * Тестирует метод {@code remove}, проверяя правильность удаления элементов и обновления размера.
     */
    @Test
    public void testRemove() {
        Container<String> container = new Container<>();
        container.add("A");
        container.add("B");
        container.add("C");

        // Удаление элемента на позиции 1 и проверка размера и содержимого
        container.remove(1);
        assertEquals(2, container.size());
        assertEquals("A", container.get(0));
        assertEquals("C", container.get(1));

        // Удаление элемента на позиции 0 и проверка размера и содержимого
        container.remove(0);
        assertEquals(1, container.size());
        assertEquals("C", container.get(0));
    }

    /**
     * Тестирует обработку исключений {@code IndexOutOfBoundsException},
     * когда попытка доступа к элементу или удаления элемента с неверным индексом.
     */
    @Test
    public void testIndexOutOfBoundsException() {
        Container<String> container = new Container<>();
        container.add("Test");

        // Проверка исключения при попытке получить элемент по несуществующему индексу
        assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(1);
        });

        // Проверка исключения при попытке удалить элемент по несуществующему индексу
        assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(1);
        });
    }
}
