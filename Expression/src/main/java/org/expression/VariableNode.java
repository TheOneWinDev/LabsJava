package org.expression;

import java.util.Map;

/**
 * Представляет собой узел выражения, который хранит имя переменной и может вычислять её значение.
 * Этот класс используется для представления переменных в выражениях.
 */
public class VariableNode extends ExpressionNode {
    private String name;

    /**
     * Конструктор, создающий узел переменной с заданным именем.
     *
     * @param name Имя переменной.
     */
    public VariableNode(String name) {
        this.name = name;
    }

    /**
     * Получает имя переменной.
     *
     * @return Имя переменной.
     */
    public String getName() {
        return name;
    }

    /**
     * Вычисляет значение переменной, используя переданный набор переменных.
     * Если переменная не найдена, выбрасывается исключение.
     *
     * @param variables Набор переменных и их значений, используемых для вычисления.
     * @return Значение переменной.
     * @throws RuntimeException Если переменная не найдена в наборе переменных.
     */
    @Override
    public double evaluate(Map<String, Double> variables) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Переменная не определена: " + name);
        }
        return variables.get(name);
    }
}
