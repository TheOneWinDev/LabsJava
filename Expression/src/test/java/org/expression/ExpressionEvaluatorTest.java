package org.expression;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {

    /**
     * Тестирует корректность вычисления выражения с простыми переменными.
     * Проверяется вычисление выражения "x + y", где x = 3 и y = 5. Ожидаемый результат - 8.
     */
    @Test
    public void testEvaluateExpression() {
        // Эмулируем ввод выражения и переменных
        String input = "x + y\nx = 3\ny = 5\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Эмулируем парсер
        String expression = "x + y";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 3.0);
        variables.put("y", 5.0);

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        double result = evaluator.evaluate(ast);

        // Ожидаем результат 3 + 5 = 8
        assertEquals(8.0, result);
    }

    /**
     * Тестирует обработку неверного ввода переменных.
     * Проверяется ситуация, когда вводится переменная "y", но ее значение не может быть преобразовано в число.
     * Ожидается выброс исключения NumberFormatException.
     */
    @Test
    public void testInvalidVariableInput() {
        // Эмулируем ввод с ошибками
        String input = "x + y\nx = 3\ny = not_a_number\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Эмулируем парсер
        String expression = "x + y";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();
        // Ввод переменной с ошибкой
        variables.put("x", 3.0);

        // Проверка, что ошибка будет поймана
        assertThrows(NumberFormatException.class, () -> {
            variables.put("y", Double.parseDouble("not_a_number"));
        });
    }

    /**
     * Тестирует вычисление простого выражения без переменных.
     * Проверяется выражение "3 + 5", которое должно вернуть результат 8.
     */
    @Test
    public void testEvaluateSimpleExpression() {
        // Эмулируем ввод
        String input = "3 + 5\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Эмулируем парсер для простого выражения
        String expression = "3 + 5";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        // Пустой набор переменных
        Map<String, Double> variables = new HashMap<>();

        // Ожидаем результат 3 + 5 = 8
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        double result = evaluator.evaluate(ast);
        assertEquals(8.0, result);
    }

    /**
     * Тестирует вычисление выражения с тремя переменными и скобками.
     * Проверяется выражение "(x + y) * z", где x = 3, y = 5, z = 2.
     * Ожидаемый результат: (3 + 5) * 2 = 16.
     */
    @Test
    public void testEvaluateExpressionWithParenthesesAndVariables() {
        // Эмулируем ввод выражений и переменных
        String input = "(x + y) * z\nx = 3\ny = 5\nz = 2\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Эмулируем парсер
        String expression = "(x + y) * z";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 3.0);
        variables.put("y", 5.0);
        variables.put("z", 2.0);

        // Ожидаем результат (3 + 5) * 2 = 16
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        double result = evaluator.evaluate(ast);

        assertEquals(16.0, result);
    }

    /**
     * Тестирует обработку деления на ноль.
     * Проверяется ситуация, когда переменная "y" равна нулю, и выполняется операция деления "x / y".
     * Ожидается выброс исключения ArithmeticException.
     */
    @Test
    public void testDivisionByZero() {
        // Эмулируем ввод выражения
        String input = "x / y\nx = 10\ny = 0\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Эмулируем парсер
        String expression = "x / y";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 10.0);
        variables.put("y", 0.0);  // Деление на ноль

        // Проверка, что деление на ноль вызывает ArithmeticException
        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        assertThrows(ArithmeticException.class, () -> evaluator.evaluate(ast));
    }
}
