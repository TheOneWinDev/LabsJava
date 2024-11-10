package org.expression;

import java.util.Map;

/**
 * Представляет собой узел выражения, который выполняет операцию между двумя выражениями.
 * Этот класс используется для представления операций (сложение, вычитание, умножение, деление) в дереве выражений.
 */
public class OperationNode extends ExpressionNode {
    private Operation operation;
    private ExpressionNode left;
    private ExpressionNode right;

    /**
     * Перечисление поддерживаемых операций.
     */
    public enum Operation {
        ADD,       // Сложение
        SUBTRACT,  // Вычитание
        MULTIPLY,  // Умножение
        DIVIDE     // Деление
    }

    /**
     * Конструктор, создающий узел операции с заданной операцией и операндами.
     *
     * @param operation Операция, которая будет выполнена (сложение, вычитание, умножение, деление).
     * @param left Левый операнд для операции.
     * @param right Правый операнд для операции.
     */
    public OperationNode(Operation operation, ExpressionNode left, ExpressionNode right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    /**
     * Получает операцию, которая выполняется этим узлом.
     *
     * @return Операция (сложение, вычитание, умножение или деление).
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Получает левый операнд для операции.
     *
     * @return Левый операнд.
     */
    public ExpressionNode getLeft() {
        return left;
    }

    /**
     * Получает правый операнд для операции.
     *
     * @return Правый операнд.
     */
    public ExpressionNode getRight() {
        return right;
    }

    /**
     * Выполняет операцию между двумя выражениями, используя переданные переменные.
     * Если операция — деление, и правый операнд равен нулю, выбрасывается исключение {@link ArithmeticException}.
     *
     * @param variables Набор переменных и их значений, используемых для вычисления.
     * @return Результат вычисления операции.
     * @throws ArithmeticException Если происходит деление на ноль.
     * @throws RuntimeException Если операция неизвестна.
     */
    @Override
    public double evaluate(Map<String, Double> variables) {
        double leftValue = left.evaluate(variables);
        double rightValue = right.evaluate(variables);

        switch (operation) {
            case ADD:
                return leftValue + rightValue;
            case SUBTRACT:
                return leftValue - rightValue;
            case MULTIPLY:
                return leftValue * rightValue;
            case DIVIDE:
                if (rightValue == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                return leftValue / rightValue;
            default:
                throw new RuntimeException("Неизвестная операция");
        }
    }
}
