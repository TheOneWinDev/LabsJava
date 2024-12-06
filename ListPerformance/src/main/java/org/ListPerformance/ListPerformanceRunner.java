package org.ListPerformance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс для тестирования производительности различных коллекций List (ArrayList и LinkedList).
 * Позволяет пользователю выбрать количество итераций и выполняет тесты для добавления, получения и удаления элементов.
 */
public class ListPerformanceRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Просим пользователя ввести количество итераций
        System.out.print("Введите количество итераций для тестирования: ");
        int iterations;

        // Проверка корректности ввода
        while (true) {
            if (scanner.hasNextInt()) {
                iterations = scanner.nextInt();
                if (iterations > 0) {
                    break;
                } else {
                    System.out.print("Пожалуйста, введите положительное число: ");
                }
            } else {
                System.out.print("Некорректный ввод! Попробуйте ещё раз: ");
                scanner.next();
            }
        }

        System.out.println("\nСравнение производительности коллекций");
        System.out.println("=======================================");

        // Тестирование ArrayList
        System.out.println("\nРезультаты для ArrayList:");
        testListPerformance(new ArrayList<>(), iterations);

        // Тестирование LinkedList
        System.out.println("\nРезультаты для LinkedList:");
        testListPerformance(new LinkedList<>(), iterations);

        // Закрытие ресурса scanner
        scanner.close();
    }

    /**
     * Метод для выполнения тестирования производительности коллекции.
     * Тестирует операции добавления, получения и удаления элементов в коллекции.
     *
     * @param list Коллекция, которую нужно протестировать (ArrayList или LinkedList).
     * @param iterations Количество итераций для выполнения операций.
     */
    private static void testListPerformance(List<Integer> list, int iterations) {
        // Создание экземпляра для тестирования
        ListPerformance tester = new ListPerformance();

        // Выполнение тестов
        tester.testAdd(list, iterations);
        tester.testGet(list, iterations);
        tester.testRemove(list, iterations);

        // Вывод результатов
        tester.displayResults();
    }
}