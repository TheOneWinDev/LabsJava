package org.container;

public class ContainerRunner {
    public static void main(String[] args) {
        Container<String> container = new Container<>();

        container.add("Первый элемент");
        container.add("Второй элемент");
        container.add("Третий элемент");

        System.out.println("Количество элементов в контейнере: " + container.size());

        for (int i = 0; i < container.size(); i++) {
            System.out.println("Элемент на позиции " + i + ": " + container.get(i));
        }

        container.remove(1);
        System.out.println("\nПосле удаления элемента на позиции 1:");

        System.out.println("Количество элементов в контейнере: " + container.size());
        for (int i = 0; i < container.size(); i++) {
            System.out.println("Элемент на позиции " + i + ": " + container.get(i));
        }
    }
}