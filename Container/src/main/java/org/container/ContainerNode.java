package org.container;

public class ContainerNode<T> {
    T value;
    ContainerNode<T> next;

    public ContainerNode(T value) {
        this.value = value;
        this.next = null;
    }
}