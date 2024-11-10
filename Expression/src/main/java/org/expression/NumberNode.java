package org.expression;

import java.util.Map;

/**
 * Представляет собой узел числа в выражении.
 * Этот класс используется для хранения и обработки числовых значений в дереве выражений.
 */
public class NumberNode extends ExpressionNode {
    private double value;

    /**
     * Конструктор, создающий узел числа с заданным значением.
     *
     * @param value Значение числа, которое будет храниться в узле.
     */
    public NumberNode(double value) {
        this.value = value;
    }

    /**
     * Получает значение числа, хранимое в узле.
     *
     * @return Значение числа.
     */
    public double getValue() {
        return value;
    }

    /**
     * Выполняет вычисление для числа. Поскольку это просто числовое значение, оно возвращается как есть.
     *
     * @param variables Набор переменных и их значений, однако для этого узла переменные не требуются.
     * @return Значение числа.
     */
    @Override
    public double evaluate(Map<String, Double> variables) {
        return value;
    }
}
