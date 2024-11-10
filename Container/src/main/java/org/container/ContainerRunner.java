package org.container;

/**
 * Класс {@code ContainerRunner} выполняет демонстрацию работы с контейнером {@code Container}.
 * Он создает контейнер, добавляет в него элементы, выводит их, удаляет элемент и снова выводит оставшиеся элементы.
 */
public class ContainerRunner {
    /**
     * Главный метод программы, выполняющий демонстрацию работы контейнера.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        // Создание контейнера для хранения строк
        Container<String> container = new Container<>();

        // Добавление элементов в контейнер
        container.add("Первый элемент");
        container.add("Второй элемент");
        container.add("Третий элемент");

        // Вывод количества элементов в контейнере
        System.out.println("Количество элементов в контейнере: " + container.size());

        // Вывод всех элементов контейнера по индексу
        for (int i = 0; i < container.size(); i++) {
            System.out.println("Элемент на позиции " + i + ": " + container.get(i));
        }

        // Удаление элемента на позиции 1
        container.remove(1);
        System.out.println("\nПосле удаления элемента на позиции 1:");

        // Вывод нового количества элементов в контейнере
        System.out.println("Количество элементов в контейнере: " + container.size());
        // Вывод оставшихся элементов
        for (int i = 0; i < container.size(); i++) {
            System.out.println("Элемент на позиции " + i + ": " + container.get(i));
        }
    }
}
