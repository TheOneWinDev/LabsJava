package org.expression;

import java.util.Stack;

/**
 * Класс, отвечающий за разбор и построение абстрактного синтаксического дерева (AST) из строкового представления математического выражения.
 * Этот парсер поддерживает выражения с операциями сложения, вычитания, умножения, деления, а также скобки и переменные.
 */
public class ExpressionParser {

    private String expression;  // Строка с исходным выражением
    private int index = 0;      // Индекс текущего символа в строке выражения

    /**
     * Конструктор, который инициализирует парсер с переданным выражением.
     * Удаляет все пробелы из выражения перед его разбором.
     *
     * @param expression Исходная строка с математическим выражением.
     */
    public ExpressionParser(String expression) {
        this.expression = expression.replaceAll("\\s+", "");  // Удаляем пробелы
    }

    /**
     * Запускает процесс парсинга выражения, начиная с главной функции.
     *
     * @return Корень абстрактного синтаксического дерева (AST) для выражения.
     */
    public ExpressionNode parse() {
        return parseExpression();
    }

    /**
     * Парсит выражение, которое может содержать сложение и вычитание.
     * Обрабатывает операции сложения и вычитания, а также рекурсивно вызывает разбор для подвыражений.
     *
     * @return Узел, представляющий выражение.
     */
    private ExpressionNode parseExpression() {
        ExpressionNode node = parseTerm();
        while (index < expression.length()) {
            char current = expression.charAt(index);
            if (current == '+' || current == '-') {
                // Операция сложения или вычитания
                OperationNode.Operation operation = current == '+' ? OperationNode.Operation.ADD : OperationNode.Operation.SUBTRACT;
                index++;  // Пропускаем символ операции
                node = new OperationNode(operation, node, parseTerm());
            } else {
                break;
            }
        }
        return node;
    }

    /**
     * Парсит терм, который может включать умножение и деление.
     * Обрабатывает операции умножения и деления.
     *
     * @return Узел, представляющий терм.
     */
    private ExpressionNode parseTerm() {
        ExpressionNode node = parseFactor();
        while (index < expression.length()) {
            char current = expression.charAt(index);
            if (current == '*' || current == '/') {
                // Операция умножения или деления
                OperationNode.Operation operation = current == '*' ? OperationNode.Operation.MULTIPLY : OperationNode.Operation.DIVIDE;
                index++;  // Пропускаем символ операции
                node = new OperationNode(operation, node, parseFactor());
            } else {
                break;
            }
        }
        return node;
    }

    /**
     * Парсит фактор, который может быть числом, переменной или подвыражением в скобках.
     *
     * @return Узел, представляющий фактор (число, переменную или выражение в скобках).
     */
    private ExpressionNode parseFactor() {
        char current = expression.charAt(index);
        if (current == '(') {
            index++;  // Пропускаем символ '('
            ExpressionNode node = parseExpression();
            expect(')');
            return node;
        } else if (Character.isDigit(current)) {
            return parseNumber();
        } else if (Character.isAlphabetic(current)) {
            return parseVariable();
        } else {
            throw new RuntimeException("Неожиданный символ: " + current);
        }
    }

    /**
     * Парсит числовое значение и создает узел типа {@link NumberNode}.
     *
     * @return Узел, представляющий число.
     */
    private ExpressionNode parseNumber() {
        StringBuilder number = new StringBuilder();
        while (index < expression.length() && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
            number.append(expression.charAt(index));
            index++;
        }
        return new NumberNode(Double.parseDouble(number.toString()));
    }

    /**
     * Парсит переменную и создает узел типа {@link VariableNode}.
     *
     * @return Узел, представляющий переменную.
     */
    private ExpressionNode parseVariable() {
        StringBuilder variable = new StringBuilder();
        while (index < expression.length() && Character.isAlphabetic(expression.charAt(index))) {
            variable.append(expression.charAt(index));
            index++;
        }
        return new VariableNode(variable.toString());
    }

    /**
     * Проверяет, что следующий символ в строке соответствует ожидаемому.
     *
     * @param expected Ожидаемый символ.
     * @throws RuntimeException Если текущий символ не соответствует ожидаемому.
     */
    private void expect(char expected) {
        if (expression.charAt(index) != expected) {
            throw new RuntimeException("Ожидался символ: " + expected);
        }
        index++;
    }
}
