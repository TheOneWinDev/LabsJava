package org.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс, отвечающий за вычисление значений выражений, представленных абстрактным синтаксическим деревом (AST).
 * Он принимает на вход карту переменных и их значений и использует её для вычисления результатов выражений.
 */
public class ExpressionEvaluator {

    private Map<String, Double> variables;

    /**
     * Конструктор для создания объекта {@link ExpressionEvaluator}.
     *
     * @param variables Карта переменных и их значений, которая используется для вычисления выражений.
     */
    public ExpressionEvaluator(Map<String, Double> variables) {
        this.variables = variables;
    }

    /**
     * Метод для вычисления значения выражения, представленного узлом абстрактного синтаксического дерева.
     *
     * @param node Узел абстрактного синтаксического дерева (AST), представляющий выражение.
     * @return Результат вычисления выражения.
     */
    public double evaluate(ExpressionNode node) {
        return node.evaluate(variables);
    }

    /**
     * Главный метод программы. Считывает выражение и переменные с консоли,
     * парсит выражение, вычисляет результат и выводит его на экран.
     *
     * Процесс:
     * 1. Вводится выражение.
     * 2. Вводятся переменные в формате "имя = значение".
     * 3. Вычисляется результат выражения с учётом переменных.
     * 4. Результат выводится на экран.
     *
     * @param args Аргументы командной строки (не используются в данном приложении).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();

        // Парсинг выражения в AST
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        // Считывание переменных
        Map<String, Double> variables = new HashMap<>();
        while (true) {
            System.out.println("Введите переменную (или 'exit' для завершения):");
            String line = scanner.nextLine().trim();
            if (line.equals("exit")) {
                break;
            }
            try {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String variableName = parts[0].trim();
                    double value = Double.parseDouble(parts[1].trim());
                    variables.put(variableName, value);
                } else {
                    System.out.println("Неверный формат. Введите переменную в формате 'x = 3'.");
                }
            } catch (Exception e) {
                System.out.println("Неверный формат. Введите переменную в формате 'x = 3'.");
            }
        }

        // Вычисление результата выражения с учетом переменных
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        double result = evaluator.evaluate(ast);
        System.out.println("Результат: " + result);
    }
}
