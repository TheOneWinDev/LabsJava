package org.container;

/**
 * Класс {@code Container} — это простая реализация обобщенного контейнера
 * на основе связного списка, который может хранить элементы любого типа.
 *
 * @param <T> тип элементов, хранимых в контейнере
 */
public class Container<T> {
    private ContainerNode<T> head;
    private int size;

    /**
     * Создает пустой контейнер {@code Container}.
     */
    public Container() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец контейнера.
     *
     * @param value элемент, который нужно добавить
     */
    public void add(T value) {
        ContainerNode<T> newContainerNode = new ContainerNode<>(value);
        if (head == null) {
            head = newContainerNode;
        } else {
            ContainerNode<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContainerNode;
        }
        size++;
    }

    /**
     * Возвращает элемент, находящийся в указанной позиции в контейнере.
     *
     * @param index индекс элемента для получения
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы контейнера
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы контейнера.");
        }
        ContainerNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Удаляет элемент, находящийся в указанной позиции в контейнере.
     *
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы контейнера
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы контейнера.");
        }
        if (index == 0) {
            head = head.next;
        } else {
            ContainerNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Возвращает количество элементов в контейнере.
     *
     * @return количество элементов в контейнере
     */
    public int size() {
        return size;
    }
}
