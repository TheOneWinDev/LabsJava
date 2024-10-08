package org.container;

public class Container<T> {
    private ContainerNode<T> head;
    private int size;

    public Container() {
        this.head = null;
        this.size = 0;
    }

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

    public int size() {
        return size;
    }
}