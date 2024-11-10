package org.container;

/**
 * Класс {@code ContainerNode} представляет узел в связном списке,
 * используемый в контейнере {@code Container}.
 *
 * @param <T> тип значения, хранимого в узле
 */
public class ContainerNode<T> {
    /**
     * Значение, хранящееся в узле.
     */
    T value;

    /**
     * Ссылка на следующий узел в списке.
     */
    ContainerNode<T> next;

    /**
     * Создает новый узел с заданным значением.
     *
     * @param value значение для хранения в узле
     */
    public ContainerNode(T value) {
        this.value = value;
        this.next = null;
    }
}
